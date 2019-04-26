package data;

import utility.Nonnull;

import java.util.List;

public class Class {

    @Nonnull
    private final String name;

    @Nonnull
    private final List<Method> methods;

    @Nonnull
    private final List<Field> fields;

    public Class(@Nonnull final String name, @Nonnull final List<Method> methods, @Nonnull List<Field> fields) {
        this.name = name;
        this.methods = methods;
        this.fields = fields;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public List<Method> getMethods() {
        return methods;
    }

    @Nonnull
    public List<Field> getFields() {
        return fields;
    }

    @Override
    public String toString() {
        return "Class{" +
                "name='" + name + '\'' +
                ", methods=" + methods +
                ", fields=" + fields +
                '}';
    }
}
