package visitor;

import data.Field;
import data.statements.*;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;

public class ForStatementVisitor extends Java9BaseVisitor<ForStatement> {

    private ForStatementVisitor() {
        //no instance
    }

    public static ForStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public ForStatement visitForStatement(final Java9Parser.ForStatementContext ctx) {
        final FieldVisitor fieldVisitor = FieldVisitor.instance();
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        final StatementVisitor statementVisitor = StatementVisitor.instance();

        if (ctx.basicForStatement() != null) {
            final Java9Parser.ForInitContext forInitContext = ctx.basicForStatement().forInit();
            final Field initVariable = forInitContext.localVariableDeclaration().accept(fieldVisitor);

            final Java9Parser.ForUpdateContext forUpdateContext = ctx.basicForStatement().forUpdate();
            final StatementWithoutTrailingSubstatement.StatementExpression updateStatement;
            if (forUpdateContext.statementExpressionList() != null) {
                //todo investigate if it possible to have multiple statement expressions
                updateStatement = forUpdateContext.statementExpressionList().statementExpression(0)
                        .accept(statementExpressionVisitor);
            } else {
                updateStatement = StatementWithoutTrailingSubstatement.StatementExpression.empty();
            }

            final Java9Parser.ExpressionContext expressionContext = ctx.basicForStatement().expression();
            final StatementWithoutTrailingSubstatement.StatementExpression expression = expressionContext.accept(statementExpressionVisitor);

            final Statement bodyStatement =
                    ctx.basicForStatement().statement().accept(statementVisitor);

            return new ForStatement(null, new BasicForStatement(initVariable, expression, updateStatement,
                    bodyStatement));
        }
        final Field variable = ctx.enhancedForStatement().accept(fieldVisitor);
        final StatementWithoutTrailingSubstatement.StatementExpression collection =
                ctx.enhancedForStatement().expression().accept(statementExpressionVisitor);

        final Statement bodyStatement = ctx.enhancedForStatement().statement().accept(statementVisitor);

        return new ForStatement(new EnhancedForStatement(variable, collection, bodyStatement), null);
    }

    @Override
    public ForStatement visitForStatementNoShortIf(final Java9Parser.ForStatementNoShortIfContext ctx) {
        final FieldVisitor fieldVisitor = FieldVisitor.instance();
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        final StatementVisitor statementVisitor = StatementVisitor.instance();

        if (ctx.basicForStatementNoShortIf() != null) {
            final Java9Parser.ForInitContext forInitContext = ctx.basicForStatementNoShortIf().forInit();
            final Field initVariable = forInitContext.localVariableDeclaration().accept(fieldVisitor);

            final Java9Parser.ForUpdateContext forUpdateContext = ctx.basicForStatementNoShortIf().forUpdate();
            final StatementWithoutTrailingSubstatement.StatementExpression updateStatement;
            if (forUpdateContext.statementExpressionList() != null) {
                //todo investigate if it possible to have multiple statement expressions
                updateStatement = forUpdateContext.statementExpressionList().statementExpression(0)
                        .accept(statementExpressionVisitor);
            } else {
                updateStatement = StatementWithoutTrailingSubstatement.StatementExpression.empty();
            }

            final Java9Parser.ExpressionContext expressionContext = ctx.basicForStatementNoShortIf().expression();
            final StatementWithoutTrailingSubstatement.StatementExpression expression = expressionContext.accept(statementExpressionVisitor);

            final Statement bodyStatement =
                    ctx.basicForStatementNoShortIf().statementNoShortIf().accept(statementVisitor);

            return new ForStatement(null, new BasicForStatement(initVariable, expression, updateStatement,
                    bodyStatement));
        }
        final Field variable = ctx.enhancedForStatementNoShortIf().accept(fieldVisitor);
        final StatementWithoutTrailingSubstatement.StatementExpression collection =
                ctx.enhancedForStatementNoShortIf().expression().accept(statementExpressionVisitor);

        final Statement bodyStatement =
                ctx.enhancedForStatementNoShortIf().statementNoShortIf().accept(statementVisitor);

        return new ForStatement(new EnhancedForStatement(variable, collection, bodyStatement), null);
    }

    private static final class Holder {
        private static final ForStatementVisitor INSTANCE = new ForStatementVisitor();
    }

}
