package data.statements;

import data.Field;
import utility.Nonnull;

public class EnhancedForStatement {

    @Nonnull
    private final Field variable;

    @Nonnull
    private final StatementWithoutTrailingSubstatement.StatementExpression collection;

    @Nonnull
    private final Statement bodyStatement;

    public EnhancedForStatement(@Nonnull final Field variable,
                                @Nonnull final StatementWithoutTrailingSubstatement.StatementExpression collection,
                                @Nonnull final Statement bodyStatement) {
        this.variable = variable;
        this.collection = collection;
        this.bodyStatement = bodyStatement;
    }

    @Nonnull
    public Field getVariable() {
        return variable;
    }

    @Nonnull
    public StatementWithoutTrailingSubstatement.StatementExpression getCollection() {
        return collection;
    }

    @Nonnull
    public Statement getBodyStatement() {
        return bodyStatement;
    }

    @Override
    public String toString() {
        return "EnhancedForStatement{" +
                "variable=" + variable +
                ", collection=" + collection +
                ", bodyStatement=" + bodyStatement +
                '}';
    }
}
