package visitor;

import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class ReturnStatementVisitor extends Java9BaseVisitor<Statement.ReturnStatement> {

    public static ReturnStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.ReturnStatement visitReturnStatement(final Java9Parser.ReturnStatementContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();

        return Statement.ReturnStatement.of(ctx.expression().accept(statementExpressionVisitor));
    }

    private static final class Holder {
        private static final ReturnStatementVisitor INSTANCE = new ReturnStatementVisitor();
    }
}
