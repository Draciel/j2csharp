package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.Annotation;
import pl.draciel.j2csharp.data.Parameter;
import pl.draciel.j2csharp.data.Type;
import generated.Java9BaseVisitor;
import generated.Java9Parser;
import pl.draciel.j2csharp.utility.Nonnull;

import java.util.List;
import java.util.stream.Collectors;

final class ParameterVisitor extends Java9BaseVisitor<Parameter> {

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

        return new Parameter(name, new Type(type), annotations, false);
    }

    @Override
    public Parameter visitLastFormalParameter(final Java9Parser.LastFormalParameterContext ctx) {
        final AnnotationVisitor annotationVisitor = AnnotationVisitor.instance();
        // fixme we can optimize modifiers flow a bit
        final List<Annotation> annotations = ctx.variableModifier().stream()
                .filter(ParameterVisitor::isAnnotation)
                .map(cm -> cm.annotation().accept(annotationVisitor))
                .collect(Collectors.toList());

        final String name = ctx.variableDeclaratorId().getText();
        final String type = ctx.unannType().getText();

        return new Parameter(name, new Type(type), annotations, true);
    }

    private static boolean isAnnotation(@Nonnull final Java9Parser.VariableModifierContext ctx) {
        return ctx.annotation() != null;
    }

    private static final class HOLDER {
        private static final ParameterVisitor INSTANCE = new ParameterVisitor();
    }
}
