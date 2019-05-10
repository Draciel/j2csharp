package visitor;

import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class DoStatementVisitor extends Java9BaseVisitor<Statement.DoStatement> {

    public static DoStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.DoStatement visitDoStatement(final Java9Parser.DoStatementContext ctx) {
        final StatementWithoutTrailingSubstatementVisitor statementVisitor =
                StatementWithoutTrailingSubstatementVisitor.instance();
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();

        final Statement statement = ctx.statement().accept(statementVisitor);
        final Statement.StatementExpression statementExpression =
                ctx.expression().accept(statementExpressionVisitor);

        return Statement.DoStatement.of(statementExpression, statement);
    }

    private static final class Holder {
        private static final DoStatementVisitor INSTANCE = new DoStatementVisitor();
    }
}