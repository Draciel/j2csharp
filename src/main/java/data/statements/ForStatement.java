package data.statements;

import utility.Nullable;

public class ForStatement {

    @Nullable
    private final EnhancedForStatement enhancedForStatement;

    @Nullable
    private final BasicForStatement basicForStatement;

    public ForStatement(@Nullable final EnhancedForStatement enhancedForStatement, @Nullable final BasicForStatement basicForStatement) {
        this.enhancedForStatement = enhancedForStatement;
        this.basicForStatement = basicForStatement;
    }

    @Nullable
    public EnhancedForStatement getEnhancedForStatement() {
        return enhancedForStatement;
    }

    @Nullable
    public BasicForStatement getBasicForStatement() {
        return basicForStatement;
    }

    @Override
    public String toString() {
        return "ForStatement{" +
                "enhancedForStatement=" + enhancedForStatement +
                ", basicForStatement=" + basicForStatement +
                '}';
    }
}
