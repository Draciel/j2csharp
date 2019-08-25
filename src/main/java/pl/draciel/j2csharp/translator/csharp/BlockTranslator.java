package pl.draciel.j2csharp.translator.csharp;

import pl.draciel.j2csharp.data.BlockStatement;
import pl.draciel.j2csharp.data.Statement;
import pl.draciel.j2csharp.translator.ComponentTranslator;
import pl.draciel.j2csharp.utility.Nonnull;

import java.util.stream.Collectors;

final class BlockTranslator implements ComponentTranslator<Statement.Block> {

    private BlockTranslator() {
        //no instance
    }

    public static BlockTranslator instance() {
        return Holder.INSTANCE;
    }

    static String translateBlockStatement(@Nonnull final BlockStatement blockStatement, final int indentation) {
        final StatementTranslator statementTranslator = StatementTranslator.instance();
        final FieldTranslator fieldTranslator = FieldTranslator.instance();

        final StringBuilder blockStatementBuilder = new StringBuilder();

        if (blockStatement.getField() != null) {
            blockStatementBuilder.append(fieldTranslator.translate(blockStatement.getField(), indentation))
                    .append(";");
        }

        if (blockStatement.getStatement() != null) {
            blockStatementBuilder
                    .append(statementTranslator.translate(blockStatement.getStatement(), indentation));
        }

        return blockStatementBuilder.toString();
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.Block input, final int indentationCounter) {
        return input.getBlockStatement().stream()
                .map(bs -> translateBlockStatement(bs, indentationCounter + 1))
                .collect(Collectors.joining(Codestyle.newLine()));
    }

    private static final class Holder {
        private static final BlockTranslator INSTANCE = new BlockTranslator();
    }
}
