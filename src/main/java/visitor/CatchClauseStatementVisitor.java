package visitor;

import data.Type;
import data.CatchClauseStatement;
import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.List;
import java.util.stream.Collectors;

class CatchClauseStatementVisitor extends Java9BaseVisitor<CatchClauseStatement> {

    public static CatchClauseStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public CatchClauseStatement visitCatchClause(final Java9Parser.CatchClauseContext ctx) {
        final BlockVisitor blockVisitor = BlockVisitor.instance();

        final List<Type> types = ctx.catchFormalParameter()
                .catchType()
                .classType()
                .stream()
                .map(ct -> new Type(ct.identifier().getText()))
                .collect(Collectors.toList());

        final String name = ctx.catchFormalParameter().variableDeclaratorId().identifier().getText();

        final Statement.Block block = ctx.block().accept(blockVisitor);

        return new CatchClauseStatement(block, types, name);
    }

    private static final class Holder {
        private static final CatchClauseStatementVisitor INSTANCE = new CatchClauseStatementVisitor();
    }
}
