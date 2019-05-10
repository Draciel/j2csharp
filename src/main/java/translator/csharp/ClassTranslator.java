package translator.csharp;

import data.Class;
import data.Modifier;
import translator.ComponentTranslator;
import utility.Nonnull;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

final class ClassTranslator implements ComponentTranslator<Class> {

    private static Function<Modifier, Modifier> MODIFIER_TRANSFORMER = modifier -> {
        switch (modifier) {
            case PACKAGE:
                return Modifier.INTERNAL;

            default:
                return modifier;
        }
    };

    private ClassTranslator() {

    }

    public static ClassTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Class input, final int indentationCounter) {
        final FieldTranslator fieldTranslator = FieldTranslator.instance();
        final ConstructorTranslator constructorTranslator = ConstructorTranslator.instance();
        final MethodTranslator methodTranslator = MethodTranslator.instance();
        final InterfaceTranslator interfaceTranslator = InterfaceTranslator.instance();
        final EnumTranslator enumTranslator = EnumTranslator.instance();

        //todo handle annotations;
        final StringBuilder builder = new StringBuilder();
        final int indentationForNested = indentationCounter + 1;

        builder.append(Utility.appendIndentation(indentationCounter))
                .append(Utility.appendModifiers(translateModifiers(input.getModifiers())))
                .append(Codestyle.space())
                .append("class")
                .append(Codestyle.space())
                .append(input.getName())
                .append(Utility.appendGenericsParameters(input.getGenerics()))
                .append(Utility.appendInheritance(input.getSuperClass(), input.getSuperInterfaces()))
                .append(Utility.appendGenericsBounds(input.getGenerics(), indentationForNested))
                .append(Codestyle.space())
                .append("{")
                .append(Codestyle.newLine());

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
                .map(c -> translate(c, indentationForNested))
                .forEach(c -> builder.append(Codestyle.newLine())
                        .append(c)
                        .append(Codestyle.newLine()));

        input.getInterfaces().stream()
                .map(c -> interfaceTranslator.translate(c, indentationForNested))
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
                .filter(ClassTranslator::isAvailableModifier)
                .map(MODIFIER_TRANSFORMER)
                .collect(Collectors.toList());
    }

    private static boolean isAvailableModifier(@Nonnull final Modifier modifier) {
        return modifier == Modifier.PUBLIC || modifier == Modifier.PRIVATE || modifier == Modifier.PROTECTED ||
                modifier == Modifier.PACKAGE || modifier == Modifier.ABSTRACT;
    }

    private static final class Holder {
        private static final ClassTranslator INSTANCE = new ClassTranslator();
    }


}

