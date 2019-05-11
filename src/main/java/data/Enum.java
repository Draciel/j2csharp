package data;

import utility.Nonnull;

import java.util.List;

public class Enum {

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
    private final List<Type> superInterfaces;

    @Nonnull
    private final List<Class> classes;

    @Nonnull
    private final List<Interface> interfaces;

    @Nonnull
    private final List<Enum> enums;

    @Nonnull
    private final List<EnumConstant> enumConstants;


    public Enum(@Nonnull final String name, @Nonnull final List<Modifier> modifiers,
                @Nonnull final List<Constructor> constructors, @Nonnull final List<Field> fields,
                @Nonnull final List<Method> methods, @Nonnull final List<Annotation> annotations,
                @Nonnull final List<Type> superInterfaces, @Nonnull final List<Class> classes,
                @Nonnull final List<Interface> interfaces, @Nonnull final List<Enum> enums,
                @Nonnull final List<EnumConstant> enumConstants) {
        this.name = name;
        this.modifiers = modifiers;
        this.constructors = constructors;
        this.fields = fields;
        this.methods = methods;
        this.annotations = annotations;
        this.superInterfaces = superInterfaces;
        this.classes = classes;
        this.interfaces = interfaces;
        this.enums = enums;
        this.enumConstants = enumConstants;
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
    public List<Constructor> getConstructors() {
        return constructors;
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
    public List<Type> getSuperInterfaces() {
        return superInterfaces;
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

    @Nonnull
    public List<EnumConstant> getEnumConstants() {
        return enumConstants;
    }

    @Override
    public String toString() {
        return "Enum{" +
                "name='" + name + '\'' +
                ", modifiers=" + modifiers +
                ", constructors=" + constructors +
                ", fields=" + fields +
                ", methods=" + methods +
                ", annotations=" + annotations +
                ", superInterfaces=" + superInterfaces +
                ", classes=" + classes +
                ", interfaces=" + interfaces +
                ", enums=" + enums +
                ", enumConstants=" + enumConstants +
                '}';
    }
}
