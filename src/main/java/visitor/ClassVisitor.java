package visitor;

import data.*;
import data.Class;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;
import utility.Nonnull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

class ClassVisitor extends Java9BaseVisitor<Class> {

    private ClassVisitor() {
    }

    public static ClassVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Class visitClassDeclaration(Java9Parser.ClassDeclarationContext ctx) {
        final String name = ctx.normalClassDeclaration().identifier().getText();
        final FieldVisitor fieldVisitor = FieldVisitor.instance();
        final MethodVisitor methodVisitor = MethodVisitor.instance();
        final ConstructorVisitor constructorVisitor = ConstructorVisitor.instance();
        final AnnotationVisitor annotationVisitor = AnnotationVisitor.instance();
        final TypeParameterVisitor typeParameterVisitor = TypeParameterVisitor.instance();

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

        // fixme we can optimize modifiers flow a bit
        final List<Annotation> annotations = ctx.normalClassDeclaration().classModifier().stream()
                .filter(ClassVisitor::isAnnotation)
                .map(cm -> cm.annotation().accept(annotationVisitor))
                .collect(Collectors.toList());

        final List<Modifier> modifiers = ctx.normalClassDeclaration().classModifier().stream()
                .filter(cm -> !isAnnotation(cm))
                .map(cm -> Modifier.of(cm.getText()))
                .collect(Collectors.toList());

        // check if class does have package private
        final Optional<Modifier> accessModifier = modifiers.stream()
                .filter(Modifier::isAccessModifier)
                .findFirst();

        final String superClass = ctx.normalClassDeclaration().superclass() == null ? "" :
                ctx.normalClassDeclaration().superclass().classType().getText();

        final List<String> superInterfaces = ctx.normalClassDeclaration().superinterfaces() == null ? emptyList() :
                ctx.normalClassDeclaration().superinterfaces().interfaceTypeList().interfaceType().stream()
                        .map(i -> i.classType().getText())
                        .collect(Collectors.toList());

        final List<Generic> generics = ctx.normalClassDeclaration().typeParameters() == null ? Collections.emptyList() :
                ctx.normalClassDeclaration().typeParameters().typeParameterList().typeParameter().stream()
                        .map(tpc -> tpc.accept(typeParameterVisitor))
                        .collect(Collectors.toList());

        if (!accessModifier.isPresent()) {
            modifiers.add(Modifier.PACKAGE);
        }

        // handle other things
        return new Class(name, modifiers, constructors, methods, fields, annotations, superClass, superInterfaces, generics);
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

    private static boolean isAnnotation(@Nonnull final Java9Parser.ClassModifierContext ctx) {
        return ctx.annotation() != null;
    }

    private static final class HOLDER {
        private static final ClassVisitor INSTANCE = new ClassVisitor();
    }
}

