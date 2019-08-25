package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.Field;
import pl.draciel.j2csharp.data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;
import org.antlr.v4.runtime.misc.Interval;
import pl.draciel.j2csharp.utility.Nonnull;

import java.util.stream.Collectors;

final class ForStatementVisitor extends Java9BaseVisitor<Statement> {

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
        final StatementVisitor statementVisitor = StatementVisitor.instance();

        if (ctx.basicForStatement() != null) {
            final Java9Parser.ForInitContext forInitContext = ctx.basicForStatement().forInit();
            final Field initVariable = forInitContext.localVariableDeclaration().accept(fieldVisitor);

            final Java9Parser.ForUpdateContext forUpdateContext = ctx.basicForStatement().forUpdate();
            final Statement.StatementExpression updateStatement;
            if (forUpdateContext.statementExpressionList() != null) {
                updateStatement = Statement.StatementExpression.of(forUpdateContext.statementExpressionList()
                        .statementExpression()
                        .stream()
                        .map(ForStatementVisitor::parseStatementExpressionAsString)
                        .collect(Collectors.joining(", ")), true);
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
        final StatementVisitor statementVisitor = StatementVisitor.instance();

        if (ctx.basicForStatementNoShortIf() != null) {
            final Java9Parser.ForInitContext forInitContext = ctx.basicForStatementNoShortIf().forInit();
            final Field initVariable = forInitContext.localVariableDeclaration().accept(fieldVisitor);

            final Java9Parser.ForUpdateContext forUpdateContext = ctx.basicForStatementNoShortIf().forUpdate();
            final Statement.StatementExpression updateStatement;
            if (forUpdateContext.statementExpressionList() != null) {
                updateStatement = Statement.StatementExpression.of(forUpdateContext.statementExpressionList()
                        .statementExpression()
                        .stream()
                        .map(ForStatementVisitor::parseStatementExpressionAsString)
                        .collect(Collectors.joining(", ")), true);
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

    private static String parseStatementExpressionAsString(@Nonnull final Java9Parser.StatementExpressionContext ctx) {
        final int a = ctx.start.getStartIndex();
        final int b = ctx.stop.getStopIndex();
        final Interval interval = new Interval(a, b);
        return ctx.start.getInputStream().getText(interval);
    }

    private static final class Holder {
        private static final ForStatementVisitor INSTANCE = new ForStatementVisitor();
    }
}
