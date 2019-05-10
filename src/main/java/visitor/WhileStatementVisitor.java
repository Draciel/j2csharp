package visitor;

import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class WhileStatementVisitor extends Java9BaseVisitor<Statement.WhileStatement> {

    public static WhileStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.WhileStatement visitWhileStatement(final Java9Parser.WhileStatementContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        final StatementWithoutTrailingSubstatementVisitor statementVisitor =
                StatementWithoutTrailingSubstatementVisitor.instance();

        final Statement.StatementExpression condition =
                ctx.expression().accept(statementExpressionVisitor);
        final Statement whileBody = ctx.statement().accept(statementVisitor);

        return Statement.WhileStatement.of(condition, whileBody);
    }

    private static final class Holder {
        private static final WhileStatementVisitor INSTANCE = new WhileStatementVisitor();
    }

}
