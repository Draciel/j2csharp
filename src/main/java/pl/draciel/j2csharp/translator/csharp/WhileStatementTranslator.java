package pl.draciel.j2csharp.translator.csharp;

import pl.draciel.j2csharp.data.Statement;
import pl.draciel.j2csharp.translator.ComponentTranslator;
import pl.draciel.j2csharp.utility.Nonnull;

final class WhileStatementTranslator implements ComponentTranslator<Statement.WhileStatement> {

    private WhileStatementTranslator() {
        //no instance
    }

    public static WhileStatementTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.WhileStatement input, final int indentationCounter) {
        final StatementExpressionTranslator statementExpressionTranslator = StatementExpressionTranslator.instance();
        final StatementTranslator statementTranslator = StatementTranslator.instance();
        final StringBuilder whileStatementBuilder = new StringBuilder();

        whileStatementBuilder
                .append(Utility.appendIndentation(indentationCounter))
                .append("while")
                .append("(")
                .append(statementExpressionTranslator.translate(input.getCondition(), 0))
                .append(")")
                .append(Codestyle.space())
                .append("{")
                .append(Codestyle.newLine())
                .append(statementTranslator.translate(input.getWhileBody(), indentationCounter))
                .append(Codestyle.newLine())
                .append(Utility.appendIndentation(indentationCounter))
                .append("}");

        return whileStatementBuilder.toString();
    }

    private static final class Holder {
        private static final WhileStatementTranslator INSTANCE = new WhileStatementTranslator();
    }
}
