package visitor;

import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.List;
import java.util.stream.Collectors;

final class AssertStatementVisitor extends Java9BaseVisitor<Statement.AssertStatement> {

    public static AssertStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    private AssertStatementVisitor() {
        //no instance
    }

    @Override
    public Statement.AssertStatement visitAssertStatement(final Java9Parser.AssertStatementContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();

        final List<Statement.StatementExpression> statementExpressions = ctx.expression()
                .stream()
                .map(e -> e.accept(statementExpressionVisitor))
                .collect(Collectors.toList());

        return Statement.AssertStatement.of(statementExpressions);
    }

    private static final class Holder {
        private static final AssertStatementVisitor INSTANCE = new AssertStatementVisitor();
    }
}
