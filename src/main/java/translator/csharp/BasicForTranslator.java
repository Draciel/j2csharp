package translator.csharp;

import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

final class BasicForTranslator implements ComponentTranslator<Statement.BasicForStatement> {

    private BasicForTranslator() {
        //no instance
    }

    public static BasicForTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.BasicForStatement input, final int indentationCounter) {
        final StringBuilder forLoopBuilder = new StringBuilder();
        final StatementExpressionTranslator statementExpressionTranslator = StatementExpressionTranslator.instance();
        final FieldTranslator fieldTranslator = FieldTranslator.instance();
        final StatementTranslator statementTranslator = StatementTranslator.instance();

        forLoopBuilder.append(Utility.appendIndentation(indentationCounter))
                .append("for")
                .append(Codestyle.space())
                .append("(")
                .append(fieldTranslator.translate(input.getInitVariable(), 0))
                .append(";")
                .append(Codestyle.space())
                .append(statementExpressionTranslator.translate(input.getExpression(), 0))
                .append(";")
                .append(Codestyle.space())
                .append(statementExpressionTranslator.translate(input.getUpdateStatement(), 0))
                .append(")")
                .append(Codestyle.space())
                .append("{")
                .append(Codestyle.newLine())
                .append(statementTranslator.translate(input.getBodyStatement(), indentationCounter + 1))
                .append(Codestyle.newLine())
                .append(Utility.appendIndentation(indentationCounter))
                .append("}");

        return forLoopBuilder.toString();

    }

    private static final class Holder {
        private static final BasicForTranslator INSTANCE = new BasicForTranslator();
    }

}
