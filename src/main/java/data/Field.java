package data;

import utility.Nonnull;

import java.util.Arrays;
import java.util.List;

public class Field {

    private final String name;

    private final String type;

    private final List<Modifier> modifiers;

    private final List<Annotation> annotations;

    private Field(@Nonnull final String name,
                  @Nonnull final String type,
                  @Nonnull final List<Modifier> modifiers,
                  @Nonnull final List<Annotation> annotations) {
        this.name = name;
        this.type = type;
        this.modifiers = modifiers;
        this.annotations = annotations;
    }

    public static Field of(@Nonnull final String name,
                           @Nonnull final String type,
                           @Nonnull final List<Modifier> list,
                           @Nonnull final List<Annotation> annotations) {
        return new Field(name, type, list, annotations);
    }

    public static Field of(@Nonnull final String name,
                           @Nonnull final String type,
                           @Nonnull final List<Annotation> annotations,
                           @Nonnull final Modifier... modifiers) {
        return new Field(name, type, Arrays.asList(modifiers), annotations);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<Modifier> getModifiers() {
        return modifiers;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", modifiers=" + modifiers +
                ", annotations=" + annotations +
                '}';
    }
}
