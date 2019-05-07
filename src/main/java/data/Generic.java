package data;

import utility.Nonnull;
import utility.Nullable;

import java.util.List;

public class Generic {

    @Nonnull
    private final Type typeParameter; // T

    @Nullable
    private final Type type; // Class, kinda code smell

    @Nonnull
    private final String boundType; //extends or super

    @Nullable
    private final List<Generic> boundedType; // mainly wildcards, not supported in c#

    private final boolean isWildcard;

    public Generic(@Nonnull final Type typeParameter,
                   @Nullable final Type type,
                   @Nonnull final String boundType,
                   final boolean isWildcard,
                   @Nullable final List<Generic> boundedType) {
        this.type = type;
        this.typeParameter = typeParameter;
        this.boundType = boundType;
        this.isWildcard = isWildcard;
        this.boundedType = boundedType;
    }

    @Nonnull
    public Type getTypeParameter() {
        return typeParameter;
    }

    @Nonnull
    public String getBoundType() {
        return boundType;
    }

    @Nullable
    public List<Generic> getBoundedType() {
        return boundedType;
    }

    @Nullable
    public Type getType() {
        return type;
    }

    public boolean isWildcard() {
        return isWildcard;
    }

    @Override
    public String toString() {
        return "Generic{" +
                "typeParameter='" + typeParameter + '\'' +
                ", type='" + type + '\'' +
                ", boundType='" + boundType + '\'' +
                ", boundedType=" + boundedType +
                ", isWildcard=" + isWildcard +
                '}';
    }
}
