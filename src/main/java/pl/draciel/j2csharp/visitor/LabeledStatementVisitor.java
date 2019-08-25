package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

final class LabeledStatementVisitor extends Java9BaseVisitor<Statement.LabeledStatement> {

    private LabeledStatementVisitor() {
        //no instance
    }

    public static LabeledStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.LabeledStatement visitLabeledStatement(final Java9Parser.LabeledStatementContext ctx) {
        throw new NotImplementedException();
    }

    private static final class Holder {
        private static final LabeledStatementVisitor INSTANCE = new LabeledStatementVisitor();
    }
}
