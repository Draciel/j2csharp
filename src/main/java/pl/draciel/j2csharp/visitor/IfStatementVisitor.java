package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

final class IfStatementVisitor extends Java9BaseVisitor<Statement.IfStatement> {

    private IfStatementVisitor() {
        //no instance
    }

    public static IfStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.IfStatement visitIfThenElseStatement(final Java9Parser.IfThenElseStatementContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        final StatementVisitor statementVisitor = StatementVisitor.instance();

        final Statement.StatementExpression conditional = ctx.expression().accept(statementExpressionVisitor);
        final Statement ifBodyStatement = ctx.statementNoShortIf().accept(statementVisitor);
        final Statement elseBodyStatement = ctx.statement().accept(statementVisitor);

        return Statement.IfStatement.of(conditional, ifBodyStatement, elseBodyStatement);
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
    public Statement.IfStatement visitIfThenStatement(final Java9Parser.IfThenStatementContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        final StatementVisitor statementVisitor = StatementVisitor.instance();

        final Statement.StatementExpression conditional = ctx.expression().accept(statementExpressionVisitor);
        final Statement ifBodyStatement = ctx.statement().accept(statementVisitor);

        return Statement.IfStatement.of(conditional, ifBodyStatement, null);
    }

    private static final class Holder {
        private static final IfStatementVisitor INSTANCE = new IfStatementVisitor();
    }
}
