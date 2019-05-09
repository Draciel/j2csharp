package visitor;

import data.statements.StatementWithoutTrailingSubstatement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class SynchronizedStatementVisitor extends Java9BaseVisitor<StatementWithoutTrailingSubstatement.SynchronizedStatement> {

    public static SynchronizedStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public StatementWithoutTrailingSubstatement.SynchronizedStatement visitSynchronizedStatement(
            final Java9Parser.SynchronizedStatementContext ctx) {
        final BlockVisitor blockVisitor = BlockVisitor.instance();
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();

        final StatementWithoutTrailingSubstatement.Block block;
        if (ctx.block() != null && ctx.block().blockStatements() != null) {
            block = ctx.block().accept(blockVisitor);
        } else {
            block = null;
        }

        final StatementWithoutTrailingSubstatement.StatementExpression expression = ctx.expression().accept(statementExpressionVisitor);

        return StatementWithoutTrailingSubstatement.SynchronizedStatement.of(block, expression);
    }

    private static final class Holder {
        private static final SynchronizedStatementVisitor INSTANCE = new SynchronizedStatementVisitor();
    }
}
