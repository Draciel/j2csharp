package visitor;

import data.BlockStatement;
import data.statements.StatementWithoutTrailingSubstatement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

import java.util.List;
import java.util.stream.Collectors;

class BlockVisitor extends Java9BaseVisitor<StatementWithoutTrailingSubstatement.Block> {

    public static BlockVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public StatementWithoutTrailingSubstatement.Block visitBlock(final Java9Parser.BlockContext ctx) {
        final BlockStatementVisitor blockStatementVisitor = BlockStatementVisitor.instance();

        final List<BlockStatement> blockStatements = ctx.blockStatements()
                .blockStatement()
                .stream()
                .map(bc -> bc.accept(blockStatementVisitor))
                .collect(Collectors.toList());

        return StatementWithoutTrailingSubstatement.Block.of(blockStatements);
    }

    private static final class Holder {
        private static final BlockVisitor INSTANCE = new BlockVisitor();
    }
}
