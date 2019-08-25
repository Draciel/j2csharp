package pl.draciel.j2csharp.translator.csharp;

import pl.draciel.j2csharp.data.Statement;
import pl.draciel.j2csharp.translator.ComponentTranslator;
import pl.draciel.j2csharp.utility.Nonnull;

final class BreakStatementTranslator implements ComponentTranslator<Statement.BreakStatement> {

    private BreakStatementTranslator() {
        //no instance
    }

    public static BreakStatementTranslator instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.BreakStatement input, final int indentationCounter) {
        if (input.getIdentifier() != null) {
            throw new UnsupportedOperationException("Break labels are unsupported in c#");
        }
        return Utility.appendIndentation(indentationCounter) + "break" + ";";
    }

    private static final class Holder {
        private static final BreakStatementTranslator INSTANCE = new BreakStatementTranslator();
    }
}
