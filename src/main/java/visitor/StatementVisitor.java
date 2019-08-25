package visitor;

import data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

final class StatementVisitor extends Java9BaseVisitor<Statement> {

    private StatementVisitor() {
        //no instance
    }

    public static StatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement visitStatement(final Java9Parser.StatementContext ctx) {
        final LabeledStatementVisitor labeledStatementVisitor = LabeledStatementVisitor.instance();
        final IfStatementVisitor ifStatementVisitor = IfStatementVisitor.instance();
        final WhileStatementVisitor whileStatementVisitor = WhileStatementVisitor.instance();
        final ForStatementVisitor forStatementVisitor = ForStatementVisitor.instance();

        if (ctx.statementWithoutTrailingSubstatement() != null) {
            return ctx.statementWithoutTrailingSubstatement().accept(this);
        } else if (ctx.labeledStatement() != null) {
            return ctx.labeledStatement().accept(labeledStatementVisitor);
        } else if (ctx.ifThenStatement() != null) {
            return ctx.ifThenStatement().accept(ifStatementVisitor);
        } else if (ctx.ifThenElseStatement() != null) {
            return ctx.ifThenElseStatement().accept(ifStatementVisitor);
        } else if (ctx.whileStatement() != null) {
            return ctx.whileStatement().accept(whileStatementVisitor);
        } else if (ctx.forStatement() != null) {
            return ctx.forStatement().accept(forStatementVisitor);
        } else {
            throw new IllegalArgumentException("Unsupported statement type!");
        }
    }

    @Override
    public Statement visitStatementNoShortIf(final Java9Parser.StatementNoShortIfContext ctx) {
        final LabeledStatementVisitor labeledStatementVisitor = LabeledStatementVisitor.instance();
        final IfStatementVisitor ifStatementVisitor = IfStatementVisitor.instance();
        final WhileStatementVisitor whileStatementVisitor = WhileStatementVisitor.instance();
        final ForStatementVisitor forStatementVisitor = ForStatementVisitor.instance();

        if (ctx.statementWithoutTrailingSubstatement() != null) {
            return ctx.statementWithoutTrailingSubstatement().accept(this);
        } else if (ctx.labeledStatementNoShortIf() != null) {
            return ctx.labeledStatementNoShortIf().accept(labeledStatementVisitor);
        } else if (ctx.ifThenElseStatementNoShortIf() != null) {
            return ctx.ifThenElseStatementNoShortIf().accept(ifStatementVisitor);
        } else if (ctx.whileStatementNoShortIf() != null) {
            return ctx.whileStatementNoShortIf().accept(whileStatementVisitor);
        } else if (ctx.forStatementNoShortIf() != null) {
            return ctx.forStatementNoShortIf().accept(forStatementVisitor);
        } else {
            throw new IllegalArgumentException("Unsupported statement type!");
        }
    }

    @Override
    public Statement visitStatementWithoutTrailingSubstatement(final Java9Parser.StatementWithoutTrailingSubstatementContext ctx) {
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
            return Statement.EmptyStatement.instance();
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
        private static final StatementVisitor INSTANCE = new StatementVisitor();
    }
}
