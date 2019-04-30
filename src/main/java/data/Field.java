package data;

import utility.Nonnull;

import java.util.Arrays;
import java.util.List;

public class Field {

    private final String name;

    private final String type;

    private final List<Modifier> modifiers;

    private final List<Annotation> annotations;

    private final Statement initializer;

    private Field(@Nonnull final String name,
                  @Nonnull final String type,
                  @Nonnull final List<Modifier> modifiers,
                  @Nonnull final List<Annotation> annotations,
                  @Nonnull final Statement initializer) {
        this.name = name;
        this.type = type;
        this.modifiers = modifiers;
        this.annotations = annotations;
        this.initializer = initializer;
    }

    public static Field of(@Nonnull final String name,
                           @Nonnull final String type,
                           @Nonnull final List<Modifier> list,
                           @Nonnull final List<Annotation> annotations,
                           @Nonnull final Statement statement) {
        return new Field(name, type, list, annotations, statement);
    }

    public static Field of(@Nonnull final String name,
                           @Nonnull final String type,
                           @Nonnull final List<Annotation> annotations,
                           @Nonnull final Statement statement,
                           @Nonnull final Modifier... modifiers) {
        return new Field(name, type, Arrays.asList(modifiers), annotations, statement);
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

    public Statement getInitializer() {
        return initializer;
    }

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", modifiers=" + modifiers +
                ", annotations=" + annotations +
                ", initializer=" + initializer +
                '}';
    }
}
