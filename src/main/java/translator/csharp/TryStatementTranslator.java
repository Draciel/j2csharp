package translator.csharp;

import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

final class TryStatementTranslator implements ComponentTranslator<Statement.TryStatement> {

    private TryStatementTranslator() {
        //no instance
    }

    public static TryStatementTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.TryStatement input, final int indentationCounter) {
        final BlockTranslator blockTranslator = BlockTranslator.instance();
        final CatchClauseTranslator catchClauseTranslator = CatchClauseTranslator.instance();
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(Utility.appendIndentation(indentationCounter))
                .append("try")
                .append("{")
                .append(Codestyle.newLine())
                .append(blockTranslator.translate(input.getTryBlock(), indentationCounter))
                .append("}");

        if (!input.getCatchClauseStatements().isEmpty()) {
            input.getCatchClauseStatements()
                    .forEach(ccs -> stringBuilder.append(catchClauseTranslator.translate(ccs, indentationCounter)));
        }

        if (input.getFinallyBlock() != null) {
            stringBuilder.append(Codestyle.space())
                    .append("finally")
                    .append(Codestyle.space())
                    .append("{")
                    .append(Codestyle.newLine())
                    .append(blockTranslator.translate(input.getFinallyBlock(), indentationCounter))
                    .append("}");
        }

        return stringBuilder.toString();
    }

    private static final class Holder {
        private static final TryStatementTranslator INSTANCE = new TryStatementTranslator();
    }
}
