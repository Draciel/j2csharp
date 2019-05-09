package visitor;

import data.statements.StatementWithoutTrailingSubstatement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class ContinueStatementVisitor extends Java9BaseVisitor<StatementWithoutTrailingSubstatement.ContinueStatement> {

    public static ContinueStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public StatementWithoutTrailingSubstatement.ContinueStatement visitContinueStatement(final Java9Parser.ContinueStatementContext ctx) {
        return StatementWithoutTrailingSubstatement.ContinueStatement.of(ctx.identifier().getText());
    }

    private static final class Holder {
        private static final ContinueStatementVisitor INSTANCE = new ContinueStatementVisitor();
    }
}
