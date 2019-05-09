package visitor;

import data.statements.StatementWithoutTrailingSubstatement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

public class StatementWithoutTrailingSubstatementVisitor extends Java9BaseVisitor<StatementWithoutTrailingSubstatement> {

    public static StatementWithoutTrailingSubstatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public StatementWithoutTrailingSubstatement visitStatementWithoutTrailingSubstatement(final Java9Parser.StatementWithoutTrailingSubstatementContext ctx) {
        final SynchronizedStatementVisitor synchronizedStatementVisitor = SynchronizedStatementVisitor.instance();
        final ReturnStatementVisitor returnStatementVisitor = ReturnStatementVisitor.instance();
        final BlockVisitor blockVisitor = BlockVisitor.instance();
        final AssertStatementVisitor assertStatementVisitor = AssertStatementVisitor.instance();
        final BreakStatementVisitor breakStatementVisitor = BreakStatementVisitor.instance();
        final ContinueStatementVisitor continueStatementVisitor = ContinueStatementVisitor.instance();
        final DoStatementVisitor doStatementVisitor = DoStatementVisitor.instance();
        final SwitchStatementVisitor switchStatementVisitor = SwitchStatementVisitor.instance();
        final ThrowStatementVisitor throwStatementVisitor = ThrowStatementVisitor.instance();
        final TryStatementVisitor tryStatementVisitor = TryStatementVisitor.instance();
        final TryWithResourcesVisitor tryWithResourcesVisitor = TryWithResourcesVisitor.instance();
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();

        if (ctx.synchronizedStatement() != null) {
            return ctx.synchronizedStatement().accept(synchronizedStatementVisitor);
        } else if (ctx.returnStatement() != null) {
            return ctx.returnStatement().accept(returnStatementVisitor);
        } else if (ctx.block() != null) {
            return ctx.block().accept(blockVisitor);
        } else if (ctx.assertStatement() != null) {
            return ctx.assertStatement().accept(assertStatementVisitor);
        } else if (ctx.breakStatement() != null) {
            return ctx.breakStatement().accept(breakStatementVisitor);
        } else if (ctx.continueStatement() != null) {
            return ctx.continueStatement().accept(continueStatementVisitor);
        } else if (ctx.doStatement() != null) {
            return ctx.doStatement().accept(doStatementVisitor);
        } else if (ctx.switchStatement() != null) {
            return ctx.switchStatement().accept(switchStatementVisitor);
        } else if (ctx.emptyStatement() != null) {
            return StatementWithoutTrailingSubstatement.EmptyStatement.instance();
        } else if (ctx.throwStatement() != null) {
            return ctx.throwStatement().accept(throwStatementVisitor);
        } else if (ctx.tryStatement() != null) {
            return ctx.tryStatement().accept(tryStatementVisitor);
        } else if (ctx.tryStatement() != null && ctx.tryStatement().tryWithResourcesStatement() != null) {
            return ctx.tryStatement().tryWithResourcesStatement().accept(tryWithResourcesVisitor);
        } else if (ctx.expressionStatement() != null) {
            return ctx.expressionStatement().statementExpression().accept(statementExpressionVisitor);
        } else {
            throw new IllegalArgumentException("Invalid statement");
        }
    }

    private static final class Holder {
        private static final StatementWithoutTrailingSubstatementVisitor INSTANCE =
                new StatementWithoutTrailingSubstatementVisitor();
    }

}
