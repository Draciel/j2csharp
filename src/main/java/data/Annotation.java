package data;

public class Annotation {

    private final String name;

    public Annotation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Annotation{" +
                "name='" + name + '\'' +
                '}';
    }
}
