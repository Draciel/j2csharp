package pl.draciel.j2csharp.translator.csharp;

import pl.draciel.j2csharp.data.Statement;
import pl.draciel.j2csharp.translator.ComponentTranslator;
import pl.draciel.j2csharp.utility.Nonnull;

import java.util.HashMap;

final class StatementBootstrap implements ComponentTranslator<Statement.StatementExpression> {

    private final HashMap<String, String> statementTranslationMap = new HashMap<>();

    private StatementBootstrap() {
        statementTranslationMap.put("System.out.println", "Console.WriteLine");
        statementTranslationMap.put("super", "base");
    }

    public static StatementBootstrap instance() {
        return Holder.INSTANCE;
    }

    @Nonnull
    @Override
    public String translate(@Nonnull final Statement.StatementExpression input, final int indentationCounter) {
        if (input.isExpression() || input.getContent().isEmpty()) {
            return input.getContent();
        }

        return statementTranslationMap.entrySet().stream()
                .filter(e -> input.getContent().contains(e.getKey()))
                .findFirst()
                .map(e -> input.getContent().replace(e.getKey(), e.getValue()))
                .orElseGet(input::getContent);
    }

    private static final class Holder {
        private static final StatementBootstrap INSTANCE = new StatementBootstrap();
    }
}
