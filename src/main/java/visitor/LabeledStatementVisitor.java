package visitor;

import data.statements.LabeledStatement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

class LabeledStatementVisitor extends Java9BaseVisitor<LabeledStatement> {

    public static LabeledStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public LabeledStatement visitLabeledStatement(final Java9Parser.LabeledStatementContext ctx) {
        throw new NotImplementedException();
    }

    private static final class Holder {
        private static final LabeledStatementVisitor INSTANCE = new LabeledStatementVisitor();
    }
}
