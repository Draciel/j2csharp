package data;

import utility.Nonnull;

import java.util.List;

public class Class {

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
    private final Type superClass;

    @Nonnull
    private final List<Type> superInterfaces;

    @Nonnull
    private final List<Generic> generics;

    @Nonnull
    private final List<Class> classes;

    @Nonnull
    private final List<Interface> interfaces;

    @Nonnull
    private final List<Enum> enums;

    public Class(@Nonnull final String name, @Nonnull final List<Modifier> modifiers,
                 @Nonnull final List<Constructor> constructors,
                 @Nonnull final List<Method> methods, @Nonnull List<Field> fields,
                 @Nonnull final List<Annotation> annotations,
                 @Nonnull final Type superClass,
                 @Nonnull final List<Type> superInterfaces,
                 @Nonnull final List<Generic> generics, @Nonnull final List<Class> classes,
                 @Nonnull final List<Interface> interfaces, @Nonnull final List<Enum> enums) {
        this.name = name;
        this.modifiers = modifiers;
        this.constructors = constructors;
        this.methods = methods;
        this.fields = fields;
        this.annotations = annotations;
        this.superClass = superClass;
        this.superInterfaces = superInterfaces;
        this.generics = generics;
        this.classes = classes;
        this.interfaces = interfaces;
        this.enums = enums;
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
    public Type getSuperClass() {
        return superClass;
    }

    @Nonnull
    public List<Type> getSuperInterfaces() {
        return superInterfaces;
    }

    @Nonnull
    public List<Generic> getGenerics() {
        return generics;
    }

    @Nonnull
    public List<Class> getClasses() {
        return classes;
    }

    @Nonnull
    public List<Interface> getInterfaces() {
        return interfaces;
    }

    @Nonnull
    public List<Enum> getEnums() {
        return enums;
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
                ", classes=" + classes +
                ", interfaces=" + interfaces +
                ", enums=" + enums +
                '}';
    }
}
