package data;

import data.statements.Statement;
import utility.Nonnull;
import utility.Nullable;

import java.util.Objects;

public class BlockStatement {

    @Nonnull
    private final Field field;

    @Nullable
    private final Statement statement;

    //fixme class declaration (probably anonymous class)

    public BlockStatement(@Nullable final Field field, @Nonnull final Statement statement) {
        this.field = field;
        this.statement = statement;
    }

    @Nonnull
    public Field getField() {
        return field;
    }

    @Nullable
    public Statement getStatement() {
        return statement;
    }

    @Override
    public String toString() {
        return "BlockStatement{" +
                "field=" + field +
                ", statement=" + statement +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockStatement)) return false;
        final BlockStatement that = (BlockStatement) o;
        return Objects.equals(field, that.field) &&
                Objects.equals(statement, that.statement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, statement);
    }
}
