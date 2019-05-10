package visitor;

import data.Field;
import data.Statement;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

public class ForStatementVisitor extends Java9BaseVisitor<Statement> {

    private ForStatementVisitor() {
        //no instance
    }

    public static ForStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement visitForStatement(final Java9Parser.ForStatementContext ctx) {
        final FieldVisitor fieldVisitor = FieldVisitor.instance();
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        final StatementWithoutTrailingSubstatementVisitor statementVisitor =
                StatementWithoutTrailingSubstatementVisitor.instance();

        if (ctx.basicForStatement() != null) {
            final Java9Parser.ForInitContext forInitContext = ctx.basicForStatement().forInit();
            final Field initVariable = forInitContext.localVariableDeclaration().accept(fieldVisitor);

            final Java9Parser.ForUpdateContext forUpdateContext = ctx.basicForStatement().forUpdate();
            final Statement.StatementExpression updateStatement;
            if (forUpdateContext.statementExpressionList() != null) {
                //todo investigate if it possible to have multiple statement expressions
                updateStatement = forUpdateContext.statementExpressionList().statementExpression(0)
                        .accept(statementExpressionVisitor);
            } else {
                updateStatement = Statement.StatementExpression.empty();
            }

            final Java9Parser.ExpressionContext expressionContext = ctx.basicForStatement().expression();
            final Statement.StatementExpression expression =
                    expressionContext.accept(statementExpressionVisitor);

            final Statement bodyStatement =
                    ctx.basicForStatement().statement().accept(statementVisitor);

            return Statement.BasicForStatement.of(initVariable, expression,
                    updateStatement, bodyStatement);
        }
        final Field variable = ctx.enhancedForStatement().accept(fieldVisitor);
        final Statement.StatementExpression collection =
                ctx.enhancedForStatement().expression().accept(statementExpressionVisitor);

        final Statement bodyStatement =
                ctx.enhancedForStatement().statement().accept(statementVisitor);

        return Statement.EnhancedForStatement.of(variable, collection, bodyStatement);
    }

    @Override
    public Statement visitForStatementNoShortIf(final Java9Parser.ForStatementNoShortIfContext ctx) {
        final FieldVisitor fieldVisitor = FieldVisitor.instance();
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        final StatementWithoutTrailingSubstatementVisitor statementVisitor = StatementWithoutTrailingSubstatementVisitor.instance();

        if (ctx.basicForStatementNoShortIf() != null) {
            final Java9Parser.ForInitContext forInitContext = ctx.basicForStatementNoShortIf().forInit();
            final Field initVariable = forInitContext.localVariableDeclaration().accept(fieldVisitor);

            final Java9Parser.ForUpdateContext forUpdateContext = ctx.basicForStatementNoShortIf().forUpdate();
            final Statement.StatementExpression updateStatement;
            if (forUpdateContext.statementExpressionList() != null) {
                //todo investigate if it possible to have multiple statement expressions
                updateStatement = forUpdateContext.statementExpressionList().statementExpression(0)
                        .accept(statementExpressionVisitor);
            } else {
                updateStatement = Statement.StatementExpression.empty();
            }

            final Java9Parser.ExpressionContext expressionContext = ctx.basicForStatementNoShortIf().expression();
            final Statement.StatementExpression expression =
                    expressionContext.accept(statementExpressionVisitor);

            final Statement bodyStatement =
                    ctx.basicForStatementNoShortIf().statementNoShortIf().accept(statementVisitor);

            return Statement.BasicForStatement.of(initVariable, expression, updateStatement,
                    bodyStatement);
        }
        final Field variable = ctx.enhancedForStatementNoShortIf().accept(fieldVisitor);
        final Statement.StatementExpression collection =
                ctx.enhancedForStatementNoShortIf().expression().accept(statementExpressionVisitor);

        final Statement bodyStatement =
                ctx.enhancedForStatementNoShortIf().statementNoShortIf().accept(statementVisitor);

        return Statement.EnhancedForStatement.of(variable, collection, bodyStatement);
    }

    private static final class Holder {
        private static final ForStatementVisitor INSTANCE = new ForStatementVisitor();
    }
}
