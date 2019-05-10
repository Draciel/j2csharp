package visitor;

import data.CatchClauseStatement;
import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

final class TryStatementVisitor extends Java9BaseVisitor<Statement.TryStatement> {

    private TryStatementVisitor() {
        //no instance
    }

    public static TryStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.TryStatement visitTryStatement(final Java9Parser.TryStatementContext ctx) {
        final BlockVisitor blockVisitor = BlockVisitor.instance();
        final CatchClauseStatementVisitor catchClauseStatementVisitor = CatchClauseStatementVisitor.instance();

        final Statement.Block tryBlock = ctx.block().accept(blockVisitor);

        final List<CatchClauseStatement> catchClauseStatements = ctx.catches() == null ? Collections.emptyList() :
                ctx.catches()
                        .catchClause()
                        .stream()
                        .map(cc -> cc.accept(catchClauseStatementVisitor))
                        .collect(Collectors.toList());

        final Statement.Block finallyBlock;
        if (ctx.finally_() != null) {
            finallyBlock = ctx.finally_().block().accept(blockVisitor);
        } else {
            finallyBlock = null;
        }

        return Statement.TryStatement.of(tryBlock, catchClauseStatements, finallyBlock);
    }

    private static final class Holder {
        private static final TryStatementVisitor INSTANCE = new TryStatementVisitor();
    }
}
