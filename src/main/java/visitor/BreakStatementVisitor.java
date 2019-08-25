package visitor;

import data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

final class BreakStatementVisitor extends Java9BaseVisitor<Statement.BreakStatement> {

    private BreakStatementVisitor() {
        //no instance
    }

    public static BreakStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.BreakStatement visitBreakStatement(final Java9Parser.BreakStatementContext ctx) {
        return Statement.BreakStatement.of(ctx.identifier() == null ? null : ctx.identifier().getText());
    }

    private static final class Holder {
        private static final BreakStatementVisitor INSTANCE = new BreakStatementVisitor();
    }
}
