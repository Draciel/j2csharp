package data;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utility.Nonnull;
import utility.Nullable;

import java.util.List;
import java.util.Objects;

import static data.Statement.ComplexStatementType.*;

//todo find better name
public abstract class Statement {

    @Nonnull
    private final ComplexStatementType type;

    private Statement(@Nonnull final ComplexStatementType type) {
        this.type = type;
    }

    @Nonnull
    public ComplexStatementType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Statement{" +
                "type=" + type +
                '}';
    }

    public enum ComplexStatementType {

        SYNCHRONIZED("synchronized"), RETURN("return"), BLOCK(""), ASSERT("assert"), BREAK("break"),
        CONTINUE("continue"), DO("do"), SWITCH("switch"), EMPTY(""), THROW("throw"), TRY("try"),
        TRY_WITH_RESOURCES("try"), STATEMENT_EXPRESSION(""), IF("if"), WHILE("while"),
        BASIC_FOR("for"), ENHANCED_FOR("for");

        //todo this is useless
        private final String keyword;

        ComplexStatementType(final String keyword) {
            this.keyword = keyword;
        }

        public String getKeyword() {
            return keyword;
        }
    }

    public static final class SynchronizedStatement extends Statement {

        @Nonnull
        private final Block block;

        @Nonnull
        private final StatementExpression expression;

        private SynchronizedStatement(@Nonnull final ComplexStatementType type, @Nonnull final Block block,
                                      @Nonnull final StatementExpression expression) {
            super(type);
            this.block = block;
            this.expression = expression;
        }

        public static SynchronizedStatement of(@Nonnull final Block block,
                                               @Nonnull final StatementExpression statementExpression) {
            return new SynchronizedStatement(ComplexStatementType.SYNCHRONIZED, block, statementExpression);
        }

        @Nonnull
        public Block getBlock() {
            return block;
        }

        @Nonnull
        public StatementExpression getExpression() {
            return expression;
        }

