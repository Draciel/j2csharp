package data;

import utility.Nonnull;

import java.util.Arrays;
import java.util.List;

public class Field {

    private final String name;

    private final String type;

    private final List<Modifier> modifiers;

    private Field(@Nonnull final String name,
                  @Nonnull final String type,
                  @Nonnull final List<Modifier> modifiers) {
        this.name = name;
        this.type = type;
        this.modifiers = modifiers;
    }

    public static Field of(@Nonnull final String name,
                    @Nonnull final String type,
                    @Nonnull final List<Modifier> list) {
        return new Field(name, type, list);
    }

    public static Field of(@Nonnull final String name,
                    @Nonnull final String type,
                    @Nonnull final Modifier... modifiers) {
        return new Field(name, type, Arrays.asList(modifiers));
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

    @Override
    public String toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", modifiers=" + modifiers +
                '}';
    }
}
