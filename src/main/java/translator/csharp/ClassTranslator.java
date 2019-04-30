package translator.csharp;

import data.Class;
import translator.PartialTranslator;
import utility.Nonnull;

class ClassTranslator implements PartialTranslator<Class> {

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

        //todo handle annotations;
        final StringBuilder builder = new StringBuilder();
        final int indentation = indentationCounter + 1;

        builder.append(Utility.appendModifiers(input.getModifiers()))
                .append(Codestyle.space())
                .append("class")
                .append(Codestyle.space()) // fixme handle interfaces and enums
                .append(input.getName())
                .append(Codestyle.space())
                .append("{")
                .append(Codestyle.newLine());

        input.getFields().stream()
                .map(f -> fieldTranslator.translate(f, indentation))
                .forEach(f -> builder.append(Codestyle.newLine())
                        .append(f)
                        .append(Codestyle.newLine()));

        input.getConstructors().stream()
                .map(c -> constructorTranslator.translate(c, indentation))
                .forEach(c -> builder.append(Codestyle.newLine())
                        .append(c)
                        .append(Codestyle.newLine()));

        input.getMethods().stream()
                .map(c -> methodTranslator.translate(c, indentation))
                .forEach(c -> builder.append(Codestyle.newLine())
                        .append(c)
                        .append(Codestyle.newLine()));

        builder.append("}");

        return builder.toString();
    }

    private static final class Holder {
        private static final ClassTranslator INSTANCE = new ClassTranslator();
    }


}

