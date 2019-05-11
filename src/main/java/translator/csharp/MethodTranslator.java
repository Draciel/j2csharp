package translator.csharp;

import data.Annotation;
import data.Method;
import data.Modifier;
import translator.ComponentTranslator;
import utility.Nonnull;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

final class MethodTranslator implements ComponentTranslator<Method> {

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
        final StringBuilder stringBuilder = new StringBuilder();
        final StatementTranslator statementTranslator = StatementTranslator.instance();
        stringBuilder
                .append(Utility.appendIndentation(indentationCounter))
                .append(Utility.appendModifiers(translateModifiers(input.isDeclaration() ? Collections.emptyList() :
                        input.getModifiers(), input.getAnnotations())))
                .append(Codestyle.space())
                .append(TypeBootstrap.instance().translate(input.getResult(), 0))
                .append(Codestyle.space())
                .append(input.getName())
                .append("(")
                .append(Utility.appendParameters(input.getParameters()))
                .append(")");

        if (input.isDeclaration()) {
            stringBuilder.append(";");
        } else {
            stringBuilder.append(Codestyle.space())
                    .append("{")
                    .append(Codestyle.newLine())
                    .append(statementTranslator.translate(input.getBlock(), indentationCounter))
                    .append(Codestyle.newLine())
                    .append(Utility.appendIndentation(indentationCounter))
                    .append("}");
        }

        return stringBuilder.toString();
    }

    private static List<Modifier> translateModifiers(@Nonnull final List<Modifier> modifiers,
                                                     @Nonnull final List<Annotation> annotations) {

        final List<Modifier> filteredModifiers = modifiers.stream()
                .filter(MethodTranslator::isAvailableModifier)
                .collect(Collectors.toList());

//        final boolean hasOverrideAnnotation = annotations.stream()
//                .anyMatch(a -> a.getName().equals("@Override"));
//
//        if (hasOverrideAnnotation) {
//            filteredModifiers.add(Modifier.OVERRIDE);
//        }

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
