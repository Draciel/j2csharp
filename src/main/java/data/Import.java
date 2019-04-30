package data;

public class Import {

    private final boolean isStatic;
    private final String content;

    public Import(final boolean isStatic, final String content) {
        this.isStatic = isStatic;
        this.content = content;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Import{" +
                "isStatic=" + isStatic +
                ", content='" + content + '\'' +
                '}';
    }
}
