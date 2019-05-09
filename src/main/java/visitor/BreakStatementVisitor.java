package visitor;

import data.statements.StatementWithoutTrailingSubstatement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class BreakStatementVisitor extends Java9BaseVisitor<StatementWithoutTrailingSubstatement.BreakStatement> {

    public static BreakStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public StatementWithoutTrailingSubstatement.BreakStatement visitBreakStatement(final Java9Parser.BreakStatementContext ctx) {
        return StatementWithoutTrailingSubstatement.BreakStatement.of(ctx.identifier().getText());
    }

    private static final class Holder {
        private static final BreakStatementVisitor INSTANCE = new BreakStatementVisitor();
    }
}
