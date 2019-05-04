package translator.csharp;

import data.Modifier;
import data.Parameter;
import data.Statement;
import utility.Nonnull;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Utility {

    private Utility() {
        //no instance
    }

    public static boolean isAvailableModifier(@Nonnull final Modifier modifier) {
        return modifier == Modifier.PUBLIC || modifier == Modifier.PRIVATE || modifier == Modifier.PROTECTED ||
                modifier == Modifier.STATIC || modifier == Modifier.VOLATILE || modifier == Modifier.PACKAGE;
    }

    // fixme we need a bit different approach for modifiers
    public static String appendModifiers(@Nonnull final List<Modifier> modifiers) {
        return modifiers.stream()
                .filter(Utility::isAvailableModifier)
                .map(Utility::translateModifiers)
                .collect(Collectors.joining(Codestyle.space()));
    }

    public static String translateModifiers(@Nonnull final Modifier modifier) {
        if (modifier == Modifier.PACKAGE) {
            return "internal";
        }
        return modifier.getCodeRepresentation();
    }

    public static String appendIndentation(final int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> Codestyle.indentation())
                .collect(Collectors.joining());
    }

    public static String appendInitializer(@Nonnull final Statement statement) {
        if (!statement.getContent().isEmpty()) {
            return Codestyle.space() + "=" + Codestyle.space() + statement.getContent();
        }
        return statement.getContent();
    }

    public static String appendParameters(@Nonnull final List<Parameter> parameters) {
        return parameters.stream()
                .map(p -> p.getType() + Codestyle.space() + p.getName())
                .collect(Collectors.joining("," + Codestyle.space()));
    }

    public static String appendStatements(@Nonnull final List<Statement> statements, final int indentation) {
        return statements.stream()
                .map(s -> appendIndentation(indentation) + s.getContent())
                .collect(Collectors.joining(Codestyle.newLine()));
    }

    public static String appendInheritance(@Nonnull final String superClass,
                                           @Nonnull final List<String> superInterfaces) {
        if (superClass.isEmpty() && superInterfaces.isEmpty()) {
            return Codestyle.space();
        }

        final String prefixSuperInterfaces = superClass.isEmpty() || superInterfaces.isEmpty() ? "" : "," + Codestyle.space();

        final String formattedSuperInterfaces = superInterfaces.stream().collect(Collectors.joining(
                "," + Codestyle.space(), prefixSuperInterfaces, ""));

        return Codestyle.space() + ":" + Codestyle.space() + superClass + formattedSuperInterfaces + Codestyle.space();
    }
}
