package visitor;

import data.statements.StatementWithoutTrailingSubstatement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class ReturnStatementVisitor extends Java9BaseVisitor<StatementWithoutTrailingSubstatement.ReturnStatement> {

    public static ReturnStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public StatementWithoutTrailingSubstatement.ReturnStatement visitReturnStatement(final Java9Parser.ReturnStatementContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();

        return StatementWithoutTrailingSubstatement.ReturnStatement.of(ctx.expression().accept(statementExpressionVisitor));
    }

    private static final class Holder {
        private static final ReturnStatementVisitor INSTANCE = new ReturnStatementVisitor();
    }
}
