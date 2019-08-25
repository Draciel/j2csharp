package pl.draciel.j2csharp.translator.csharp;

import pl.draciel.j2csharp.data.Field;
import pl.draciel.j2csharp.data.Modifier;
import pl.draciel.j2csharp.translator.ComponentTranslator;
import pl.draciel.j2csharp.utility.Nonnull;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

final class FieldTranslator implements ComponentTranslator<Field> {

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
        final StringBuilder fieldBuilder = new StringBuilder();

        fieldBuilder.append(Utility.appendIndentation(indentationCounter))
                .append(Utility.appendModifiers(translateModifiers(input.getModifiers())));

        if (!input.getModifiers().isEmpty()) {
            fieldBuilder.append(Codestyle.space());
        }

        fieldBuilder.append(bootstrap.translate(input.getType(), 0))
                .append(Codestyle.space())
                .append(input.getName())
                .append(Utility.appendInitializer(input.getInitializer()));

        return fieldBuilder.toString();
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
