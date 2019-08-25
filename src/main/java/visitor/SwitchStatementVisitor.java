package visitor;

import data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;

import java.util.List;
import java.util.stream.Collectors;

final class SwitchStatementVisitor extends Java9BaseVisitor<Statement.SwitchStatement> {

    private SwitchStatementVisitor() {
        //no instance
    }

    public static SwitchStatementVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.SwitchStatement visitSwitchStatement(final Java9Parser.SwitchStatementContext ctx) {
        final StatementExpressionVisitor statementExpressionVisitor = StatementExpressionVisitor.instance();
        final SwitchBlockStatementGroupVisitor switchBlockStatementGroupVisitor =
                SwitchBlockStatementGroupVisitor.instance();

        final Statement.StatementExpression statementExpression = ctx.expression().accept(statementExpressionVisitor);

        final List<Statement.SwitchBlockStatementGroup> switchBlockStatementGroups = ctx.switchBlock()
                .switchBlockStatementGroup()
                .stream()
                .map(sbsg -> sbsg.accept(switchBlockStatementGroupVisitor))
                .collect(Collectors.toList());

        return Statement.SwitchStatement.of(statementExpression, switchBlockStatementGroups);
    }

    private static final class Holder {
        private static final SwitchStatementVisitor INSTANCE = new SwitchStatementVisitor();
    }
}
