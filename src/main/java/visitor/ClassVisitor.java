package visitor;

import data.Class;
import data.Field;
import data.Method;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;
import utility.Nonnull;

import java.util.List;
import java.util.stream.Collectors;

class ClassVisitor extends Java9BaseVisitor<Class> {

    @Override
    public Class visitClassDeclaration(Java9Parser.ClassDeclarationContext ctx) {
        final String name = ctx.normalClassDeclaration().identifier().getText();
        final MethodVisitor methodVisitor = new MethodVisitor();
        final FieldVisitor fieldVisitor = new FieldVisitor();

        final List<Method> methods = ctx.normalClassDeclaration().classBody().classBodyDeclaration().stream()
                .filter(ClassVisitor::isMethod)
                .map(b -> b.classMemberDeclaration().methodDeclaration().accept(methodVisitor))
                .collect(Collectors.toList());

        final List<Field> fields = ctx.normalClassDeclaration().classBody().classBodyDeclaration().stream()
                .filter(ClassVisitor::isField)
                .map(b -> b.classMemberDeclaration().fieldDeclaration().accept(fieldVisitor))
                .collect(Collectors.toList());
        // handle other things
        return new Class(name, methods, fields);
    }

    // fixme find better way...
    private static boolean isMethod(@Nonnull final Java9Parser.ClassBodyDeclarationContext ctx) {
        return (ctx.classMemberDeclaration() != null && ctx.classMemberDeclaration().methodDeclaration() != null);
    }

    private static boolean isField(@Nonnull final Java9Parser.ClassBodyDeclarationContext ctx) {
        return (ctx.classMemberDeclaration() != null && ctx.classMemberDeclaration().fieldDeclaration() != null);
    }

}

