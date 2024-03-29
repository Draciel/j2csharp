package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.CatchClauseStatement;
import pl.draciel.j2csharp.data.Field;
import pl.draciel.j2csharp.data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

import java.util.List;
import java.util.stream.Collectors;

final class TryWithResourcesVisitor extends Java9BaseVisitor<Statement.TryWithResourcesStatement> {

    private TryWithResourcesVisitor() {
        //no instance
    }

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
