package visitor;

import data.Annotation;
import data.Parameter;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;
import utility.Nonnull;

import java.util.List;
import java.util.stream.Collectors;

class ParameterVisitor extends Java9BaseVisitor<Parameter> {

    private ParameterVisitor() {
    }

    public static ParameterVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Parameter visitFormalParameter(Java9Parser.FormalParameterContext ctx) {
        final AnnotationVisitor annotationVisitor = AnnotationVisitor.instance();
        // fixme we can optimize modifiers flow a bit
        final List<Annotation> annotations = ctx.variableModifier().stream()
                .filter(ParameterVisitor::isAnnotation)
                .map(cm -> cm.annotation().accept(annotationVisitor))
                .collect(Collectors.toList());

        final String name = ctx.variableDeclaratorId().getText();
        final String type = ctx.unannType().getText();

        return new Parameter(name, type, annotations);
    }

    private static boolean isAnnotation(@Nonnull final Java9Parser.VariableModifierContext ctx) {
        return ctx.annotation() != null;
    }

    private static final class HOLDER {
        private static final ParameterVisitor INSTANCE = new ParameterVisitor();
    }
}
