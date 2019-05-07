package translator.csharp;

import data.Annotation;
import data.Method;
import data.Modifier;
import translator.ComponentTranslator;
import utility.Nonnull;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class MethodTranslator implements ComponentTranslator<Method> {

    private static Function<Modifier, Modifier> MODIFIER_TRANSFORMER = modifier -> {
        switch (modifier) {
            case PACKAGE:
                return Modifier.INTERNAL;

            default:
                return modifier;
        }
    };

    private MethodTranslator() {
        //no instance
    }

    public static MethodTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Method input, int indentationCounter) {
        return new StringBuilder()
                .append(Utility.appendIndentation(indentationCounter))
                .append(Utility.appendModifiers(translateModifiers(input.getModifiers(), input.getAnnotations())))
                .append(Codestyle.space())
                .append(input.getResult())
                .append(Codestyle.space())
                .append(input.getName())
                .append("(")
                .append(Utility.appendParameters(input.getParameters()))
                .append(")")
                .append(Codestyle.space())
                .append("{")
                .append(Codestyle.newLine())
                .append(Utility.appendStatements(input.getStatements(), indentationCounter + 1))
                .append(Codestyle.newLine())
                .append(Utility.appendIndentation(indentationCounter))
                .append("}")
                .toString();
    }

    private static List<Modifier> translateModifiers(@Nonnull final List<Modifier> modifiers,
                                                     @Nonnull final List<Annotation> annotations) {

        final List<Modifier> filteredModifiers = modifiers.stream()
                .filter(MethodTranslator::isAvailableModifier)
                .collect(Collectors.toList());

        final boolean hasOverrideAnnotation = annotations.stream()
                .anyMatch(a -> a.getName().equals("@Override"));

        if (hasOverrideAnnotation) {
            filteredModifiers.add(Modifier.OVERRIDE);
        }

        return filteredModifiers.stream()
                .map(MODIFIER_TRANSFORMER)
                .collect(Collectors.toList());
    }

    private static boolean isAvailableModifier(@Nonnull final Modifier modifier) {
        return modifier == Modifier.PUBLIC || modifier == Modifier.PRIVATE || modifier == Modifier.PROTECTED ||
                modifier == Modifier.STATIC || modifier == Modifier.PACKAGE || modifier == Modifier.ABSTRACT;
    }

    private static final class Holder {
        private static final MethodTranslator INSTANCE = new MethodTranslator();
    }
}
