package translator.csharp;

import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

final class StatementExpressionTranslator implements ComponentTranslator<Statement.StatementExpression> {

    private StatementExpressionTranslator() {
        //no instance
    }

    public static StatementExpressionTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.StatementExpression input, final int indentationCounter) {
        final StatementBootstrap statementBootstrap = StatementBootstrap.instance();

        final String output = Utility.appendIndentation(indentationCounter) + statementBootstrap.translate(input, 0);

        if (input.isExpression()) {
            return output;
        }
        return output + ";";
    }

    private static final class Holder {
        private static final StatementExpressionTranslator INSTANCE = new StatementExpressionTranslator();
    }
}
