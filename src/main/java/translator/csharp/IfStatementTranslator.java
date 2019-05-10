package translator.csharp;

import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

final class IfStatementTranslator implements ComponentTranslator<Statement.IfStatement> {

    private IfStatementTranslator() {
        //no instance
    }

    public static IfStatementTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.IfStatement input, final int indentationCounter) {
        final StringBuilder ifStatementBuilder = new StringBuilder();
        final StatementTranslator statementTranslator = StatementTranslator.instance();

        ifStatementBuilder.append(Utility.appendIndentation(indentationCounter))
                .append("if")
                .append(Codestyle.space())
                .append("(")
                .append(StatementExpressionTranslator.instance().translate(input.getConditional(), 0))
                .append(")")
                .append(Codestyle.space())
                .append("{")
                .append(Codestyle.newLine())
                .append(statementTranslator.translate(input.getIfBodyStatement(), indentationCounter + 1));

        if (input.getElseBodyStatement() != null) {
            ifStatementBuilder.append(Codestyle.newLine())
                    .append(Utility.appendIndentation(indentationCounter))
                    .append("}")
                    .append(Codestyle.space())
                    .append("else")
                    .append(Codestyle.space());

            if (input.getElseBodyStatement().getType() == Statement.ComplexStatementType.IF) {
                ifStatementBuilder.append(statementTranslator.translate(input.getElseBodyStatement(),
                        indentationCounter));
            } else {
                ifStatementBuilder.append("{")
                        .append(Codestyle.newLine())
                        .append(statementTranslator.translate(input.getElseBodyStatement(), indentationCounter + 1))
                        .append(Codestyle.newLine())
                        .append(Utility.appendIndentation(indentationCounter))
                        .append("}");
            }
        } else {
            ifStatementBuilder.append(Codestyle.newLine())
                    .append(Utility.appendIndentation(indentationCounter))
                    .append("}");
        }

        return ifStatementBuilder.toString();
    }

    private static final class Holder {
        private static final IfStatementTranslator INSTANCE = new IfStatementTranslator();
    }
}
