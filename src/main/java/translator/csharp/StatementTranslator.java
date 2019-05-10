package translator.csharp;

import data.Statement;
import translator.ComponentTranslator;
import utility.Nonnull;

final class StatementTranslator implements ComponentTranslator<Statement> {

    private StatementTranslator() {
        //no instance
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement input, final int indentationCounter) {


    }
}
