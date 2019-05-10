package visitor;

import data.Field;
import data.CatchClauseStatement;
import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.List;
import java.util.stream.Collectors;

class TryWithResourcesVisitor extends Java9BaseVisitor<Statement.TryWithResourcesStatement> {

    public static TryWithResourcesVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.TryWithResourcesStatement visitTryWithResourcesStatement(final Java9Parser.TryWithResourcesStatementContext ctx) {
        final BlockVisitor blockVisitor = BlockVisitor.instance();
        final CatchClauseStatementVisitor catchClauseStatementVisitor = CatchClauseStatementVisitor.instance();
        final FieldVisitor fieldVisitor = FieldVisitor.instance();

        final Statement.Block tryBlock = ctx.block().accept(blockVisitor);

        final List<CatchClauseStatement> catchClauseStatements = ctx.catches()
                .catchClause()
                .stream()
                .map(cc -> cc.accept(catchClauseStatementVisitor))
                .collect(Collectors.toList());

        final Statement.Block finallyBlock = ctx.finally_().block().accept(blockVisitor);

        final List<Field> resources = ctx.resourceSpecification()
                .resourceList()
                .resource()
                .stream()
                .map(r -> r.accept(fieldVisitor))
                .collect(Collectors.toList());

        return Statement.TryWithResourcesStatement.of(tryBlock, catchClauseStatements,
                finallyBlock, resources);
    }

    private static final class Holder {
        private static final TryWithResourcesVisitor INSTANCE = new TryWithResourcesVisitor();
    }
}
