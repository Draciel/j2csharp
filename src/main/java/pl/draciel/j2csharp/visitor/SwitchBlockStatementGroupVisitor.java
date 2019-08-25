package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.BlockStatement;
import pl.draciel.j2csharp.data.Statement;
import generated.Java9BaseVisitor;
import generated.Java9Parser;
import pl.draciel.j2csharp.utility.Nonnull;

import java.util.List;
import java.util.stream.Collectors;

final class SwitchBlockStatementGroupVisitor extends Java9BaseVisitor<Statement.SwitchBlockStatementGroup> {

    private SwitchBlockStatementGroupVisitor() {
        //no instance
    }

    public static SwitchBlockStatementGroupVisitor instance() {
        return Holder.INSTANCE;
    }

    @Override
    public Statement.SwitchBlockStatementGroup visitSwitchBlockStatementGroup(final Java9Parser.SwitchBlockStatementGroupContext ctx) {
        final BlockStatementVisitor blockStatementVisitor = BlockStatementVisitor.instance();

        final List<BlockStatement> blockStatements = ctx.blockStatements()
                .blockStatement()
                .stream()
                .map(bs -> bs.accept(blockStatementVisitor))
                .collect(Collectors.toList());

        final List<String> labels = ctx.switchLabels()
                .switchLabel()
                .stream()
                .map(SwitchBlockStatementGroupVisitor::parseSwitchLabel)
                .collect(Collectors.toList());

        return Statement.SwitchBlockStatementGroup.of(blockStatements, labels);
    }

    private static String parseSwitchLabel(@Nonnull final Java9Parser.SwitchLabelContext context) {
        if (context.enumConstantName() != null) {
            return context.enumConstantName().getText();
        } else if (context.constantExpression() != null) {
            return context.constantExpression().getText();
        } else {
            return "default";
        }
    }

    private static final class Holder {
        private static final SwitchBlockStatementGroupVisitor INSTANCE = new SwitchBlockStatementGroupVisitor();
    }
}
