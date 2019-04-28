package data;

public class Annotation {

    private final String type;

    public Annotation(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Annotation{" +
                "type='" + type + '\'' +
                '}';
    }
}
