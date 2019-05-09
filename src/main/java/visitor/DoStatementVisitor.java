package visitor;

import data.statements.Statement;
import data.statements.StatementWithoutTrailingSubstatement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class DoStatementVisitor extends Java9BaseVisitor<StatementWithoutTrailingSubstatement.DoStatement> {

    public static DoStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public StatementWithoutTrailingSubstatement.DoStatement visitDoStatement(final Java9Parser.DoStatementContext ctx) {
        final StatementVisitor statementVisitor = StatementVisitor.instance();
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();

        final Statement statement = ctx.statement().accept(statementVisitor);
        final StatementWithoutTrailingSubstatement.StatementExpression statementExpression = ctx.expression().accept(statementExpressionVisitor);

        return StatementWithoutTrailingSubstatement.DoStatement.of(statementExpression, statement);
    }

    private static final class Holder {
        private static final DoStatementVisitor INSTANCE = new DoStatementVisitor();
    }
}
