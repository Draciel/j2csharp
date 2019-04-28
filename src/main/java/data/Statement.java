package data;

import utility.Nonnull;

public class Statement {

    @Nonnull
    private final String content;

    public Statement(@Nonnull String content) {
        this.content = content;
    }

    @Nonnull
    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "content='" + content + '\'' +
                '}';
    }
}
