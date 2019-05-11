package translator.csharp;

import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

final class EnhancedForTranslator implements ComponentTranslator<Statement.EnhancedForStatement> {

    private EnhancedForTranslator() {
        //no instance
    }

    public static EnhancedForTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.EnhancedForStatement input, final int indentationCounter) {
        final StringBuilder forLoopBuilder = new StringBuilder();
        final StatementExpressionTranslator statementExpressionTranslator = StatementExpressionTranslator.instance();
        final FieldTranslator fieldTranslator = FieldTranslator.instance();
        final StatementTranslator statementTranslator = StatementTranslator.instance();

        forLoopBuilder.append(Utility.appendIndentation(indentationCounter))
                .append("foreach")
                .append(Codestyle.space())
                .append("(")
                .append(fieldTranslator.translate(input.getVariable(), 0))
                .append(Codestyle.space())
                .append("in")
                .append(Codestyle.space())
                .append(statementExpressionTranslator.translate(input.getCollection(), 0))
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
        private static final EnhancedForTranslator INSTANCE = new EnhancedForTranslator();
    }
}
