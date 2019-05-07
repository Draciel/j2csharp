package data;

import utility.Nonnull;

import java.util.Objects;

// Proxy class used to avoid mistakes during translation
public class Type {

    @Nonnull
    private final String literal;

    public Type(@Nonnull final String literal) {
        this.literal = literal;
    }

    @Nonnull
    public String literal() {
        return literal;
    }

    @Override
    public String toString() {
        return "Type{" +
                "literal='" + literal + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Type type = (Type) o;
        return Objects.equals(literal, type.literal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(literal);
    }
}
