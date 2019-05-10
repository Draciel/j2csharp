package visitor;

import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

final class SwitchStatementVisitor extends Java9BaseVisitor<Statement.SwitchStatement> {

    private SwitchStatementVisitor() {
        //no instance
    }

    public static SwitchStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.SwitchStatement visitSwitchStatement(final Java9Parser.SwitchStatementContext ctx) {
        throw new NotImplementedException();
    }

    private static final class Holder {
        private static final SwitchStatementVisitor INSTANCE = new SwitchStatementVisitor();
    }
}
