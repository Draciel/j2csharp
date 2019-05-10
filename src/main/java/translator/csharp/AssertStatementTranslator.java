package translator.csharp;

import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

final class AssertStatementTranslator implements ComponentTranslator<Statement.AssertStatement> {

    private AssertStatementTranslator() {
        //no instance
    }

    public static AssertStatementTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.AssertStatement input, final int indentationCounter) {
        throw new UnsupportedOperationException("Asserts are not supported in C#");
    }

    private static final class Holder {
        private static final AssertStatementTranslator INSTANCE = new AssertStatementTranslator();
    }

}
