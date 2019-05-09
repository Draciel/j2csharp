package data.statements;

import data.Field;
import utility.Nonnull;
import utility.Nullable;

public class BasicForStatement {

    @Nullable
    private final Field initVariable;

    @Nullable
    private final StatementWithoutTrailingSubstatement.StatementExpression expression;

    @Nullable
    private final StatementWithoutTrailingSubstatement.StatementExpression updateStatement;

    @Nonnull
    private final Statement bodyStatement;

    public BasicForStatement(@Nullable final Field initVariable,
                             @Nullable final StatementWithoutTrailingSubstatement.StatementExpression expression,
                             @Nullable final StatementWithoutTrailingSubstatement.StatementExpression updateStatement,
                             @Nonnull final Statement bodyStatement) {

        this.initVariable = initVariable;
        this.expression = expression;
        this.updateStatement = updateStatement;
        this.bodyStatement = bodyStatement;
    }

    @Nullable
    public Field getInitVariable() {
        return initVariable;
    }

    @Nullable
    public StatementWithoutTrailingSubstatement.StatementExpression getExpression() {
        return expression;
    }

    @Nullable
    public StatementWithoutTrailingSubstatement.StatementExpression getUpdateStatement() {
        return updateStatement;
    }

    @Nonnull
    public Statement getBodyStatement() {
        return bodyStatement;
    }

    @Override
    public String toString() {
        return "BasicForStatement{" +
                "initVariable=" + initVariable +
                ", expression=" + expression +
                ", updateStatement=" + updateStatement +
                ", bodyStatement=" + bodyStatement +
                '}';
    }
}
