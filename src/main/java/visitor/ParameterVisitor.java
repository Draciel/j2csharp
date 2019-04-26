package visitor;

import data.Parameter;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class ParameterVisitor extends Java9BaseVisitor<Parameter> {

    @Override
    public Parameter visitTypeParameter(final Java9Parser.TypeParameterContext ctx) {
        final String name = ctx.identifier().getText();
        return new Parameter(name, "wtf");
    }
}
