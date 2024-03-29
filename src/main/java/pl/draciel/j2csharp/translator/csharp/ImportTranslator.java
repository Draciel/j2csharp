package pl.draciel.j2csharp.translator.csharp;

import pl.draciel.j2csharp.data.Import;
import pl.draciel.j2csharp.translator.ComponentTranslator;
import pl.draciel.j2csharp.utility.Nonnull;

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
        //fixme
        if (input.getClassName().contains("Exception")) {
            return new StringBuilder().append("using")
                    .append(Codestyle.space())
                    .append(input.getClassName())
                    .append(Codestyle.space())
                    .append("=")
                    .append(Codestyle.space())
                    .append(input.getPackageName())
                    .append(".")
                    .append(input.getClassName())
                    .append(";")
                    .toString();
        }
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
