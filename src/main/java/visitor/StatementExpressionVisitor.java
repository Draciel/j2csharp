package visitor;

import data.Statement;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import pl.jcsharp.grammar.Java9BaseVisitor;
import pl.jcsharp.grammar.Java9Parser;
import utility.Nonnull;

// Visitor dedicated for simple statements, initializers, expressions.
class StatementExpressionVisitor extends Java9BaseVisitor<Statement.StatementExpression> {

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
        return Statement.StatementExpression.of(parseRuleAsString(ctx));
    }

    @Override
    public Statement.StatementExpression visitExpression(final Java9Parser.ExpressionContext ctx) {
        if (ctx.getChildCount() == 0) {
            return Statement.StatementExpression.empty();
        }
        return Statement.StatementExpression.of(parseRuleAsString(ctx));
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
