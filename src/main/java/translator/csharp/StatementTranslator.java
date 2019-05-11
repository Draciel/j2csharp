package translator.csharp;

import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

final class StatementTranslator implements ComponentTranslator<Statement> {

    private StatementTranslator() {
        //no instance
    }

    public static StatementTranslator instance() {
        return Holder.INSTANCE;
    }

    // just emulating kotlin sealed class :(
    @Nonnull
    @Override
    public String translate(@Nonnull final Statement input, final int indentationCounter) {
        switch (input.getType()) {

            case SYNCHRONIZED:
                return SynchronizedStatementTranslator.instance().translate((Statement.SynchronizedStatement) input
                        , indentationCounter);

            case RETURN:
                return ReturnStatementTranslator.instance().translate((Statement.ReturnStatement) input,
                        indentationCounter);

            case BLOCK:
                return BlockTranslator.instance().translate((Statement.Block) input, indentationCounter);

            case ASSERT:
                return AssertStatementTranslator.instance().translate((Statement.AssertStatement) input,
                        indentationCounter);

            case BREAK:
                return BreakStatementTranslator.instance().translate((Statement.BreakStatement) input,
                        indentationCounter);

            case CONTINUE:
                return ContinueStatementTranslator.instance().translate((Statement.ContinueStatement) input,
                        indentationCounter);

            case DO:
                return DoStatementTranslator.instance().translate((Statement.DoStatement) input,
                        indentationCounter);

            case SWITCH:
                return SwitchStatementTranslator.instance().translate((Statement.SwitchStatement) input,
                        indentationCounter);

            case EMPTY:
                return Codestyle.newLine();

            case THROW:
                return ThrowStatementTranslator.instance().translate((Statement.ThrowStatement) input,
                        indentationCounter);
            case TRY:
                return TryStatementTranslator.instance().translate((Statement.TryStatement) input,
                        indentationCounter);

            case TRY_WITH_RESOURCES:
                return TryWithResourcesTranslator.instance().translate((Statement.TryWithResourcesStatement) input,
                        indentationCounter);

            case STATEMENT_EXPRESSION:
                return StatementExpressionTranslator.instance().translate((Statement.StatementExpression) input,
                        indentationCounter);

            case IF:
                return IfStatementTranslator.instance().translate((Statement.IfStatement) input,
                        indentationCounter);

            case WHILE:
                return WhileStatementTranslator.instance().translate((Statement.WhileStatement) input,
                        indentationCounter);

            case BASIC_FOR:
                return BasicForTranslator.instance().translate((Statement.BasicForStatement) input,
                        indentationCounter);

            case ENHANCED_FOR:
                return EnhancedForTranslator.instance().translate((Statement.EnhancedForStatement) input,
                        indentationCounter);

            default:
                throw new IllegalArgumentException("Unsupported statement input: " + input.getType());
        }
    }

    private static final class Holder {
        private static final StatementTranslator INSTANCE = new StatementTranslator();
    }
}
