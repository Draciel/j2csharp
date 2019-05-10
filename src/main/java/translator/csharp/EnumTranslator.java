package translator.csharp;

import data.Enum;
import data.EnumConstant;
import data.Modifier;
import translator.ComponentTranslator;
import utility.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

final class EnumTranslator implements ComponentTranslator<Enum> {

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

        if (isComplexEnum(input)) {
            final Modifier accessModifier = input.getModifiers().stream().filter(Modifier::isAccessModifier)
                    .map(MODIFIER_TRANSFORMER)
                    .findFirst().get();

            final List<Modifier> enumConstantModifiers = new ArrayList<>();
            enumConstantModifiers.add(accessModifier);
            enumConstantModifiers.add(Modifier.STATIC);
            enumConstantModifiers.add(Modifier.READONLY);

            builder.append(Utility.appendIndentation(indentationCounter))
                    .append(Utility.appendModifiers(translateModifiers(input.getModifiers())))
                    .append(Codestyle.space())
                    .append("class")
                    .append(Codestyle.space())
                    .append(input.getName())
                    .append(Utility.appendInheritance("", input.getSuperInterfaces()))
                    .append("{")
                    .append(Codestyle.newLine())
                    .append(Codestyle.newLine())
                    .append(input.getEnumConstants().stream()
                            .map(ec -> translateEnumConstant(enumConstantModifiers, ec, input.getName(),
                                    indentationForNested))
                            .collect(Collectors.joining()));

            input.getFields().stream()
                    .map(f -> fieldTranslator.translate(f, indentationForNested))
                    .forEach(f -> builder.append(Codestyle.newLine())
                            .append(f)
                            .append(";")
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

            // creating values method
            final List<Modifier> valuesMethodModifiers = new ArrayList<>();
            valuesMethodModifiers.add(accessModifier);
            valuesMethodModifiers.add(Modifier.STATIC);

            builder.append(Codestyle.newLine())
                    .append(Utility.appendIndentation(indentationForNested))
                    .append(Utility.appendModifiers(valuesMethodModifiers))
                    .append(Codestyle.space())
                    .append("IEnumerable")
                    .append("<")
                    .append(input.getName())
                    .append(">")
                    .append(Codestyle.space())
                    .append("values")
                    .append(Codestyle.space())
                    .append("{")
                    .append(Codestyle.newLine())
                    .append(Utility.appendIndentation(indentationForNested + 1))
                    .append("get")
                    .append(Codestyle.space())
                    .append("{")
                    .append(Codestyle.newLine())
                    .append(input.getEnumConstants().stream()
                            .map(ec -> appendValuesMethodRow(ec, indentationForNested + 2))
                            .collect(Collectors.joining()))
                    .append(Utility.appendIndentation(indentationForNested + 1))
                    .append("}")
                    .append(Codestyle.newLine())
                    .append(Utility.appendIndentation(indentationForNested))
                    .append("}");


            builder.append(Codestyle.newLine())
                    .append(Utility.appendIndentation(indentationCounter))
                    .append("}");
        } else {
            builder.append(Utility.appendIndentation(indentationCounter))
                    .append(Utility.appendModifiers(translateModifiers(input.getModifiers())))
                    .append(Codestyle.space())
                    .append("enum")
                    .append(Codestyle.space())
                    .append(input.getName())
                    .append(Codestyle.space())
                    .append("{")
                    .append(Codestyle.newLine())
                    .append(Codestyle.newLine())
                    .append(Utility.appendIndentation(indentationForNested))
                    .append(input.getEnumConstants().stream()
                            .map(EnumConstant::getContantName)
                            .collect(Collectors.joining("," + Codestyle.space())))
                    .append(Utility.appendIndentation(indentationCounter))
                    .append("}")
                    .append(Codestyle.newLine());
        }

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

    private static String translateEnumConstant(@Nonnull final List<Modifier> modifiers,
                                                @Nonnull final EnumConstant constant,
                                                @Nonnull final String enumName,
                                                final int indentation) {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(Utility.appendIndentation(indentation))
                .append(Utility.appendModifiers(modifiers))
                .append(Codestyle.space())
                .append(enumName)
                .append(Codestyle.space())
                .append(constant.getContantName())
                .append(Codestyle.space())
                .append("=")
                .append(Codestyle.space())
                .append("new")
                .append(Codestyle.space())
                .append(enumName)
                .append("(")
                .append(Utility.appendPassedParameters(constant.getPassedParameters()))
                .append(")")
                .append(";")
                .append(Codestyle.newLine());

        return stringBuilder.toString();
    }

    private static String appendValuesMethodRow(@Nonnull final EnumConstant ec, final int indentation) {
        return new StringBuilder().append(Utility.appendIndentation(indentation))
                .append("yield")
                .append(Codestyle.space())
                .append("return")
                .append(Codestyle.space())
                .append(ec.getContantName())
                .append(";")
                .append(Codestyle.newLine())
                .toString();
    }

    private static boolean isComplexEnum(Enum enumm) {
        return !enumm.getConstructors().isEmpty() || !enumm.getMethods().isEmpty();
    }

    private static final class Holder {
        private static final EnumTranslator INSTANCE = new EnumTranslator();
    }

}
