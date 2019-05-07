package translator.csharp;

import data.Constructor;
import data.Modifier;
import translator.ComponentTranslator;
import utility.Nonnull;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class ConstructorTranslator implements ComponentTranslator<Constructor> {

    private static Function<Modifier, Modifier> MODIFIER_TRANSFORMER = modifier -> {
        switch (modifier) {
            case PACKAGE:
                return Modifier.INTERNAL;

            default:
                return modifier;
        }
    };

    private ConstructorTranslator() {
        //no instance
    }

    public static ConstructorTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Constructor input, final int indentationCounter) {
        return new StringBuilder()
                .append(Utility.appendIndentation(indentationCounter))
                .append(Utility.appendModifiers(translateModifiers(input.getModifiers())))
                .append(Codestyle.space())
                .append(input.getClassName())
                .append("(")
                .append(Utility.appendParameters(input.getParameters()))
                .append(")")
                .append(Codestyle.space())
                .append("{")
                .append(Codestyle.newLine())
                .append(Utility.appendStatements(input.getStatements(), indentationCounter + 1)) //todo maybe use statement translator
                .append(Codestyle.newLine())
                .append(Utility.appendIndentation(indentationCounter))
                .append("}")
                .toString();
    }

    private static List<Modifier> translateModifiers(@Nonnull final List<Modifier> modifiers) {
        return modifiers.stream()
                .filter(ConstructorTranslator::isAvailableModifier)
                .map(MODIFIER_TRANSFORMER)
                .collect(Collectors.toList());
    }

    private static boolean isAvailableModifier(@Nonnull final Modifier modifier) {
        return modifier == Modifier.PUBLIC || modifier == Modifier.PRIVATE || modifier == Modifier.PROTECTED ||
                modifier == Modifier.PACKAGE;
    }

    private static final class Holder {
        private static final ConstructorTranslator INSTANCE = new ConstructorTranslator();
    }
}
