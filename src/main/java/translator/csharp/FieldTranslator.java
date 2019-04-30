package translator.csharp;

import data.Field;
import translator.PartialTranslator;
import utility.Nonnull;

class FieldTranslator implements PartialTranslator<Field> {

    private FieldTranslator() {
        //no instance
    }

    public static FieldTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Field input, final int indentationCounter) {
        return new StringBuilder()
                .append(Utility.appendIndentation(indentationCounter))
                .append(Utility.appendModifiers(input.getModifiers()))
                .append(Codestyle.space())
                .append(input.getType())
                .append(Codestyle.space())
                .append(input.getName())
                .append(Utility.appendInitializer(input.getInitializer()))
                .append(";")
                .toString();
    }

    private static class Holder {
        private static final FieldTranslator INSTANCE = new FieldTranslator();
    }
}
