package data;

import utility.Nonnull;

import java.util.List;

public class Class {

    // TODO: add inheritance

    @Nonnull
    private final String name;

    @Nonnull
    private final List<Modifier> modifiers;

    @Nonnull
    private final List<Constructor> constructors;

    @Nonnull
    private final List<Field> fields;

    @Nonnull
    private final List<Method> methods;

    public Class(@Nonnull final String name, @Nonnull final List<Modifier> modifiers,
                 @Nonnull final List<Constructor> constructors,
                 @Nonnull final List<Method> methods, @Nonnull List<Field> fields) {
        this.name = name;
        this.modifiers = modifiers;
        this.constructors = constructors;
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

    @Nonnull
    public List<Modifier> getModifiers() {
        return modifiers;
    }

    @Nonnull
    public List<Constructor> getConstructors() {
        return constructors;
    }

    @Override
    public String toString() {
        return "Class{" +
                "name='" + name + '\'' +
                ", modifiers=" + modifiers +
                ", constructors=" + constructors +
                ", fields=" + fields +
                ", methods=" + methods +
                '}';
    }
}
