package data;

import java.util.List;

public class Parameter {

    private final String name;

    private final String type;

    private final List<Annotation> annotations;

    public Parameter(final String name, final String type, List<Annotation> annotations) {
        this.name = name;
        this.type = type;
        this.annotations = annotations;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", annotations=" + annotations +
                '}';
    }
}
