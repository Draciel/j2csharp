package pl.draciel.j2csharp.translator.csharp;

import pl.draciel.j2csharp.data.Statement;
import pl.draciel.j2csharp.translator.ComponentTranslator;
import pl.draciel.j2csharp.utility.Nonnull;

final class DoStatementTranslator implements ComponentTranslator<Statement.DoStatement> {

    private DoStatementTranslator() {
        //no instance
    }

    public static DoStatementTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.DoStatement input, final int indentationCounter) {
        final StringBuilder stringBuilder = new StringBuilder();
        final StatementTranslator statementTranslator = StatementTranslator.instance();
        stringBuilder.append(Utility.appendIndentation(indentationCounter))
                .append("do")
                .append(Codestyle.space())
                .append("{")
                .append(statementTranslator.translate(input.getStatement(), indentationCounter + 1))
                .append(Codestyle.newLine())
                .append(Utility.appendIndentation(indentationCounter))
                .append("}")
                .append(Codestyle.space())
                .append("while")
                .append("(")
                .append(statementTranslator.translate(input.getExpression(), indentationCounter))
                .append(")");

        return stringBuilder.toString();
    }

    private static final class Holder {
        private static final DoStatementTranslator INSTANCE = new DoStatementTranslator();
    }
}
