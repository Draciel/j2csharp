package data.statements;

import utility.Nonnull;
import utility.Nullable;

public class IfStatement {

    @Nonnull
    private final StatementWithoutTrailingSubstatement.StatementExpression conditional;

    @Nonnull
    private final Statement ifBodyStatement;

    @Nullable
    private final Statement elseBodyStatement;

    public IfStatement(@Nonnull final StatementWithoutTrailingSubstatement.StatementExpression conditional,
                       @Nonnull final Statement ifBodyStatement,
                       @Nullable final Statement elseBodyStatement) {

        this.conditional = conditional;
        this.ifBodyStatement = ifBodyStatement;
        this.elseBodyStatement = elseBodyStatement;
    }

    @Nonnull
    public StatementWithoutTrailingSubstatement.StatementExpression getConditional() {
        return conditional;
    }

    @Nonnull
    public Statement getIfBodyStatement() {
        return ifBodyStatement;
    }

    @Nullable
    public Statement getElseBodyStatement() {
        return elseBodyStatement;
    }

    @Override
    public String toString() {
        return "IfStatement{" +
                "conditional=" + conditional +
                ", ifBodyStatement=" + ifBodyStatement +
                ", elseBodyStatement=" + elseBodyStatement +
                '}';
    }
}
