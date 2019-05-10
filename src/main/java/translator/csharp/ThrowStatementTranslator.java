package translator.csharp;

import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

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
        return Utility.appendIndentation(indentationCounter) + "throw" + "new" +
                statementExpressionTranslator.translate(input.getStatementExpression(), 0);
    }

    private static final class Holder {
        private static final ThrowStatementTranslator INSTANCE = new ThrowStatementTranslator();
    }
}
