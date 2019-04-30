package visitor;

import data.Class;
import data.File;
import data.Import;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.List;
import java.util.stream.Collectors;

class FileVisitor extends Java9BaseVisitor<File> {

    private FileVisitor() {
    }

    public static FileVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public File visitOrdinaryCompilation(final Java9Parser.OrdinaryCompilationContext ctx) {
        final ClassVisitor classVisitor = ClassVisitor.instance();
        final ImportVisitor importVisitor = ImportVisitor.instance();

        final String packageName = ctx.packageDeclaration().packageName().identifier().getText();

        final List<Import> imports = ctx.importDeclaration()
                .stream()
                .map(idc -> idc.accept(importVisitor))
                .collect(Collectors.toList());

        //fixme handle multiple classes in one file
        final Class clazz = ctx.typeDeclaration(0).classDeclaration()
                .accept(classVisitor);

        return new File(packageName, imports, clazz);
    }

    private static final class Holder {
        private static final FileVisitor INSTANCE = new FileVisitor();
    }
}
