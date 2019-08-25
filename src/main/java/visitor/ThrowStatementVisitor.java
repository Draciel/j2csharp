package visitor;

import data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

final class ThrowStatementVisitor extends Java9BaseVisitor<Statement.ThrowStatement> {

    private ThrowStatementVisitor() {
        //no instance
    }

    public static ThrowStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.ThrowStatement visitThrowStatement(final Java9Parser.ThrowStatementContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        return Statement.ThrowStatement.of(ctx.expression().accept(statementExpressionVisitor));
    }

    private static final class Holder {
        private static final ThrowStatementVisitor INSTANCE = new ThrowStatementVisitor();
    }
}
