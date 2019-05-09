package visitor;

import data.BlockStatement;
import data.Field;
import data.statements.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

class BlockStatementVisitor extends Java9BaseVisitor<BlockStatement> {

    public static BlockStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public BlockStatement visitBlockStatement(final Java9Parser.BlockStatementContext ctx) {
        final StatementVisitor statementVisitor = StatementVisitor.instance();
        final FieldVisitor fieldVisitor = FieldVisitor.instance();

        final Field field;
        if (ctx.localVariableDeclarationStatement() != null) {
            field = ctx.localVariableDeclarationStatement().localVariableDeclaration()
                    .accept(fieldVisitor);
        } else {
            field = null;
        }

        final Statement statement;
        if (ctx.statement() != null) {
            statement = ctx.statement().accept(statementVisitor);
        } else {
            statement = null;
        }

        return new BlockStatement(field, statement);
    }

    private static final class Holder {
        private static final BlockStatementVisitor INSTANCE = new BlockStatementVisitor();
    }
}
