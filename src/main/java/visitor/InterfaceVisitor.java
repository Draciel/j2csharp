package visitor;

import data.Class;
import data.Enum;
import data.*;
import generated.Java9BaseVisitor;
import generated.Java9Parser;
import utility.Nonnull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

final class InterfaceVisitor extends Java9BaseVisitor<Interface> {

    private InterfaceVisitor() {
    }

    public static InterfaceVisitor instance() {
        return InterfaceVisitor.HOLDER.INSTANCE;
    }


    @Override
    public Interface visitInterfaceDeclaration(final Java9Parser.InterfaceDeclarationContext ctx) {
        final String name = ctx.normalInterfaceDeclaration().identifier().getText();
        final FieldVisitor fieldVisitor = FieldVisitor.instance();
        final MethodVisitor methodVisitor = MethodVisitor.instance();
        final AnnotationVisitor annotationVisitor = AnnotationVisitor.instance();
        final TypeParameterVisitor typeParameterVisitor = TypeParameterVisitor.instance();
        final ClassVisitor classVisitor = ClassVisitor.instance();
        final EnumVisitor enumVisitor = EnumVisitor.instance();

        final List<Method> methods =
                ctx.normalInterfaceDeclaration().interfaceBody().interfaceMemberDeclaration().stream()
                .filter(InterfaceVisitor::isMethod)
                .map(b -> b.interfaceMethodDeclaration().accept(methodVisitor))
                .collect(Collectors.toList());

        final List<Field> fields =
                ctx.normalInterfaceDeclaration().interfaceBody().interfaceMemberDeclaration().stream()
                .filter(InterfaceVisitor::isField)
                .map(b -> b.constantDeclaration().accept(fieldVisitor))
                .collect(Collectors.toList());

        // fixme we can optimize modifiers flow a bit
        final List<Annotation> annotations = ctx.normalInterfaceDeclaration().interfaceModifier().stream()
                .filter(InterfaceVisitor::isAnnotation)
                .map(cm -> cm.annotation().accept(annotationVisitor))
                .collect(Collectors.toList());

        final List<Modifier> modifiers = ctx.normalInterfaceDeclaration().interfaceModifier().stream()
                .filter(cm -> !isAnnotation(cm))
                .map(cm -> Modifier.of(cm.getText()))
                .collect(Collectors.toList());

        final List<Class> classes =
                ctx.normalInterfaceDeclaration().interfaceBody().interfaceMemberDeclaration().stream()
                .filter(InterfaceVisitor::isClass)
                .map(b -> b.classDeclaration().accept(classVisitor))
                .collect(Collectors.toList());

        final List<Interface> interfaces =
                ctx.normalInterfaceDeclaration().interfaceBody().interfaceMemberDeclaration().stream()
                .filter(InterfaceVisitor::isInterface)
                .map(b -> b.interfaceDeclaration().accept(this))
                .collect(Collectors.toList());

        final List<Enum> enums = ctx.normalInterfaceDeclaration().interfaceBody().interfaceMemberDeclaration().stream()
                .filter(InterfaceVisitor::isEnum)
                .map(b -> b.classDeclaration().enumDeclaration().accept(enumVisitor))
                .collect(Collectors.toList());

        // check if class does have package private
        final Optional<Modifier> accessModifier = modifiers.stream()
                .filter(Modifier::isAccessModifier)
                .findFirst();

        final List<Type> superInterfaces = ctx.normalInterfaceDeclaration().extendsInterfaces() == null ? emptyList() :
                ctx.normalInterfaceDeclaration().extendsInterfaces().interfaceTypeList().interfaceType().stream()
                        .map(i -> new Type(i.classType().getText()))
                        .collect(Collectors.toList());

        final List<Generic> generics = ctx.normalInterfaceDeclaration().typeParameters() == null ?
                Collections.emptyList() :
                ctx.normalInterfaceDeclaration().typeParameters().typeParameterList().typeParameter().stream()
                        .map(tpc -> tpc.accept(typeParameterVisitor))
                        .collect(Collectors.toList());

        if (!accessModifier.isPresent()) {
            modifiers.add(Modifier.PACKAGE);
        }

        // handle other things
        return new Interface(name, modifiers, fields, methods, annotations, superInterfaces, generics, interfaces,
                classes, enums);
    }

    // fixme find better way...
    private static boolean isMethod(@Nonnull final Java9Parser.InterfaceMemberDeclarationContext ctx) {
        return ctx.interfaceMethodDeclaration() != null;
    }

    private static boolean isField(@Nonnull final Java9Parser.InterfaceMemberDeclarationContext ctx) {
        return ctx.constantDeclaration() != null;
    }

    private static boolean isEnum(@Nonnull final Java9Parser.InterfaceMemberDeclarationContext ctx) {
        return (ctx.classDeclaration() != null && ctx.classDeclaration().enumDeclaration() != null);
    }

    private static boolean isClass(@Nonnull final Java9Parser.InterfaceMemberDeclarationContext ctx) {
        return ctx.classDeclaration() != null;
    }

    private static boolean isInterface(@Nonnull final Java9Parser.InterfaceMemberDeclarationContext ctx) {
        return ctx.interfaceDeclaration() != null;
    }

    private static boolean isAnnotation(@Nonnull final Java9Parser.InterfaceModifierContext ctx) {
        return ctx.annotation() != null;
    }

    private static final class HOLDER {
        private static final InterfaceVisitor INSTANCE = new InterfaceVisitor();
    }

}
