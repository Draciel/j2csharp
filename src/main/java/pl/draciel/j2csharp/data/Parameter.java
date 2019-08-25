package pl.draciel.j2csharp.data;

import java.util.List;

public class Parameter {

    private final String name;

    private final Type type;

    private final List<Annotation> annotations;

    private final boolean isVararg;

    public Parameter(final String name, final Type type, List<Annotation> annotations,
                     final boolean isVararg) {
        this.name = name;
        this.type = type;
        this.annotations = annotations;
        this.isVararg = isVararg;
    }

    public boolean isVararg() {
        return isVararg;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", annotations=" + annotations +
                ", isVararg=" + isVararg +
                '}';
    }
}
