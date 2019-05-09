package data.statements;

import utility.Nullable;

public class Statement {

    @Nullable
    private final StatementWithoutTrailingSubstatement statementWithoutTrailingSubstatement;

    @Nullable
    private final LabeledStatement labeledStatement;

    @Nullable
    private final IfStatement ifStatement;

    @Nullable
    private final WhileStatement whileStatement;

    @Nullable
    private final ForStatement forStatement;

    private boolean isEmpty;

    private Statement(@Nullable final StatementWithoutTrailingSubstatement statementWithoutTrailingSubstatement,
                      @Nullable final LabeledStatement labeledStatement,
                      @Nullable final IfStatement ifStatement,
                      @Nullable final WhileStatement whileStatement,
                      @Nullable final ForStatement forStatement,
                      final boolean isEmpty) {

        this.statementWithoutTrailingSubstatement = statementWithoutTrailingSubstatement;
        this.labeledStatement = labeledStatement;
        this.ifStatement = ifStatement;
        this.whileStatement = whileStatement;
        this.forStatement = forStatement;
        this.isEmpty = isEmpty;
    }

    public static Statement empty() {
        return new Statement(null, null, null, null, null, true);
    }

    public static Statement of(@Nullable final StatementWithoutTrailingSubstatement statementWithoutTrailingSubstatement,
                               @Nullable final LabeledStatement labeledStatement,
                               @Nullable final IfStatement ifStatement,
                               @Nullable final WhileStatement whileStatement,
                               @Nullable final ForStatement forStatement) {
        return new Statement(statementWithoutTrailingSubstatement, labeledStatement, ifStatement, whileStatement,
                forStatement, false);
    }

    @Nullable
    public StatementWithoutTrailingSubstatement getStatementWithoutTrailingSubstatement() {
        return statementWithoutTrailingSubstatement;
    }

    @Nullable
    public LabeledStatement getLabeledStatement() {
        return labeledStatement;
    }

    @Nullable
    public IfStatement getIfStatement() {
        return ifStatement;
    }

    @Nullable
    public WhileStatement getWhileStatement() {
        return whileStatement;
    }

    @Nullable
    public ForStatement getForStatement() {
        return forStatement;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "statementWithoutTrailingSubstatement=" + statementWithoutTrailingSubstatement +
                ", labeledStatement=" + labeledStatement +
                ", ifStatement=" + ifStatement +
                ", whileStatement=" + whileStatement +
                ", forStatement=" + forStatement +
                '}';
    }
}
