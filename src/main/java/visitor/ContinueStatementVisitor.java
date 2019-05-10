package visitor;

import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

final class ContinueStatementVisitor extends Java9BaseVisitor<Statement.ContinueStatement> {

    private ContinueStatementVisitor() {
        //no instance
    }

    public static ContinueStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.ContinueStatement visitContinueStatement(final Java9Parser.ContinueStatementContext ctx) {
        return Statement.ContinueStatement.of(ctx.identifier().getText());
    }

    private static final class Holder {
        private static final ContinueStatementVisitor INSTANCE = new ContinueStatementVisitor();
    }
}
