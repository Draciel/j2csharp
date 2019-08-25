package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.CatchClauseStatement;
import pl.draciel.j2csharp.data.Statement;
import pl.draciel.j2csharp.data.Type;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class CatchClauseStatementVisitor extends Java9BaseVisitor<CatchClauseStatement> {

    private CatchClauseStatementVisitor() {
        //no instance
    }

    public static CatchClauseStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public CatchClauseStatement visitCatchClause(final Java9Parser.CatchClauseContext ctx) {
        final BlockVisitor blockVisitor = BlockVisitor.instance();

        final List<Type> types;
        if (ctx.catchFormalParameter().catchType().classType().isEmpty()) {
            types = Collections.singletonList(new Type(ctx.catchFormalParameter().catchType().unannClassType().getText()));
        } else {
            types = new ArrayList<>();
            types.add(new Type(ctx.catchFormalParameter().catchType().unannClassType().getText()));
            ctx.catchFormalParameter()
                    .catchType()
                    .classType()
                    .stream()
                    .map(ct -> new Type(ct.identifier().getText()))
                    .forEachOrdered(types::add);
        }

        final String name = ctx.catchFormalParameter().variableDeclaratorId().identifier().getText();

        final Statement.Block block = ctx.block().accept(blockVisitor);

        return new CatchClauseStatement(block, types, name);
    }

    private static final class Holder {
        private static final CatchClauseStatementVisitor INSTANCE = new CatchClauseStatementVisitor();
    }
}
