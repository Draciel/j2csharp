package pl.draciel.j2csharp.translator.csharp;

import pl.draciel.j2csharp.data.Statement;
import pl.draciel.j2csharp.translator.ComponentTranslator;
import pl.draciel.j2csharp.utility.Nonnull;

final class ReturnStatementTranslator implements ComponentTranslator<Statement.ReturnStatement> {

    private ReturnStatementTranslator() {
        //no instance
    }

    public static ReturnStatementTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.ReturnStatement input, final int indentationCounter) {
        return new StringBuilder()
                .append(Utility.appendIndentation(indentationCounter))
                .append("return")
                .append(Codestyle.space())
                .append(StatementExpressionTranslator.instance().translate(input.getReturnStatement(), 0))
                .append(";")
                .toString();
    }

    private static final class Holder {
        private static final ReturnStatementTranslator INSTANCE = new ReturnStatementTranslator();
    }
}
