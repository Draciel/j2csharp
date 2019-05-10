package visitor;

import data.*;
import data.Class;
import data.Enum;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.List;
import java.util.stream.Collectors;

final class FileVisitor extends Java9BaseVisitor<File> {

    private FileVisitor() {
    }

    public static FileVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public File visitOrdinaryCompilation(final Java9Parser.OrdinaryCompilationContext ctx) {
        final ClassVisitor classVisitor = ClassVisitor.instance();
        final EnumVisitor enumVisitor = EnumVisitor.instance();
        final InterfaceVisitor interfaceVisitor = InterfaceVisitor.instance();
        final ImportVisitor importVisitor = ImportVisitor.instance();

        final String packageName = ctx.packageDeclaration().packageName().identifier().getText();

        final List<Import> imports = ctx.importDeclaration()
                .stream()
                .map(idc -> idc.accept(importVisitor))
                .collect(Collectors.toList());

        //fixme handle multiple classes in one file
        Class clazz = null;
        Enum enumm = null;
        Interface interfacee = null;
        if (ctx.typeDeclaration(0).interfaceDeclaration() != null) {
            interfacee = ctx.typeDeclaration(0).interfaceDeclaration().accept(interfaceVisitor);
        } else if (ctx.typeDeclaration(0).classDeclaration() != null && ctx.typeDeclaration(0).classDeclaration().normalClassDeclaration() != null) {
            clazz = ctx.typeDeclaration(0).classDeclaration().accept(classVisitor);
        } else if (ctx.typeDeclaration(0).classDeclaration().enumDeclaration() != null) {
            enumm = ctx.typeDeclaration(0).classDeclaration().enumDeclaration().accept(enumVisitor);
        } else {
            throw new IllegalArgumentException("This type of object is not supported");
        }

        return new File(packageName, imports, clazz, enumm, interfacee);
    }

    private static final class Holder {
        private static final FileVisitor INSTANCE = new FileVisitor();
    }
}
