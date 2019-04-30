package data;

import utility.Nonnull;

public class Statement {

    private static final Statement EMPTY_STATEMENT = new Statement("");

    @Nonnull
    private final String content;

    public Statement(@Nonnull String content) {
        this.content = content;
    }

    public static Statement emptyStatement() {
        return EMPTY_STATEMENT;
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
