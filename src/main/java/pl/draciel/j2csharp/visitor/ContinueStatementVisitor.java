package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

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
