package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

final class SynchronizedStatementVisitor extends Java9BaseVisitor<Statement.SynchronizedStatement> {

    private SynchronizedStatementVisitor() {
        //no instance
    }

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
