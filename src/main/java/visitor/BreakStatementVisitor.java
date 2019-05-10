package visitor;

import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

final class BreakStatementVisitor extends Java9BaseVisitor<Statement.BreakStatement> {

    private BreakStatementVisitor() {
        //no instance
    }

    public static BreakStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.BreakStatement visitBreakStatement(final Java9Parser.BreakStatementContext ctx) {
        return Statement.BreakStatement.of(ctx.identifier().getText());
    }

    private static final class Holder {
        private static final BreakStatementVisitor INSTANCE = new BreakStatementVisitor();
    }
}
