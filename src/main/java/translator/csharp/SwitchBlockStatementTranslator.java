package translator.csharp;

import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

import java.util.stream.Collectors;

final class SwitchBlockStatementTranslator implements ComponentTranslator<Statement.SwitchBlockStatementGroup> {

    private SwitchBlockStatementTranslator() {
        //no instance
    }

    public static SwitchBlockStatementTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.SwitchBlockStatementGroup input, final int indentationCounter) {
        final StringBuilder caseBuilder = new StringBuilder();

        final String formattedLabels = input.getLabels()
                .stream()
                .map(l -> l.equals("default") ? "default:" : "case" + l + ":")
                .map(l -> Utility.appendIndentation(indentationCounter) + l)
                .collect(Collectors.joining(Codestyle.newLine()));

        caseBuilder.append(formattedLabels)
                .append(Codestyle.newLine())
                .append(input.getBlockStatement().stream()
                        .map(bs -> BlockTranslator.translateBlockStatement(bs, indentationCounter + 1))
                        .collect(Collectors.joining(Codestyle.newLine())));

        return caseBuilder.toString();
    }

    private static final class Holder {
        private static final SwitchBlockStatementTranslator INSTANCE = new SwitchBlockStatementTranslator();
    }
}
