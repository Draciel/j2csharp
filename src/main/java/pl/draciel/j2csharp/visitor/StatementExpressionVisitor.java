package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import pl.draciel.j2csharp.utility.Nonnull;

// Visitor dedicated for simple statements, initializers, expressions.
final class StatementExpressionVisitor extends Java9BaseVisitor<Statement.StatementExpression> {

    private StatementExpressionVisitor() {
    }

    public static StatementExpressionVisitor instance() {
        return HOLDER.INSTANCE;
    }

    @Override
    public Statement.StatementExpression visitStatementExpression(final Java9Parser.StatementExpressionContext ctx) {
        if (ctx.getChildCount() == 0) {
            return Statement.StatementExpression.empty();
        }
        return Statement.StatementExpression.of(parseRuleAsString(ctx), false);
    }

    //fixme slice this class and pojo to 2 separate visitors to avoid cluttering with extra field
    @Override
    public Statement.StatementExpression visitExpression(final Java9Parser.ExpressionContext ctx) {
        if (ctx.getChildCount() == 0) {
            return Statement.StatementExpression.empty();
        }
        return Statement.StatementExpression.of(parseRuleAsString(ctx),  true);
    }

    private static String parseRuleAsString(@Nonnull final ParserRuleContext ctx) {
        final int a = ctx.start.getStartIndex();
        final int b = ctx.stop.getStopIndex();
        final Interval interval = new Interval(a, b);
        return ctx.start.getInputStream().getText(interval);
    }

    private static final class HOLDER {
        private static final StatementExpressionVisitor INSTANCE = new StatementExpressionVisitor();
    }
}
