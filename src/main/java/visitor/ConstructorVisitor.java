package visitor;

import data.*;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;
import utility.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class ConstructorVisitor extends Java9BaseVisitor<Constructor> {

    private ConstructorVisitor() {
    }

    public static ConstructorVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Constructor visitConstructorDeclaration(Java9Parser.ConstructorDeclarationContext ctx) {
        final ParameterVisitor parameterVisitor = ParameterVisitor.instance();
        final StatementVisitor statementVisitor = StatementVisitor.instance();

        final String className = ctx.constructorDeclarator().simpleTypeName().identifier().getText();

        final List<Parameter> parameters = new ArrayList<>();
        parameters.add(ctx.constructorDeclarator()
                .formalParameterList()
                .lastFormalParameter()
                .formalParameter()
                .accept(parameterVisitor));

        final List<Statement> statements = ctx.constructorBody()
                .blockStatements()
                .blockStatement()
                .stream()
                .map(b -> b.accept(statementVisitor))
                .collect(Collectors.toList());

        final AnnotationVisitor annotationVisitor = AnnotationVisitor.instance();
        // fixme we can optimize modifiers flow a bit
        final List<Annotation> annotations = ctx.constructorModifier().stream()
                .filter(ConstructorVisitor::isAnnotation)
                .map(cm -> cm.annotation().accept(annotationVisitor))
                .collect(Collectors.toList());

        final List<Modifier> modifiers = ctx.constructorModifier().stream()
                .filter(cm -> !isAnnotation(cm))
                .map(cm -> Modifier.of(cm.getText()))
                .collect(Collectors.toList());

        // check if class does have package private
        final Optional<Modifier> accessModifier = modifiers.stream()
                .filter(Modifier::isAccessModifier)
                .findFirst();

        if (!accessModifier.isPresent()) {
            modifiers.add(Modifier.PACKAGE);
        }

        return new Constructor(className, parameters, statements, modifiers, annotations);
    }

    private static boolean isAnnotation(@Nonnull final Java9Parser.ConstructorModifierContext ctx) {
        return ctx.annotation() != null;
    }

    private static final class HOLDER {
        private static final ConstructorVisitor INSTANCE = new ConstructorVisitor();
    }
}