        @Override
        public String toString() {
            return "SynchronizedStatement{" +
                    "block=" + block +
                    ", expression=" + expression +
                    "} " + super.toString();
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof SynchronizedStatement)) return false;
            final SynchronizedStatement that = (SynchronizedStatement) o;
            return Objects.equals(block, that.block) &&
                    Objects.equals(expression, that.expression);
        }

        @Override
        public int hashCode() {
            return Objects.hash(block, expression);
        }
    }

    public static final class ReturnStatement extends Statement {

        @Nonnull
        private final StatementExpression returnStatement;

        private ReturnStatement(@Nonnull final ComplexStatementType type,
                                @Nonnull final StatementExpression returnStatement) {
            super(type);
            this.returnStatement = returnStatement;
        }

        public static ReturnStatement of(@Nonnull final StatementExpression returnStatement) {
            return new ReturnStatement(ComplexStatementType.RETURN, returnStatement);
        }

        @Nonnull
        public StatementExpression getReturnStatement() {
            return returnStatement;
        }

        @Override
        public String toString() {
            return "ReturnStatement{" +
                    "expression=" + returnStatement +
                    "} " + super.toString();
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof ReturnStatement)) return false;
            final ReturnStatement that = (ReturnStatement) o;
            return Objects.equals(returnStatement, that.returnStatement);
        }

        @Override
        public int hashCode() {
            return Objects.hash(returnStatement);
        }
    }

    public static final class Block extends Statement {

        @Nonnull
        private final List<BlockStatement> blockStatement;

        private Block(@Nonnull final ComplexStatementType type, @Nonnull final List<BlockStatement> blockStatement) {
            super(type);
            this.blockStatement = blockStatement;
        }

        public static Block of(@Nonnull final List<BlockStatement> statementExpressions) {
            return new Block(BLOCK, statementExpressions);
        }

        @Nonnull
        public List<BlockStatement> getBlockStatement() {
            return blockStatement;
        }

        @Override
        public String toString() {
            return "Block{" +
                    "blockStatement=" + blockStatement +
                    "} " + super.toString();
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof Block)) return false;
            final Block that = (Block) o;
            return Objects.equals(blockStatement, that.blockStatement);
        }

        @Override
        public int hashCode() {
            return Objects.hash(blockStatement);
        }
    }

    public static final class AssertStatement extends Statement {

        @Nonnull
        private final List<StatementExpression> statementExpressions;

        private AssertStatement(@Nonnull final ComplexStatementType type,
                                @Nonnull final List<StatementExpression> statementExpressions) {
            super(type);
            this.statementExpressions = statementExpressions;
        }

        public static AssertStatement of(@Nonnull final List<StatementExpression> statementExpression) {
            return new AssertStatement(ASSERT, statementExpression);
        }

        @Nonnull
        public List<StatementExpression> getStatementExpressions() {
            return statementExpressions;
        }

        @Override
        public String toString() {
            return "AssertStatement{" +
                    "statementExpressions=" + statementExpressions +
                    "} " + super.toString();
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof AssertStatement)) return false;
            final AssertStatement that = (AssertStatement) o;
            return Objects.equals(statementExpressions, that.statementExpressions);
        }

        @Override
        public int hashCode() {
            return Objects.hash(statementExpressions);
        }
    }

    public static final class BreakStatement extends Statement {

        @Nonnull
        private final String identifier;

        private BreakStatement(@Nonnull final ComplexStatementType type, @Nonnull final String identifier) {
            super(type);
            this.identifier = identifier;
        }

        public static BreakStatement of(@Nonnull final String identifier) {
            return new BreakStatement(BREAK, identifier);
        }

        @Nonnull
        public String getIdentifier() {
            return identifier;
        }

        @Override
        public String toString() {
            return "BreakStatement{" +
                    "identifier='" + identifier + '\'' +
                    "} " + super.toString();
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof BreakStatement)) return false;
            final BreakStatement that = (BreakStatement) o;
            return Objects.equals(identifier, that.identifier);
        }

        @Override
        public int hashCode() {
            return Objects.hash(identifier);
        }
    }

    public static final class ContinueStatement extends Statement {

        @Nonnull
        private final String identifier;

        private ContinueStatement(@Nonnull final ComplexStatementType type, @Nonnull final String identifier) {
            super(type);
            this.identifier = identifier;
        }

        public static ContinueStatement of(@Nonnull final String identifier) {
            return new ContinueStatement(CONTINUE, identifier);
        }

        @Nonnull
        public String getIdentifier() {
            return identifier;
        }

        @Override
        public String toString() {
            return "ContinueStatement{" +
                    "identifier='" + identifier + '\'' +
                    "} " + super.toString();
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof ContinueStatement)) return false;
            final ContinueStatement that = (ContinueStatement) o;
            return Objects.equals(identifier, that.identifier);
        }

        @Override
        public int hashCode() {
            return Objects.hash(identifier);
        }
    }

    public static final class DoStatement extends Statement {

        @Nonnull
        private final StatementExpression expression;

        @Nonnull
        private final Statement statement;

        private DoStatement(@Nonnull final ComplexStatementType type, @Nonnull final StatementExpression expression,
                            @Nonnull final Statement statement) {
            super(type);
            this.expression = expression;
            this.statement = statement;
        }

        public static DoStatement of(@Nonnull final StatementExpression expression,
                                     @Nonnull final Statement statement) {
            return new DoStatement(DO, expression, statement);
        }

        @Nonnull
        public StatementExpression getExpression() {
            return expression;
        }

        @Nonnull
        public Statement getStatement() {
            return statement;
        }

        @Override
        public String toString() {
            return "DoStatement{" +
                    "expression=" + expression +
                    ", statement=" + statement +
                    "} " + super.toString();
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof DoStatement)) return false;
            final DoStatement that = (DoStatement) o;
            return Objects.equals(expression, that.expression) &&
                    Objects.equals(statement, that.statement);
        }

        @Override
        public int hashCode() {
            return Objects.hash(expression, statement);
        }
    }

    public static final class SwitchStatement extends Statement {

        @Nonnull
        private final StatementExpression statementExpression;

        @Nonnull
        private final List<Statement> statements;

        private SwitchStatement(@Nonnull final ComplexStatementType type,
                                @Nonnull final StatementExpression statementExpression,
                                @Nonnull final List<Statement> statements) {
            super(type);
            this.statementExpression = statementExpression;
            this.statements = statements;
        }

        public static SwitchStatement of(@Nonnull final StatementExpression statementExpression,
                                         @Nonnull final List<Statement> statements) {
            return new SwitchStatement(SWITCH, statementExpression, statements);
        }

        @Nonnull
        public StatementExpression getStatementExpression() {
            return statementExpression;
        }

        @Nonnull
        public List<Statement> getStatements() {
            return statements;
        }

        @Override
        public String toString() {
            return "SwitchStatement{" +
                    "statementExpressions=" + statementExpression +
                    ", statements=" + statements +
                    "} " + super.toString();
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof SwitchStatement)) return false;
            final SwitchStatement that = (SwitchStatement) o;
            return Objects.equals(statementExpression, that.statementExpression) &&
                    Objects.equals(statements, that.statements);
        }

        @Override
        public int hashCode() {
            return Objects.hash(statementExpression, statements);
        }
    }

    // todo investigate if it is needed
    public static final class EmptyStatement extends Statement {

        private EmptyStatement(@Nonnull final ComplexStatementType type) {
            super(type);
        }

        public static EmptyStatement instance() {
            return Holder.INSTANCE;
        }

        @Override
        public String toString() {
            return "EmptyStatement{} " + super.toString();
        }

        private static final class Holder {
            private static final EmptyStatement INSTANCE = new EmptyStatement(EMPTY);
        }
    }

    public static final class ThrowStatement extends Statement {

        @Nonnull
        private final StatementExpression statementExpression;

        private ThrowStatement(@Nonnull final ComplexStatementType type,
                               @Nonnull final StatementExpression statementExpression) {
            super(type);
            this.statementExpression = statementExpression;
        }

        public static ThrowStatement of(@Nonnull final StatementExpression statementExpression) {
            return new ThrowStatement(THROW, statementExpression);
        }

        @Nonnull
        public StatementExpression getStatementExpression() {
            return statementExpression;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof ThrowStatement)) return false;
            final ThrowStatement that = (ThrowStatement) o;
            return Objects.equals(statementExpression, that.statementExpression);
        }

        @Override
        public int hashCode() {
            return Objects.hash(statementExpression);
        }

        @Override
        public String toString() {
            return "ThrowStatement{" +
                    "statementExpressions=" + statementExpression +
                    "} " + super.toString();
        }
    }

    public static final class TryStatement extends Statement {

        @Nonnull
        private final Block tryBlock;

        @Nonnull
        private final List<CatchClauseStatement> catchClauseStatements;

        @Nonnull
        private final Block finallyBlock;

        private TryStatement(@Nonnull final ComplexStatementType type, @Nonnull final Block tryBlock,
                             @Nonnull final List<CatchClauseStatement> catchClauseStatements,
                             @Nonnull final Block finallyBlock) {
            super(type);
            this.tryBlock = tryBlock;
            this.catchClauseStatements = catchClauseStatements;
            this.finallyBlock = finallyBlock;
        }

        public static TryStatement of(@Nonnull final Block tryBlock,
                                      @Nonnull final List<CatchClauseStatement> catchClauseStatements,
                                      @Nonnull final Block finallyBlock) {
            return new TryStatement(TRY, tryBlock, catchClauseStatements, finallyBlock);
        }

        @Nonnull
        public Block getTryBlock() {
            return tryBlock;
        }

        @Nonnull
        public List<CatchClauseStatement> getCatchClauseStatements() {
            return catchClauseStatements;
        }

        @Nonnull
        public Block getFinallyBlock() {
            return finallyBlock;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof TryStatement)) return false;
            final TryStatement that = (TryStatement) o;
            return Objects.equals(tryBlock, that.tryBlock) &&
                    Objects.equals(catchClauseStatements, that.catchClauseStatements) &&
                    Objects.equals(finallyBlock, that.finallyBlock);
        }

        @Override
        public int hashCode() {
            return Objects.hash(tryBlock, catchClauseStatements, finallyBlock);
        }

        @Override
        public String toString() {
            return "TryStatement{" +
                    "tryBlock=" + tryBlock +
                    ", catchClauseStatements=" + catchClauseStatements +
                    ", finallyBlock=" + finallyBlock +
                    "} " + super.toString();
        }
    }

    public static final class TryWithResourcesStatement extends Statement {

        @Nonnull
        private final Block tryBlock;

        @Nonnull
        private final List<CatchClauseStatement> catchClauseStatements;

        @Nonnull
        private final Block finallyBlock;

        @Nonnull
        private final List<Field> resources;

        private TryWithResourcesStatement(@Nonnull final ComplexStatementType type, @Nonnull final Block tryBlock,
                                          @Nonnull final List<CatchClauseStatement> catchClauseStatements,
                                          @Nonnull final Block finallyBlock, @Nonnull final List<Field> resources) {
            super(type);
            this.tryBlock = tryBlock;
            this.catchClauseStatements = catchClauseStatements;
            this.finallyBlock = finallyBlock;
            this.resources = resources;
        }

        public static TryWithResourcesStatement of(@Nonnull final Block tryBlock,
                                                   @Nonnull final List<CatchClauseStatement> catchClauseStatements,
                                                   @Nonnull final Block finallyBlock,
                                                   @Nonnull final List<Field> resources) {
            return new TryWithResourcesStatement(TRY_WITH_RESOURCES, tryBlock, catchClauseStatements, finallyBlock,
                    resources);
        }

        @Nonnull
        public Block getTryBlock() {
            return tryBlock;
        }

        @Nonnull
        public List<CatchClauseStatement> getCatchClauseStatements() {
            return catchClauseStatements;
        }

        @Nonnull
        public Block getFinallyBlock() {
            return finallyBlock;
        }

        @Nonnull
        public List<Field> getResources() {
            return resources;
        }

        @Override
        public String toString() {
            return "TryWithResourcesStatement{" +
                    "tryBlock=" + tryBlock +
                    ", catchClauseStatements=" + catchClauseStatements +
                    ", finallyBlock=" + finallyBlock +
                    ", resources=" + resources +
                    "} " + super.toString();
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (!(o instanceof TryWithResourcesStatement)) return false;
            final TryWithResourcesStatement that = (TryWithResourcesStatement) o;
            return Objects.equals(tryBlock, that.tryBlock) &&
                    Objects.equals(catchClauseStatements, that.catchClauseStatements) &&
                    Objects.equals(finallyBlock, that.finallyBlock) &&
                    Objects.equals(resources, that.resources);
        }

        @Override
        public int hashCode() {
            return Objects.hash(tryBlock, catchClauseStatements, finallyBlock, resources);
        }
    }

    public static final class StatementExpression extends Statement {

        private static final StatementExpression EMPTY_STATEMENT_EXPRESSION =
                new StatementExpression(STATEMENT_EXPRESSION, "");

        @Nonnull
        private final String content;

        private StatementExpression(@Nonnull final ComplexStatementType type,
                                    @Nonnull final String content) {
            super(type);
            this.content = content;
        }

        public static StatementExpression of(@Nonnull final String content) {
            return new StatementExpression(STATEMENT_EXPRESSION, content);
        }

        public static StatementExpression empty() {
            return EMPTY_STATEMENT_EXPRESSION;
        }

        @Nonnull
        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return "StatementExpression{" +
                    "content='" + content + '\'' +
                    '}';
        }
    }

    public static class LabeledStatement extends Statement {

        private LabeledStatement(@Nonnull final ComplexStatementType type) {
            super(type);
        }

        public static LabeledStatement of() {
            throw new NotImplementedException();
        }

    }

    public static class IfStatement extends Statement {

        @Nonnull
        private final StatementExpression conditional;

        @Nonnull
        private final Statement ifBodyStatement;

        @Nullable
        private final Statement elseBodyStatement;


        private IfStatement(@Nonnull final ComplexStatementType type,
                            @Nonnull final StatementExpression conditional,
                            @Nonnull final Statement ifBodyStatement,
                            @Nullable final Statement elseBodyStatement) {
            super(type);
            this.conditional = conditional;
            this.ifBodyStatement = ifBodyStatement;
            this.elseBodyStatement = elseBodyStatement;
        }

        public static IfStatement of(@Nonnull final StatementExpression conditional,
                                     @Nonnull final Statement ifBodyStatement,
                                     @Nullable final Statement elseBodyStatement) {
            return new IfStatement(IF, conditional, ifBodyStatement, elseBodyStatement);
        }

        @Nonnull
        public StatementExpression getConditional() {
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

    public static class WhileStatement extends Statement {

        @Nonnull
        private final StatementExpression condition;

        @Nullable
        private final Statement whileBody;

        private WhileStatement(@Nonnull final ComplexStatementType type,
                               @Nonnull final StatementExpression condition,
                               @Nullable final Statement whileBody) {
            super(type);
            this.condition = condition;
            this.whileBody = whileBody;
        }

        public static WhileStatement of(@Nonnull final StatementExpression condition,
                                        @Nullable final Statement whileBody) {
            return new WhileStatement(WHILE, condition, whileBody);
        }

        @Nonnull
        public StatementExpression getCondition() {
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

    public static class BasicForStatement extends Statement {

        @Nullable
        private final Field initVariable;

        @Nullable
        private final StatementExpression expression;

        @Nullable
        private final StatementExpression updateStatement;

        @Nonnull
        private final Statement bodyStatement;


        private BasicForStatement(@Nonnull final ComplexStatementType type,
                                  @Nullable final Field initVariable,
                                  @Nullable final StatementExpression expression,
                                  @Nullable final StatementExpression updateStatement,
                                  @Nonnull final Statement bodyStatement) {

            super(type);
            this.initVariable = initVariable;
            this.expression = expression;
            this.updateStatement = updateStatement;
            this.bodyStatement = bodyStatement;
        }

        public static BasicForStatement of(@Nullable final Field initVariable,
                                           @Nullable final StatementExpression expression,
                                           @Nullable final StatementExpression updateStatement,
                                           @Nonnull final Statement bodyStatement) {
            return new BasicForStatement(BASIC_FOR, initVariable, expression, updateStatement, bodyStatement);
        }

        @Nullable
        public Field getInitVariable() {
            return initVariable;
        }

        @Nullable
        public StatementExpression getExpression() {
            return expression;
        }

        @Nullable
        public StatementExpression getUpdateStatement() {
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

    public static class EnhancedForStatement extends Statement {

        @Nonnull
        private final Field variable;

        @Nonnull
        private final StatementExpression collection;

        @Nonnull
        private final Statement bodyStatement;

        private EnhancedForStatement(@Nonnull final ComplexStatementType type,
                                     @Nonnull final Field variable,
                                     @Nonnull final StatementExpression collection,
                                     @Nonnull final Statement bodyStatement) {
            super(type);
            this.variable = variable;
            this.collection = collection;
            this.bodyStatement = bodyStatement;
        }

        public static EnhancedForStatement of(@Nonnull final Field variable,
                                              @Nonnull final StatementExpression collection,
                                              @Nonnull final Statement bodyStatement) {
            return new EnhancedForStatement(ENHANCED_FOR, variable, collection, bodyStatement);
        }

        @Nonnull
        public Field getVariable() {
            return variable;
        }

        @Nonnull
        public StatementExpression getCollection() {
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
}
