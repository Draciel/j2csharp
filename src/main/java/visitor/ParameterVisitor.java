package visitor;

import data.Parameter;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class ParameterVisitor extends Java9BaseVisitor<Parameter> {

    private ParameterVisitor() {
    }

    public static ParameterVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Parameter visitFormalParameter(Java9Parser.FormalParameterContext ctx) {
        final String name = ctx.variableDeclaratorId().getText();
        final String type = ctx.unannType().getText();
        return new Parameter(name, type);
    }

    private static final class HOLDER {
        private static final ParameterVisitor INSTANCE = new ParameterVisitor();
    }
}
