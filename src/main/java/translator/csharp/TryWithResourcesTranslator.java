package translator.csharp;

import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

final class TryWithResourcesTranslator implements ComponentTranslator<Statement.TryWithResourcesStatement> {

    private TryWithResourcesTranslator() {
        //no instance
    }

    public static TryWithResourcesTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.TryWithResourcesStatement input, final int indentationCounter) {
        throw new UnsupportedOperationException("Try with resources is not supported by c#");
    }

    private static final class Holder {
        private static final TryWithResourcesTranslator INSTANCE = new TryWithResourcesTranslator();
    }
}
