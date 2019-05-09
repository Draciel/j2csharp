package visitor;

import data.statements.StatementWithoutTrailingSubstatement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class ThrowStatementVisitor extends Java9BaseVisitor<StatementWithoutTrailingSubstatement.ThrowStatement> {

    public static ThrowStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public StatementWithoutTrailingSubstatement.ThrowStatement visitThrowStatement(final Java9Parser.ThrowStatementContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        return StatementWithoutTrailingSubstatement.ThrowStatement.of(ctx.expression().accept(statementExpressionVisitor));
    }

    private static final class Holder {
        private static final ThrowStatementVisitor INSTANCE = new ThrowStatementVisitor();
    }
}
