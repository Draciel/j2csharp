package visitor;

import data.Annotation;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class AnnotationVisitor extends Java9BaseVisitor<Annotation> {

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
