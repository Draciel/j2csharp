package translator.csharp;

import data.CatchClauseStatement;
import translator.ComponentTranslator;
import utility.Nonnull;

final class CatchClauseTranslator implements ComponentTranslator<CatchClauseStatement> {

    private CatchClauseTranslator() {
        //no instance
    }

    public static CatchClauseTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final CatchClauseStatement input, final int indentationCounter) {
        // there is no bitwise OR in catch clause in c#
        final StringBuilder catchClauseBuilder = new StringBuilder();
        final BlockTranslator blockTranslator = BlockTranslator.instance();

        final String translatedBlock = blockTranslator.translate(input.getBlock(), indentationCounter + 1);

        input.getTypes()
                .stream()
                .map(t -> TypeBootstrap.instance().translate(t, 0))
                .forEach(t -> catchClauseBuilder.append(Codestyle.space())
                        .append("catch")
                        .append(Codestyle.space())
                        .append("(")
                        .append(t)
                        .append(Codestyle.space())
                        .append(input.getVariableName())
                        .append(Codestyle.space())
                        .append("{")
                        .append(Codestyle.newLine())
                        .append(translatedBlock)
                        .append(Codestyle.newLine())
                        .append("}"));

        return catchClauseBuilder.toString();
    }

    private static final class Holder {
        private static final CatchClauseTranslator INSTANCE = new CatchClauseTranslator();
    }

}
