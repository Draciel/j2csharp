package visitor;

import data.statements.*;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class StatementVisitor extends Java9BaseVisitor<Statement> {

    public static StatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement visitStatement(final Java9Parser.StatementContext ctx) {
        final StatementWithoutTrailingSubstatementVisitor statementWithoutTrailingSubstatementVisitor =
                StatementWithoutTrailingSubstatementVisitor.instance();
        final LabeledStatementVisitor labeledStatementVisitor = LabeledStatementVisitor.instance();
        final IfStatementVisitor ifStatementVisitor = IfStatementVisitor.instance();
        final WhileStatementVisitor whileStatementVisitor = WhileStatementVisitor.instance();
        final ForStatementVisitor forStatementVisitor = ForStatementVisitor.instance();

        final StatementWithoutTrailingSubstatement statementWithoutTrailingSubstatement =
                ctx.statementWithoutTrailingSubstatement() == null ? null :
                        ctx.statementWithoutTrailingSubstatement().accept(statementWithoutTrailingSubstatementVisitor);

        final LabeledStatement labeledStatement = ctx.labeledStatement() == null ? null :
                ctx.labeledStatement().accept(labeledStatementVisitor);

        final IfStatement ifStatement;
        if (ctx.ifThenStatement() != null) {
            ifStatement = ctx.ifThenStatement().accept(ifStatementVisitor);
        } else if (ctx.ifThenElseStatement() != null) {
            ifStatement = ctx.ifThenElseStatement().accept(ifStatementVisitor);
        } else {
            ifStatement = null;
        }

        final WhileStatement whileStatement = ctx.whileStatement() == null ? null :
                ctx.whileStatement().accept(whileStatementVisitor);

        final ForStatement forStatement = ctx.forStatement() == null ? null :
                ctx.forStatement().accept(forStatementVisitor);

        if (statementWithoutTrailingSubstatement == null && labeledStatement == null && ifStatement == null
                && whileStatement == null && forStatement == null) {
            return Statement.empty();
        }

        return Statement.of(statementWithoutTrailingSubstatement, labeledStatement, ifStatement, whileStatement,
                forStatement);
    }

    @Override
    public Statement visitStatementNoShortIf(final Java9Parser.StatementNoShortIfContext ctx) {
        final StatementWithoutTrailingSubstatementVisitor statementWithoutTrailingSubstatementVisitor =
                StatementWithoutTrailingSubstatementVisitor.instance();
        final LabeledStatementVisitor labeledStatementVisitor = LabeledStatementVisitor.instance();
        final IfStatementVisitor ifStatementVisitor = IfStatementVisitor.instance();
        final WhileStatementVisitor whileStatementVisitor = WhileStatementVisitor.instance();
        final ForStatementVisitor forStatementVisitor = ForStatementVisitor.instance();

        final StatementWithoutTrailingSubstatement statementWithoutTrailingSubstatement =
                ctx.statementWithoutTrailingSubstatement() == null ? null :
                        ctx.statementWithoutTrailingSubstatement().accept(statementWithoutTrailingSubstatementVisitor);

        final LabeledStatement labeledStatement = ctx.labeledStatementNoShortIf() == null ? null :
                ctx.labeledStatementNoShortIf().accept(labeledStatementVisitor);

        final IfStatement ifStatement;
        if (ctx.ifThenElseStatementNoShortIf() != null) {
            ifStatement = ctx.ifThenElseStatementNoShortIf().accept(ifStatementVisitor);
        } else {
            ifStatement = null;
        }

        final WhileStatement whileStatement = ctx.whileStatementNoShortIf() == null ? null :
                ctx.whileStatementNoShortIf().accept(whileStatementVisitor);

        final ForStatement forStatement = ctx.forStatementNoShortIf() == null ? null :
                ctx.forStatementNoShortIf().accept(forStatementVisitor);

        if (statementWithoutTrailingSubstatement == null && labeledStatement == null && ifStatement == null
                && whileStatement == null && forStatement == null) {
            return Statement.empty();
        }

        return Statement.of(statementWithoutTrailingSubstatement, labeledStatement, ifStatement, whileStatement,
                forStatement);
    }

    private static final class Holder {
        private static final StatementVisitor INSTANCE = new StatementVisitor();
    }
}
