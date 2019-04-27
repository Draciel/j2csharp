package visitor;

import data.*;
import data.Class;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;
import utility.Nonnull;

import java.util.List;
import java.util.stream.Collectors;

class ClassVisitor extends Java9BaseVisitor<Class> {

    private ClassVisitor() {
    }

    public static ClassVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Class visitClassDeclaration(Java9Parser.ClassDeclarationContext ctx) {
        final String name = ctx.normalClassDeclaration().identifier().getText();
        final MethodVisitor methodVisitor = MethodVisitor.instance();
        final FieldVisitor fieldVisitor = FieldVisitor.instance();
        final ConstructorVisitor constructorVisitor = ConstructorVisitor.instance();

        final List<Method> methods = ctx.normalClassDeclaration().classBody().classBodyDeclaration().stream()
                .filter(ClassVisitor::isMethod)
                .map(b -> b.classMemberDeclaration().methodDeclaration().accept(methodVisitor))
                .collect(Collectors.toList());

        final List<Field> fields = ctx.normalClassDeclaration().classBody().classBodyDeclaration().stream()
                .filter(ClassVisitor::isField)
                .map(b -> b.classMemberDeclaration().fieldDeclaration().accept(fieldVisitor))
                .collect(Collectors.toList());

        final List<Constructor> constructors = ctx.normalClassDeclaration().classBody().classBodyDeclaration().stream()
                .filter(ClassVisitor::isConstructor)
                .map(b -> b.constructorDeclaration().accept(constructorVisitor))
                .collect(Collectors.toList());

        final List<Modifier> modifiers = ctx.normalClassDeclaration().classModifier().stream()
                .map(cm -> Modifier.of(cm.getText()))
                .collect(Collectors.toList());
        // handle other things
        return new Class(name, modifiers, constructors, methods, fields);
    }

    // fixme find better way...
    private static boolean isMethod(@Nonnull final Java9Parser.ClassBodyDeclarationContext ctx) {
        return (ctx.classMemberDeclaration() != null && ctx.classMemberDeclaration().methodDeclaration() != null);
    }

    private static boolean isField(@Nonnull final Java9Parser.ClassBodyDeclarationContext ctx) {
        return (ctx.classMemberDeclaration() != null && ctx.classMemberDeclaration().fieldDeclaration() != null);
    }

    private static boolean isConstructor(@Nonnull final Java9Parser.ClassBodyDeclarationContext ctx) {
        return ctx.constructorDeclaration() != null;
    }

    private static final class HOLDER {
        private static final ClassVisitor INSTANCE = new ClassVisitor();
    }
}

