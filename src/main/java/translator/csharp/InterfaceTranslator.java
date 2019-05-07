package translator.csharp;

import data.Interface;
import data.Modifier;
import translator.ComponentTranslator;
import utility.Nonnull;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class InterfaceTranslator implements ComponentTranslator<Interface> {

    private static Function<Modifier, Modifier> MODIFIER_TRANSFORMER = modifier -> {
        switch (modifier) {
            case PACKAGE:
                return Modifier.INTERNAL;

            default:
                return modifier;
        }
    };

    private InterfaceTranslator() {

    }

    public static InterfaceTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Interface input, final int indentationCounter) {
        final FieldTranslator fieldTranslator = FieldTranslator.instance();
        final MethodTranslator methodTranslator = MethodTranslator.instance();
        final ClassTranslator classTranslator = ClassTranslator.instance();
        final EnumTranslator enumTranslator = EnumTranslator.instance();

        //todo handle annotations;
        final StringBuilder builder = new StringBuilder();
        final int indentationForNested = indentationCounter + 1;

        builder.append(Utility.appendIndentation(indentationCounter))
                .append(Utility.appendModifiers(translateModifiers(input.getModifiers())))
                .append(Codestyle.space())
                .append("interface")
                .append(Codestyle.space())
                .append(input.getName())
                .append(Utility.appendGenericsParameters(input.getGenerics()))
                .append(Utility.appendInheritance("", input.getSuperInterfaces()))
                .append(Utility.appendGenericsBounds(input.getGenerics(), indentationForNested))
                .append(Codestyle.space())
                .append("{")
                .append(Codestyle.newLine());

        input.getFields().stream()
                .map(f -> fieldTranslator.translate(f, indentationForNested))
                .forEach(f -> builder.append(Codestyle.newLine())
                        .append(f)
                        .append(Codestyle.newLine()));

        input.getMethods().stream()
                .map(c -> methodTranslator.translate(c, indentationForNested))
                .forEach(c -> builder.append(Codestyle.newLine())
                        .append(c)
                        .append(Codestyle.newLine()));

        input.getClasses().stream()
                .map(c -> classTranslator.translate(c, indentationForNested))
                .forEach(c -> builder.append(Codestyle.newLine())
                        .append(c)
                        .append(Codestyle.newLine()));

        input.getInterfaces().stream()
                .map(c -> translate(c, indentationForNested))
                .forEach(c -> builder.append(Codestyle.newLine())
                        .append(c)
                        .append(Codestyle.newLine()));

        input.getEnums().stream()
                .map(c -> enumTranslator.translate(c, indentationForNested))
                .forEach(c -> builder.append(Codestyle.newLine())
                        .append(c)
                        .append(Codestyle.newLine()));

        builder.append(Codestyle.newLine())
                .append(Utility.appendIndentation(indentationCounter))
                .append("}");

        return builder.toString();
    }

    private static List<Modifier> translateModifiers(@Nonnull final List<Modifier> modifiers) {
        return modifiers.stream()
                .filter(InterfaceTranslator::isAvailableModifier)
                .map(MODIFIER_TRANSFORMER)
                .collect(Collectors.toList());
    }

    private static boolean isAvailableModifier(@Nonnull final Modifier modifier) {
        return modifier == Modifier.PUBLIC || modifier == Modifier.PRIVATE || modifier == Modifier.PROTECTED ||
                modifier == Modifier.STATIC || modifier == Modifier.PACKAGE;
    }

    private static final class Holder {
        private static final InterfaceTranslator INSTANCE = new InterfaceTranslator();
    }
}
