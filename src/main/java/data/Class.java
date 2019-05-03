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

    @Nonnull
    private final List<Annotation> annotations;

    @Nonnull
    private final String superClass;

    @Nonnull
    private final List<String> superInterfaces;

    @Nonnull
    private final List<Generic> generics;

    public Class(@Nonnull final String name, @Nonnull final List<Modifier> modifiers,
                 @Nonnull final List<Constructor> constructors,
                 @Nonnull final List<Method> methods, @Nonnull List<Field> fields,
                 @Nonnull final List<Annotation> annotations,
                 @Nonnull final String superClass,
                 @Nonnull final List<String> superInterfaces,
                 @Nonnull final List<Generic> generics) {
        this.name = name;
        this.modifiers = modifiers;
        this.constructors = constructors;
        this.methods = methods;
        this.fields = fields;
        this.annotations = annotations;
        this.superClass = superClass;
        this.superInterfaces = superInterfaces;
        this.generics = generics;
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

    @Nonnull
    public List<Annotation> getAnnotations() {
        return annotations;
    }

    @Nonnull
    public String getSuperClass() {
        return superClass;
    }

    @Nonnull
    public List<String> getSuperInterfaces() {
        return superInterfaces;
    }

    @Nonnull
    public List<Generic> getGenerics() {
        return generics;
    }

    @Override
    public String toString() {
        return "Class{" +
                "name='" + name + '\'' +
                ", modifiers=" + modifiers +
                ", constructors=" + constructors +
                ", fields=" + fields +
                ", methods=" + methods +
                ", annotations=" + annotations +
                ", superClass='" + superClass + '\'' +
                ", superInterfaces=" + superInterfaces +
                ", generics=" + generics +
                '}';
    }
}
