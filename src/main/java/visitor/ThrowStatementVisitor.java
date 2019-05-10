package visitor;

import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class ThrowStatementVisitor extends Java9BaseVisitor<Statement.ThrowStatement> {

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
