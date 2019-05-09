package visitor;

import data.statements.IfStatement;
import data.statements.Statement;
import data.statements.StatementWithoutTrailingSubstatement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class IfStatementVisitor extends Java9BaseVisitor<IfStatement> {

    public static IfStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public IfStatement visitIfThenElseStatement(final Java9Parser.IfThenElseStatementContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        final StatementVisitor statementVisitor = StatementVisitor.instance();

        final StatementWithoutTrailingSubstatement.StatementExpression conditional = ctx.expression().accept(statementExpressionVisitor);
        final Statement ifBodyStatement = ctx.statementNoShortIf().accept(statementVisitor);
        final Statement elseBodyStatement = ctx.statement().accept(statementVisitor);

        return new IfStatement(conditional, ifBodyStatement, elseBodyStatement);
    }

//    @Override
//    public IfStatement visitIfThenElseStatementNoShortIf(final Java9Parser.IfThenElseStatementNoShortIfContext ctx) {
//        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
//        final StatementVisitor statementVisitor = StatementVisitor.instance();
//
//        final StatementExpression conditional = ctx.expression().accept(statementExpressionVisitor);
//        final Statement ifBodyStatement = ctx.statementNoShortIf().accept(statementVisitor);
//        final Statement elseBodyStatement = ctx.statementNoShortIf().accept(statementVisitor);
//
//        return new IfStatement(conditional, ifBodyStatement, elseBodyStatement);
//    }


    @Override
    public IfStatement visitIfThenStatement(final Java9Parser.IfThenStatementContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        final StatementVisitor statementVisitor = StatementVisitor.instance();

        final StatementWithoutTrailingSubstatement.StatementExpression conditional = ctx.expression().accept(statementExpressionVisitor);
        final Statement ifBodyStatement = ctx.statement().accept(statementVisitor);

        return new IfStatement(conditional, ifBodyStatement, null);
    }

    private static final class Holder {
        private static final IfStatementVisitor INSTANCE = new IfStatementVisitor();
    }
}
