package translator.csharp;

import data.Constructor;
import data.Modifier;
import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

final class ConstructorTranslator implements ComponentTranslator<Constructor> {

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
        final StringBuilder constructorBuilder = new StringBuilder()
                .append(Utility.appendIndentation(indentationCounter))
                .append(Utility.appendModifiers(translateModifiers(input.getModifiers())))
                .append(Codestyle.space())
                .append(input.getClassName())
                .append("(")
                .append(Utility.appendParameters(input.getParameters()))
                .append(")")
                .append(Codestyle.space());

        if (!input.getExplicitConstructorInvocation().getContent().isEmpty()) {
            constructorBuilder.append(":")
                    .append(Codestyle.space())
                    .append(translateSuperclassConstructorInvocation(input.getExplicitConstructorInvocation()))
                    .append(Codestyle.space());
        }

        constructorBuilder.append("{")
                .append(Codestyle.newLine())
                .append(input.getStatements()
                        .stream()
                        .map(s -> StatementTranslator.instance().translate(s, indentationCounter + 1))
                        .collect(Collectors.joining(Codestyle.newLine())))
                .append(Codestyle.newLine())
                .append(Utility.appendIndentation(indentationCounter))
                .append("}");

        return constructorBuilder.toString();
    }

    private String translateSuperclassConstructorInvocation(@Nonnull final Statement.StatementExpression statementExpression) {
        return statementExpression.getContent().replace("super", "base");
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
