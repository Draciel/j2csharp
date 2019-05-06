package translator.csharp;

import data.Interface;
import translator.PartialTranslator;
import utility.Nonnull;

class InterfaceTranslator implements PartialTranslator<Interface> {

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
                .append(Utility.appendModifiers(input.getModifiers()))
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


    private static final class Holder {
        private static final InterfaceTranslator INSTANCE = new InterfaceTranslator();
    }
}
