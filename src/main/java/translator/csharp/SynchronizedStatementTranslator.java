package translator.csharp;

import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

final class SynchronizedStatementTranslator implements ComponentTranslator<Statement.SynchronizedStatement> {

    private SynchronizedStatementTranslator() {
        //no instance
    }

    public static SynchronizedStatementTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.SynchronizedStatement input, final int indentationCounter) {
        throw new UnsupportedOperationException("Synchronized statements are unsupported");
    }

    private static final class Holder {
        private static final SynchronizedStatementTranslator INSTANCE = new SynchronizedStatementTranslator();
    }

}
