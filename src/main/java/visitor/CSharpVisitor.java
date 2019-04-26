package visitor;

import data.Class;
import utility.Nonnull;

import static pl.jcsharp.grammar.Java9Parser.ClassDeclarationContext;

public class CSharpVisitor {

    @Nonnull
    public Class parse(ClassDeclarationContext classDeclarationContext) {
        final ClassVisitor classVisitor = new ClassVisitor();
        return classVisitor.visit(classDeclarationContext);
    }

}
