package visitor;

import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class StatementVisitor extends Java9BaseVisitor<Statement> {

    private StatementVisitor() {
    }

    public static StatementVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Statement visitStatement(Java9Parser.StatementContext ctx) {
        final Statement statement = new Statement(ctx.getText(), "raz dwa trzy");
        return statement;
    }

    private static final class HOLDER {
        private static final StatementVisitor INSTANCE = new StatementVisitor();
    }
}
