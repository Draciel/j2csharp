package visitor;

import data.*;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;
import utility.Nonnull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class FieldVisitor extends Java9BaseVisitor<Field> {

    private FieldVisitor() {
    }

    public static FieldVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Field visitFieldDeclaration(Java9Parser.FieldDeclarationContext ctx) {
        final AnnotationVisitor annotationVisitor = AnnotationVisitor.instance();
        // fixme we can optimize modifiers flow a bit
        final List<Annotation> annotations = ctx.fieldModifier().stream()
                .filter(FieldVisitor::isAnnotation)
                .map(cm -> cm.annotation().accept(annotationVisitor))
                .collect(Collectors.toList());

        final List<Modifier> modifiers = ctx.fieldModifier().stream()
                .filter(cm -> !isAnnotation(cm))
                .map(fmc -> Modifier.of(fmc.getText()))
                .collect(Collectors.toList());

        // check if class does have package private
        final Optional<Modifier> accessModifier = modifiers.stream()
                .filter(Modifier::isAccessModifier)
                .findFirst();

        if (!accessModifier.isPresent()) {
            modifiers.add(Modifier.PACKAGE);
        }

        // fixme investigate if this is the only one variable declarator
        final Java9Parser.VariableDeclaratorContext variableDeclaratorContext = ctx.variableDeclaratorList()
                .variableDeclarator().get(0);

        final String name = variableDeclaratorContext.variableDeclaratorId().identifier().getText();
        final String type = ctx.unannType().getText();

        // fixme some statements like new Object()
        final Statement initializer = variableDeclaratorContext.variableInitializer() == null ?
                Statement.emptyStatement() : new Statement(variableDeclaratorContext.variableInitializer().getText());

        return Field.of(name, new Type(type), modifiers, annotations, initializer);
    }

    @Override
    public Field visitConstantDeclaration(final Java9Parser.ConstantDeclarationContext ctx) {
        final AnnotationVisitor annotationVisitor = AnnotationVisitor.instance();
        // fixme we can optimize modifiers flow a bit
        final List<Annotation> annotations = ctx.constantModifier().stream()
                .filter(FieldVisitor::isAnnotation)
                .map(cm -> cm.annotation().accept(annotationVisitor))
                .collect(Collectors.toList());

        final List<Modifier> modifiers = ctx.constantModifier().stream()
                .filter(cm -> !isAnnotation(cm))
                .map(fmc -> Modifier.of(fmc.getText()))
                .collect(Collectors.toList());

        // check if class does have package private
        final Optional<Modifier> accessModifier = modifiers.stream()
                .filter(Modifier::isAccessModifier)
                .findFirst();

        if (!accessModifier.isPresent()) {
            modifiers.add(Modifier.PACKAGE);
        }

        // fixme investigate if this is the only one variable declarator
        final Java9Parser.VariableDeclaratorContext variableDeclaratorContext = ctx.variableDeclaratorList()
                .variableDeclarator().get(0);

        final String name = variableDeclaratorContext.variableDeclaratorId().identifier().getText();
        final String type = ctx.unannType().getText();

        // fixme some statements like new Object()
        final Statement initializer = variableDeclaratorContext.variableInitializer() == null ?
                Statement.emptyStatement() : new Statement(variableDeclaratorContext.variableInitializer().getText());

        return Field.of(name, new Type(type), modifiers, annotations, initializer);
    }

    private static boolean isAnnotation(@Nonnull final Java9Parser.FieldModifierContext ctx) {
        return ctx.annotation() != null;
    }

    private static boolean isAnnotation(@Nonnull final Java9Parser.ConstantModifierContext ctx) {
        return ctx.annotation() != null;
    }

    private static final class HOLDER {
        private static final FieldVisitor INSTANCE = new FieldVisitor();
    }
}
