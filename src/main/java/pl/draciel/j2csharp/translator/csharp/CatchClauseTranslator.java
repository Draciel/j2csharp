package pl.draciel.j2csharp.translator.csharp;

import pl.draciel.j2csharp.data.CatchClauseStatement;
import pl.draciel.j2csharp.translator.ComponentTranslator;
import pl.draciel.j2csharp.utility.Nonnull;

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

        final String translatedBlock = blockTranslator.translate(input.getBlock(), indentationCounter);

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
                        .append(")")
                        .append(Codestyle.space())
                        .append("{")
                        .append(Codestyle.newLine())
                        .append(translatedBlock)
                        .append(Codestyle.newLine())
                        .append(Utility.appendIndentation(indentationCounter))
                        .append("}"));

        return catchClauseBuilder.toString();
    }

    private static final class Holder {
        private static final CatchClauseTranslator INSTANCE = new CatchClauseTranslator();
    }

}
