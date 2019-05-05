package data;

import utility.Nonnull;

import java.util.List;

public class Interface {

    @Nonnull
    private final String name;

    @Nonnull
    private final List<Modifier> modifiers;

    @Nonnull
    private final List<Field> fields;

    @Nonnull
    private final List<Method> methods;

    @Nonnull
    private final List<Annotation> annotations;

    @Nonnull
    private final List<String> superInterfaces;

    @Nonnull
    private final List<Generic> generics;

    @Nonnull
    private final List<Interface> interfaces;

    @Nonnull
    private final List<Class> classes;

    @Nonnull
    private final List<Enum> enums;

    public Interface(@Nonnull final String name, @Nonnull final List<Modifier> modifiers,
                     @Nonnull final List<Field> fields, @Nonnull final List<Method> methods,
                     @Nonnull final List<Annotation> annotations,
                     @Nonnull final List<String> superInterfaces,
                     @Nonnull final List<Generic> generics,
                     @Nonnull final List<Interface> interfaces,
                     @Nonnull final List<Class> classes,
                     @Nonnull final List<Enum> enums) {
        this.name = name;
        this.modifiers = modifiers;
        this.fields = fields;
        this.methods = methods;
        this.annotations = annotations;
        this.superInterfaces = superInterfaces;
        this.generics = generics;
        this.interfaces = interfaces;
        this.classes = classes;
        this.enums = enums;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public List<Modifier> getModifiers() {
        return modifiers;
    }

    @Nonnull
    public List<Field> getFields() {
        return fields;
    }

    @Nonnull
    public List<Method> getMethods() {
        return methods;
    }

    @Nonnull
    public List<Annotation> getAnnotations() {
        return annotations;
    }

    @Nonnull
    public List<String> getSuperInterfaces() {
        return superInterfaces;
    }

    @Nonnull
    public List<Generic> getGenerics() {
        return generics;
    }

    @Nonnull
    public List<Interface> getInterfaces() {
        return interfaces;
    }

    @Nonnull
    public List<Class> getClasses() {
        return classes;
    }

    @Nonnull
    public List<Enum> getEnums() {
        return enums;
    }

    @Override
    public String toString() {
        return "Interface{" +
                "name='" + name + '\'' +
                ", modifiers=" + modifiers +
                ", fields=" + fields +
                ", methods=" + methods +
                ", annotations=" + annotations +
                ", superInterfaces=" + superInterfaces +
                ", generics=" + generics +
                ", interfaces=" + interfaces +
                ", classes=" + classes +
                ", enums=" + enums +
                '}';
    }
}
