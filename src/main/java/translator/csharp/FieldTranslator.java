package translator.csharp;

import data.Field;
import data.Modifier;
import translator.ComponentTranslator;
import utility.Nonnull;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class FieldTranslator implements ComponentTranslator<Field> {

    private static Function<Modifier, Modifier> MODIFIER_TRANSFORMER = modifier -> {
        switch (modifier) {
            case PACKAGE:
                return Modifier.INTERNAL;

            case FINAL:
                return Modifier.READONLY;

            default:
                return modifier;
        }
    };

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
                .append(Utility.appendModifiers(translateModifiers(input.getModifiers())))
                .append(Codestyle.space())
                .append(bootstrap.translate(input.getType(), 0))
                .append(Codestyle.space())
                .append(input.getName())
                .append(Utility.appendInitializer(input.getInitializer()))
                .append(";")
                .toString();
    }

    private List<Modifier> translateModifiers(@Nonnull final List<Modifier> modifiers) {
        final List<Modifier> filteredModifiers = modifiers.stream()
                .filter(FieldTranslator::isAvailableModifier)
                .collect(Collectors.toList());

        // exclude special cases
        if (filteredModifiers.contains(Modifier.STATIC) && filteredModifiers.contains(Modifier.FINAL)) {
            final int pos = filteredModifiers.indexOf(Modifier.STATIC);
            filteredModifiers.set(pos, Modifier.CONST);
            filteredModifiers.remove(Modifier.FINAL);
        }

        return filteredModifiers.stream()
                .map(MODIFIER_TRANSFORMER)
                .collect(Collectors.toList());
    }

    private static boolean isAvailableModifier(@Nonnull final Modifier modifier) {
        return modifier == Modifier.PUBLIC || modifier == Modifier.PRIVATE || modifier == Modifier.PROTECTED ||
                modifier == Modifier.STATIC || modifier == Modifier.VOLATILE || modifier == Modifier.PACKAGE ||
                modifier == Modifier.FINAL || modifier == Modifier.INTERNAL;
    }

    private static class Holder {
        private static final FieldTranslator INSTANCE = new FieldTranslator();
    }
}
