package pl.draciel.j2csharp.data;

import pl.draciel.j2csharp.utility.Nonnull;

import java.util.List;
import java.util.Objects;

public class CatchClauseStatement {

    @Nonnull
    private final Statement.Block block;

    @Nonnull
    private final List<Type> types;

    @Nonnull
    private final String variableName;

    public CatchClauseStatement(@Nonnull final Statement.Block block,
                                @Nonnull final List<Type> types, @Nonnull final String variableName) {
        this.block = block;
        this.types = types;
        this.variableName = variableName;
    }

    @Nonnull
    public Statement.Block getBlock() {
        return block;
    }

    @Nonnull
    public List<Type> getTypes() {
        return types;
    }

    @Nonnull
    public String getVariableName() {
        return variableName;
    }

    @Override
    public String toString() {
        return "CatchClauseStatement{" +
                "block=" + block +
                ", types=" + types +
                ", variableName='" + variableName + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof CatchClauseStatement)) return false;
        final CatchClauseStatement that = (CatchClauseStatement) o;
        return Objects.equals(block, that.block) &&
                Objects.equals(types, that.types) &&
                Objects.equals(variableName, that.variableName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(block, types, variableName);
    }
}
