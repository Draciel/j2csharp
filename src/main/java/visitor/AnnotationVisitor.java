package visitor;

import data.Annotation;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

final class AnnotationVisitor extends Java9BaseVisitor<Annotation> {

    private AnnotationVisitor() {
        //no instance
    }

    public static AnnotationVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Annotation visitAnnotation(Java9Parser.AnnotationContext ctx) {
        return new Annotation(ctx.getText());
    }

    private static final class HOLDER {
        private static final AnnotationVisitor INSTANCE = new AnnotationVisitor();
    }
}
