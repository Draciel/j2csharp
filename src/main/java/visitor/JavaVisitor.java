package visitor;

import data.Class;
import data.File;
import data.Import;
import pl.jcsharp.grammar.Java9Parser;
import utility.Nonnull;

import java.util.List;
import java.util.stream.Collectors;

// TODO: find better name
public class JavaVisitor {

    @Nonnull
    public File parse(Java9Parser parser) {
        final ClassVisitor classVisitor = ClassVisitor.instance();
        final ImportVisitor importVisitor = ImportVisitor.instance();
        final Java9Parser.OrdinaryCompilationContext ordinaryCompilationContext = parser.compilationUnit()
                .ordinaryCompilation();

        final String packageName = ordinaryCompilationContext.packageDeclaration().packageName().identifier().getText();

        final List<Import> imports = ordinaryCompilationContext.importDeclaration()
                .stream()
                .map(idc -> idc.accept(importVisitor))
                .collect(Collectors.toList());

        //fixme handle multiple classes in one file
        final Class clazz = ordinaryCompilationContext.typeDeclaration(0).classDeclaration()
                .accept(classVisitor);

        return new File(packageName, imports, clazz);
    }

}
