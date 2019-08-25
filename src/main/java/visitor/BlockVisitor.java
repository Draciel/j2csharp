package visitor;

import data.BlockStatement;
import data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

import java.util.List;
import java.util.stream.Collectors;

final class BlockVisitor extends Java9BaseVisitor<Statement.Block> {

    private BlockVisitor() {
        //no instance
    }

    public static BlockVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.Block visitBlock(final Java9Parser.BlockContext ctx) {
        final BlockStatementVisitor blockStatementVisitor = BlockStatementVisitor.instance();

        final List<BlockStatement> blockStatements = ctx.blockStatements()
                .blockStatement()
                .stream()
                .map(bc -> bc.accept(blockStatementVisitor))
                .collect(Collectors.toList());

        return Statement.Block.of(blockStatements);
    }

    private static final class Holder {
        private static final BlockVisitor INSTANCE = new BlockVisitor();
    }
}
