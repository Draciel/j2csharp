package data.statements;

import utility.Nonnull;
import utility.Nullable;

public class WhileStatement {

    @Nonnull
    private final StatementWithoutTrailingSubstatement.StatementExpression condition;

    @Nullable
    private final Statement whileBody;

    public WhileStatement(@Nonnull final StatementWithoutTrailingSubstatement.StatementExpression condition,
                          @Nullable final Statement whileBody) {

        this.condition = condition;
        this.whileBody = whileBody;
    }

    @Nonnull
    public StatementWithoutTrailingSubstatement.StatementExpression getCondition() {
        return condition;
    }

    @Nullable
    public Statement getWhileBody() {
        return whileBody;
    }

    @Override
    public String toString() {
        return "WhileStatement{" +
                "condition=" + condition +
                ", whileBody=" + whileBody +
                '}';
    }
}
