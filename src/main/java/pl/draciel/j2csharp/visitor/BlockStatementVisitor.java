package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.BlockStatement;
import pl.draciel.j2csharp.data.Field;
import pl.draciel.j2csharp.data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

final class BlockStatementVisitor extends Java9BaseVisitor<BlockStatement> {

    private BlockStatementVisitor() {
        //no instance
    }

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

//        if (statement == null && field == null){
//            return Statement.EmptyStatement.instance();
//        }

        return new BlockStatement(field, statement);
    }

    private static final class Holder {
        private static final BlockStatementVisitor INSTANCE = new BlockStatementVisitor();
    }
}
