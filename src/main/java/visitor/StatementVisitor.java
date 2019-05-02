package visitor;

import data.Statement;
import org.antlr.v4.runtime.misc.Interval;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class StatementVisitor extends Java9BaseVisitor<Statement> {

    private static final Statement EMPTY_STATEMENT = new Statement("");

    private StatementVisitor() {
    }

    public static StatementVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Statement visitStatement(Java9Parser.StatementContext ctx) {
        if (ctx.getChildCount() == 0) {
            return EMPTY_STATEMENT;
        }
        //todo temporary workaround
        final int a = ctx.start.getStartIndex();
        final int b = ctx.stop.getStopIndex();
        final Interval interval = new Interval(a, b);
        return new Statement(ctx.start.getInputStream().getText(interval));
    }

    private static final class HOLDER {
        private static final StatementVisitor INSTANCE = new StatementVisitor();
    }
}
