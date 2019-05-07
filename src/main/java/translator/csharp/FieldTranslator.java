package translator.csharp;

import data.Field;
import translator.ComponentTranslator;
import utility.Nonnull;

class FieldTranslator implements ComponentTranslator<Field> {

    private FieldTranslator() {
        //no instance
    }

    public static FieldTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Field input, final int indentationCounter) {
        final TypeBootstrap bootstrap = TypeBootstrap.instance();
        return new StringBuilder()
                .append(Utility.appendIndentation(indentationCounter))
                .append(Utility.appendModifiers(input.getModifiers()))
                .append(Codestyle.space())
                .append(bootstrap.translate(input.getType(), 0))
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
