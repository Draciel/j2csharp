package pl.draciel.j2csharp.translator.csharp;

import pl.draciel.j2csharp.data.Statement;
import pl.draciel.j2csharp.translator.ComponentTranslator;
import pl.draciel.j2csharp.utility.Nonnull;

import java.util.stream.Collectors;

final class SwitchStatementTranslator implements ComponentTranslator<Statement.SwitchStatement> {

    private SwitchStatementTranslator() {
        //no instance
    }

    public static SwitchStatementTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.SwitchStatement input, final int indentationCounter) {
        final StatementExpressionTranslator statementExpressionTranslator = StatementExpressionTranslator.instance();
        final StringBuilder whileStatementBuilder = new StringBuilder();
        final SwitchBlockStatementTranslator switchBlockStatementTranslator = SwitchBlockStatementTranslator.instance();

        whileStatementBuilder
                .append(Utility.appendIndentation(indentationCounter))
                .append("switch")
                .append("(")
                .append(statementExpressionTranslator.translate(input.getExpression(), 0))
                .append(")")
                .append(Codestyle.space())
                .append("{")
                .append(Codestyle.newLine())
                .append(input.getSwitchBlockStatements()
                        .stream()
                        .map(sbsg -> switchBlockStatementTranslator.translate(sbsg, indentationCounter + 1))
                        .collect(Collectors.joining(Codestyle.newLine())))
                .append(Codestyle.newLine())
                .append(Utility.appendIndentation(indentationCounter))
                .append("}");

        return whileStatementBuilder.toString();
    }

    private static final class Holder {
        private static final SwitchStatementTranslator INSTANCE = new SwitchStatementTranslator();
    }
}
