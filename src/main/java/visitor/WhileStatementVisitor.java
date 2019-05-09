package visitor;

import data.statements.Statement;
import data.statements.StatementWithoutTrailingSubstatement;
import data.statements.WhileStatement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class WhileStatementVisitor extends Java9BaseVisitor<WhileStatement> {

    public static WhileStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public WhileStatement visitWhileStatement(final Java9Parser.WhileStatementContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        final StatementVisitor statementVisitor = StatementVisitor.instance();

        final StatementWithoutTrailingSubstatement.StatementExpression condition = ctx.expression().accept(statementExpressionVisitor);
        final Statement whileBody = ctx.statement().accept(statementVisitor);

        return new WhileStatement(condition, whileBody);
    }

    private static final class Holder {
        private static final WhileStatementVisitor INSTANCE = new WhileStatementVisitor();
    }

}
