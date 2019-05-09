package visitor;

import data.statements.CatchClauseStatement;
import data.statements.StatementWithoutTrailingSubstatement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.List;
import java.util.stream.Collectors;

class TryStatementVisitor extends Java9BaseVisitor<StatementWithoutTrailingSubstatement.TryStatement> {

    public static TryStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public StatementWithoutTrailingSubstatement.TryStatement visitTryStatement(final Java9Parser.TryStatementContext ctx) {
        final BlockVisitor blockVisitor = BlockVisitor.instance();
        final CatchClauseStatementVisitor catchClauseStatementVisitor = CatchClauseStatementVisitor.instance();

        final StatementWithoutTrailingSubstatement.Block tryBlock = ctx.block().accept(blockVisitor);

        final List<CatchClauseStatement> catchClauseStatements = ctx.catches()
                .catchClause()
                .stream()
                .map(cc -> cc.accept(catchClauseStatementVisitor))
                .collect(Collectors.toList());

        final StatementWithoutTrailingSubstatement.Block finallyBlock = ctx.finally_().block().accept(blockVisitor);

        return StatementWithoutTrailingSubstatement.TryStatement.of(tryBlock, catchClauseStatements, finallyBlock);
    }

    private static final class Holder {
        private static final TryStatementVisitor INSTANCE = new TryStatementVisitor();
    }
}
