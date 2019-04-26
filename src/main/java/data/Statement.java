package data;

import utility.Nonnull;

public class Statement {

    @Nonnull
    private final String name;

    @Nonnull
    private final String content;

    public Statement(@Nonnull String name, @Nonnull String content) {
        this.name = name;
        this.content = content;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
