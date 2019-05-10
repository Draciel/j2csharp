package translator.csharp;

import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

final class ContinueStatementTranslator implements ComponentTranslator<Statement.ContinueStatement> {

    private ContinueStatementTranslator() {
        //no instance
    }

    public static ContinueStatementTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.ContinueStatement input, final int indentationCounter) {
        if (input.getIdentifier() != null) {
            throw new UnsupportedOperationException("Continue labels are unsupported in c#");
        }
        return Utility.appendIndentation(indentationCounter) + "continue" + ";";
    }

    private static final class Holder {
        private static final ContinueStatementTranslator INSTANCE = new ContinueStatementTranslator();
    }
}
