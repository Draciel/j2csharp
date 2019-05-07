package translator.csharp;

import data.Method;
import translator.ComponentTranslator;
import utility.Nonnull;

class MethodTranslator implements ComponentTranslator<Method> {

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
                .append(Utility.appendModifiers(input.getModifiers()))
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
                .append(Utility.appendStatements(input.getStatements(), indentationCounter + 1)) //todo maybe use statement translator
                .append(Codestyle.newLine())
                .append(Utility.appendIndentation(indentationCounter))
                .append("}")
                .toString();
    }

    private static final class Holder {
        private static final MethodTranslator INSTANCE = new MethodTranslator();
    }
}
