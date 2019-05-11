package visitor;

import data.*;
import data.Class;
import data.Enum;
import org.antlr.v4.runtime.RuleContext;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;
import utility.Nonnull;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

final class EnumVisitor extends Java9BaseVisitor<Enum> {

    private EnumVisitor() {
        //no instance
    }

    public static EnumVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Enum visitEnumDeclaration(final Java9Parser.EnumDeclarationContext ctx) {
        final String name = ctx.identifier().getText();
        final FieldVisitor fieldVisitor = FieldVisitor.instance();
        final MethodVisitor methodVisitor = MethodVisitor.instance();
        final ConstructorVisitor constructorVisitor = ConstructorVisitor.instance();
        final AnnotationVisitor annotationVisitor = AnnotationVisitor.instance();
        final InterfaceVisitor interfaceVisitor = InterfaceVisitor.instance();
        final ClassVisitor classVisitor = ClassVisitor.instance();

        final List<Method> methods = ctx.enumBody().enumBodyDeclarations() == null ? Collections.emptyList() :
                ctx.enumBody().enumBodyDeclarations().classBodyDeclaration().stream()
                        .filter(EnumVisitor::isMethod)
                        .map(b -> b.classMemberDeclaration().methodDeclaration().accept(methodVisitor))
                        .collect(Collectors.toList());

        final List<Field> fields = ctx.enumBody().enumBodyDeclarations() == null ? Collections.emptyList() :
                ctx.enumBody().enumBodyDeclarations().classBodyDeclaration().stream()
                        .filter(EnumVisitor::isField)
                        .map(b -> b.classMemberDeclaration().fieldDeclaration().accept(fieldVisitor))
                        .collect(Collectors.toList());

        final List<Constructor> constructors = ctx.enumBody().enumBodyDeclarations() == null ? Collections.emptyList() :
                ctx.enumBody().enumBodyDeclarations().classBodyDeclaration().stream()
                        .filter(EnumVisitor::isConstructor)
                        .map(b -> b.constructorDeclaration().accept(constructorVisitor))
                        .collect(Collectors.toList());

        // fixme we can optimize modifiers flow a bit
        final List<Annotation> annotations = ctx.classModifier().stream()
                .filter(EnumVisitor::isAnnotation)
                .map(cm -> cm.annotation().accept(annotationVisitor))
                .collect(Collectors.toList());

        final List<Modifier> modifiers = ctx.classModifier().stream()
                .filter(cm -> !isAnnotation(cm))
                .map(cm -> Modifier.of(cm.getText()))
                .collect(Collectors.toList());

        final List<Class> classes = ctx.enumBody().enumBodyDeclarations() == null ? Collections.emptyList() :
                ctx.enumBody().enumBodyDeclarations().classBodyDeclaration().stream()
                        .filter(EnumVisitor::isClass)
                        .map(b -> b.classMemberDeclaration().classDeclaration().accept(classVisitor))
                        .collect(Collectors.toList());

        final List<Interface> interfaces = ctx.enumBody().enumBodyDeclarations() == null ? Collections.emptyList() :
                ctx.enumBody().enumBodyDeclarations().classBodyDeclaration().stream()
                        .filter(EnumVisitor::isInterface)
                        .map(b -> b.classMemberDeclaration().interfaceDeclaration().accept(interfaceVisitor))
                        .collect(Collectors.toList());

        final List<Enum> enums = ctx.enumBody().enumBodyDeclarations() == null ? Collections.emptyList() :
                ctx.enumBody().enumBodyDeclarations().classBodyDeclaration().stream()
                        .filter(EnumVisitor::isEnum)
                        .map(b -> b.classMemberDeclaration().classDeclaration().enumDeclaration().accept(this))
                        .collect(Collectors.toList());

        final List<EnumConstant> enumConstants = ctx.enumBody().enumConstantList().enumConstant().stream()
                .map(b -> new EnumConstant(b.identifier().getText(), b.argumentList() == null ?
                        Collections.emptyList() :
                        b.argumentList().expression().stream()
                                .map(RuleContext::getText)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());

        // check if class does have package private
        final Optional<Modifier> accessModifier = modifiers.stream()
                .filter(Modifier::isAccessModifier)
                .findFirst();

        final List<Type> superInterfaces = ctx.superinterfaces() == null ? emptyList() :
                ctx.superinterfaces().interfaceTypeList().interfaceType().stream()
                        .map(i -> new Type(i.classType().getText()))
                        .collect(Collectors.toList());

        if (!accessModifier.isPresent()) {
            modifiers.add(Modifier.PACKAGE);
        }

        // handle other things
        return new Enum(name, modifiers, constructors, fields, methods, annotations, superInterfaces, classes,
                interfaces, enums, enumConstants);
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

    private static boolean isEnum(@Nonnull final Java9Parser.ClassBodyDeclarationContext ctx) {
        return (ctx.classMemberDeclaration() != null && ctx.classMemberDeclaration().classDeclaration() != null && ctx.classMemberDeclaration().classDeclaration().enumDeclaration() != null);
    }

    private static boolean isClass(@Nonnull final Java9Parser.ClassBodyDeclarationContext ctx) {
        return (ctx.classMemberDeclaration() != null && ctx.classMemberDeclaration().classDeclaration() != null && ctx.classMemberDeclaration().classDeclaration().normalClassDeclaration() != null);
    }

    private static boolean isInterface(@Nonnull final Java9Parser.ClassBodyDeclarationContext ctx) {
        return (ctx.classMemberDeclaration() != null && ctx.classMemberDeclaration().interfaceDeclaration() != null);
    }

    private static boolean isAnnotation(@Nonnull final Java9Parser.ClassModifierContext ctx) {
        return ctx.annotation() != null;
    }

    private static class Holder {
        private static final EnumVisitor INSTANCE = new EnumVisitor();
    }

}
