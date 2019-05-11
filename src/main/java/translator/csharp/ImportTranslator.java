package translator.csharp;

import data.Import;
import translator.ComponentTranslator;
import utility.Nonnull;

final class ImportTranslator implements ComponentTranslator<Import> {

    private ImportTranslator() {
        //no instance
    }

    public static ImportTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    @SuppressWarnings("StringBufferReplaceableByString") // for this case, StringBuilder is more readable
    public String translate(@Nonnull final Import input, final int indentationCounter) {
        return new StringBuilder().append("using")
                .append(Codestyle.space())
                .append(input.getPackageName())
                .append(";")
                .toString();
    }

    private static final class Holder {
        private static final ImportTranslator INSTANCE = new ImportTranslator();
    }
}
