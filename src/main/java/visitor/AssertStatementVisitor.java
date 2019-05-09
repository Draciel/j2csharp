package visitor;

import data.statements.StatementWithoutTrailingSubstatement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.List;
import java.util.stream.Collectors;

class AssertStatementVisitor extends Java9BaseVisitor<StatementWithoutTrailingSubstatement.AssertStatement> {

    public static AssertStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public StatementWithoutTrailingSubstatement.AssertStatement visitAssertStatement(final Java9Parser.AssertStatementContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();

        final List<StatementWithoutTrailingSubstatement.StatementExpression> statementExpressions = ctx.expression()
                .stream()
                .map(e -> e.accept(statementExpressionVisitor))
                .collect(Collectors.toList());

        return StatementWithoutTrailingSubstatement.AssertStatement.of(statementExpressions);
    }

    private static final class Holder {
        private static final AssertStatementVisitor INSTANCE = new AssertStatementVisitor();
    }
}
