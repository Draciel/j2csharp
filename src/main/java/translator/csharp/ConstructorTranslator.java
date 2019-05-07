package translator.csharp;

import data.Constructor;
import translator.ComponentTranslator;
import utility.Nonnull;

class ConstructorTranslator implements ComponentTranslator<Constructor> {

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
                .append(Utility.appendModifiers(input.getModifiers()))
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

    private static final class Holder {
        private static final ConstructorTranslator INSTANCE = new ConstructorTranslator();
    }
}
