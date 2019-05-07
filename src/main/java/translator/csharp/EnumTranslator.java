package translator.csharp;

import data.Enum;
import data.Modifier;
import translator.ComponentTranslator;
import utility.Nonnull;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class EnumTranslator implements ComponentTranslator<Enum> {

    private static Function<Modifier, Modifier> MODIFIER_TRANSFORMER = modifier -> {
        switch (modifier) {
            case PACKAGE:
                return Modifier.INTERNAL;

            default:
                return modifier;
        }
    };

    private EnumTranslator() {
        //no instance
    }

    public static EnumTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Enum input, final int indentationCounter) {
        final FieldTranslator fieldTranslator = FieldTranslator.instance();
        final ConstructorTranslator constructorTranslator = ConstructorTranslator.instance();
        final MethodTranslator methodTranslator = MethodTranslator.instance();
        final InterfaceTranslator interfaceTranslator = InterfaceTranslator.instance();
        final ClassTranslator classTranslator = ClassTranslator.instance();

        //todo handle annotations;
        final StringBuilder builder = new StringBuilder();
        final int indentationForNested = indentationCounter + 1;

        builder.append(Utility.appendIndentation(indentationCounter))
                .append(Utility.appendModifiers(translateModifiers(input.getModifiers())))
                .append(Codestyle.space())
                .append("enum")
                .append(Codestyle.space())
                .append(input.getName())
                .append(Utility.appendInheritance("", input.getSuperInterfaces()))
                .append("{")
                .append(Codestyle.newLine())
                .append(Codestyle.newLine())
                .append(Utility.appendIndentation(indentationForNested))
                .append(input.getEnumConstants().stream()
                        .map(ec -> ec.getPassedParameters().isEmpty() ? ec.getContantName() :
                                ec.getContantName() + "(" + Utility.appendPassedParameters(ec.getPassedParameters()) + ")")
                        .collect(Collectors.joining("," + Codestyle.space())))
                .append(";")
                .append(Codestyle.newLine());

        input.getFields().stream()
                .map(f -> fieldTranslator.translate(f, indentationForNested))
                .forEach(f -> builder.append(Codestyle.newLine())
                        .append(f)
                        .append(Codestyle.newLine()));

        input.getConstructors().stream()
                .map(c -> constructorTranslator.translate(c, indentationForNested))
                .forEach(c -> builder.append(Codestyle.newLine())
                        .append(c)
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
                .map(c -> interfaceTranslator.translate(c, indentationForNested))
                .forEach(c -> builder.append(Codestyle.newLine())
                        .append(c)
                        .append(Codestyle.newLine()));

        input.getEnums().stream()
                .map(c -> translate(c, indentationForNested))
                .forEach(c -> builder.append(Codestyle.newLine())
                        .append(c)
                        .append(Codestyle.newLine()));

        builder.append(Codestyle.newLine())
                .append(Utility.appendIndentation(indentationCounter))
                .append("}");

        return builder.toString();
    }

    private static boolean isAvailableModifier(@Nonnull final Modifier modifier) {
        return modifier == Modifier.PUBLIC || modifier == Modifier.PRIVATE || modifier == Modifier.PROTECTED ||
                modifier == Modifier.PACKAGE;
    }

    private static List<Modifier> translateModifiers(@Nonnull final List<Modifier> modifiers) {
        return modifiers.stream()
                .filter(EnumTranslator::isAvailableModifier)
                .map(MODIFIER_TRANSFORMER)
                .collect(Collectors.toList());
    }

    private static final class Holder {
        private static final EnumTranslator INSTANCE = new EnumTranslator();
    }

}
