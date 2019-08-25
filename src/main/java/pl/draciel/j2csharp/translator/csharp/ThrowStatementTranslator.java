package pl.draciel.j2csharp.translator.csharp;

import pl.draciel.j2csharp.data.Statement;
import pl.draciel.j2csharp.translator.ComponentTranslator;
import pl.draciel.j2csharp.utility.Nonnull;

final class ThrowStatementTranslator implements ComponentTranslator<Statement.ThrowStatement> {

    private ThrowStatementTranslator() {
        //no instance
    }

    public static ThrowStatementTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.ThrowStatement input, final int indentationCounter) {
        final StatementExpressionTranslator statementExpressionTranslator = StatementExpressionTranslator.instance();
        return Utility.appendIndentation(indentationCounter) + "throw" + Codestyle.space() +
                statementExpressionTranslator.translate(input.getStatementExpression(), 0) + ";";
    }

    private static final class Holder {
        private static final ThrowStatementTranslator INSTANCE = new ThrowStatementTranslator();
    }
}
