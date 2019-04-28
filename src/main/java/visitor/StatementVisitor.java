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
        return new Statement(ctx.getText());
    }

    private static final class HOLDER {
        private static final StatementVisitor INSTANCE = new StatementVisitor();
    }
}
