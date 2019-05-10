package visitor;

import data.*;
import data.Statement;
import org.antlr.v4.runtime.misc.Interval;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;
import utility.Nonnull;
import utility.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

final class FieldVisitor extends Java9BaseVisitor<Field> {

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
        final Statement.StatementExpression initializer =
                variableDeclaratorContext.variableInitializer() == null ?
                        Statement.StatementExpression.empty() :
                        parseVariableDeclarator(variableDeclaratorContext);

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

        final Statement.StatementExpression initializer =
                variableDeclaratorContext.variableInitializer() == null ?
                        Statement.StatementExpression.empty() :
                        parseVariableDeclarator(variableDeclaratorContext);

        return Field.of(name, new Type(type), modifiers, annotations, initializer);
    }

    @Override
    public Field visitLocalVariableDeclaration(final Java9Parser.LocalVariableDeclarationContext ctx) {
        final List<Modifier> modifiers = ctx.variableModifier().stream()
                .filter(vm -> !isAnnotation(vm))
                .map(vm -> Modifier.of(vm.getText()))
                .collect(Collectors.toList());

        //fixme handle multiple variable declaration
        final Java9Parser.VariableDeclaratorContext variableDeclaratorContext = ctx.variableDeclaratorList()
                .variableDeclarator().get(0);

        final String name = variableDeclaratorContext.variableDeclaratorId().identifier().getText();
        final String type = ctx.unannType().getText();

        final Statement.StatementExpression initializer =
                variableDeclaratorContext.variableInitializer() == null ?
                        Statement.StatementExpression.empty() :
                        parseVariableDeclarator(variableDeclaratorContext);

        //fixme handle annotations for local variables
        return Field.of(name, new Type(type), modifiers, Collections.emptyList(), initializer);
    }

    @Override
    public Field visitEnhancedForStatement(final Java9Parser.EnhancedForStatementContext ctx) {
        final List<Modifier> modifiers = ctx.variableModifier().stream()
                .filter(vm -> !isAnnotation(vm))
                .map(vm -> Modifier.of(vm.getText()))
                .collect(Collectors.toList());

        final String name = ctx.variableDeclaratorId().identifier().getText();
        final String type = ctx.unannType().getText();

        final Statement.StatementExpression initializer =
                Statement.StatementExpression.empty();

        //fixme handle annotations for local variables
        return Field.of(name, new Type(type), modifiers, Collections.emptyList(), initializer);
    }

    @Override
    public Field visitResource(final Java9Parser.ResourceContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        final List<Modifier> modifiers = ctx.variableModifier().stream()
                .filter(r -> !isAnnotation(r))
                .map(r -> Modifier.of(r.getText()))
                .collect(Collectors.toList());

        final String name = ctx.variableDeclaratorId().identifier().getText();
        final String type = ctx.unannType().getText();

        final Statement.StatementExpression statementExpression =
                ctx.expression().accept(statementExpressionVisitor);

        //fixme handle annotations for local variables
        return Field.of(name, new Type(type), modifiers, Collections.emptyList(), statementExpression);
    }

    private static Statement.StatementExpression parseVariableDeclarator(@Nonnull final Java9Parser.VariableDeclaratorContext ctx) {
        final int a = ctx.start.getStartIndex();
        final int b = ctx.stop.getStopIndex();
        final Interval interval = new Interval(a, b);
        return Statement.StatementExpression.of(ctx.start.getInputStream().getText(interval));
    }

    private static boolean isAnnotation(@Nonnull final Java9Parser.FieldModifierContext ctx) {
        return ctx.annotation() != null;
    }

    private static boolean isAnnotation(@Nonnull final Java9Parser.ConstantModifierContext ctx) {
        return ctx.annotation() != null;
    }

    private static boolean isAnnotation(@Nullable final Java9Parser.VariableModifierContext ctx) {
        return ctx.annotation() != null;
    }

    private static final class HOLDER {
        private static final FieldVisitor INSTANCE = new FieldVisitor();
    }
}
