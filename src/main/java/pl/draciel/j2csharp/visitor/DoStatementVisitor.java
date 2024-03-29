package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

final class DoStatementVisitor extends Java9BaseVisitor<Statement.DoStatement> {

    private DoStatementVisitor() {
        //no instance
    }

    public static DoStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.DoStatement visitDoStatement(final Java9Parser.DoStatementContext ctx) {
        final StatementVisitor statementVisitor = StatementVisitor.instance();
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
