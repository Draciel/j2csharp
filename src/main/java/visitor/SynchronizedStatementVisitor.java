package visitor;

import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class SynchronizedStatementVisitor extends Java9BaseVisitor<Statement.SynchronizedStatement> {

    public static SynchronizedStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.SynchronizedStatement visitSynchronizedStatement(
            final Java9Parser.SynchronizedStatementContext ctx) {
        final BlockVisitor blockVisitor = BlockVisitor.instance();
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();

        final Statement.Block block;
        if (ctx.block() != null && ctx.block().blockStatements() != null) {
            block = ctx.block().accept(blockVisitor);
        } else {
            block = null;
        }

        final Statement.StatementExpression expression = ctx.expression().accept(statementExpressionVisitor);

        return Statement.SynchronizedStatement.of(block, expression);
    }

    private static final class Holder {
        private static final SynchronizedStatementVisitor INSTANCE = new SynchronizedStatementVisitor();
    }
}
