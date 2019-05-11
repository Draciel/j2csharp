package translator.csharp;

import data.*;
import utility.Nonnull;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

final class Utility {

    private Utility() {
        //no instance
    }

    public static String appendModifiers(@Nonnull final List<Modifier> modifiers) {
        if (modifiers.isEmpty()) {
            return "";
        }
        return modifiers.stream()
                .map(Modifier::getCodeRepresentation)
                .collect(Collectors.joining(Codestyle.space()));
    }

    public static String appendIndentation(final int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> Codestyle.indentation())
                .collect(Collectors.joining());
    }

    public static String appendInitializer(@Nonnull final Statement.StatementExpression statement) {
        if (!statement.getContent().isEmpty()) {
            return Codestyle.space() + "=" + Codestyle.space() + statement.getContent();
        }
        return statement.getContent();
    }

    public static String appendParameters(@Nonnull final List<Parameter> parameters) {
        final TypeBootstrap bootstrap = TypeBootstrap.instance();
        return parameters.stream()
                .map(p -> !p.isVararg() ? bootstrap.translate(p.getType(), 0) + Codestyle.space() + p.getName() :
                        "params" + Codestyle.space() + bootstrap.translate(p.getType(), 0) + "[]" + Codestyle.space() + p.getName())
                .collect(Collectors.joining("," + Codestyle.space()));
    }

    public static String appendPassedParameters(@Nonnull final List<String> passedParameters) {
        return passedParameters.stream()
                .collect(Collectors.joining("," + Codestyle.space()));
    }

    public static String appendInheritance(@Nonnull final Type superClass,
                                           @Nonnull final List<Type> superInterfaces) {
        if (superClass.literal().isEmpty() && superInterfaces.isEmpty()) {
            return Codestyle.space();
        }

        final TypeBootstrap typeBootstrap = TypeBootstrap.instance();
        final String prefixSuperInterfaces = superClass.literal().isEmpty() || superInterfaces.isEmpty() ? "" :
                "," + Codestyle.space();

        final String formattedSuperInterfaces = superInterfaces.stream()
                .map(t -> typeBootstrap.translate(t, 0))
                .collect(Collectors.joining("," + Codestyle.space(), prefixSuperInterfaces, ""));

        return Codestyle.space() + ":" + Codestyle.space() + typeBootstrap.translate(superClass, 0) + formattedSuperInterfaces;
    }

    public static String appendGenericsBounds(@Nonnull final List<Generic> generics, final int indentation) {
        final StringBuilder builder = new StringBuilder();
        generics.stream()
                .filter(filterWildcards())
                .filter(g -> !(g.getBoundedType().isEmpty() && g.getType() == null))
                .forEach(g -> builder
                        .append(Codestyle.newLine())
                        .append(appendIndentation(indentation))
                        .append("where")
                        .append(Codestyle.space())
                        .append(g.getTypeParameter().literal())
                        .append(Codestyle.space())
                        .append(":")
                        .append(Codestyle.space())
                        .append(formatNestedGeneric(g)));

        return builder.toString();
    }

    public static String appendGenericsParameters(@Nonnull final List<Generic> generics) {
        if (generics.isEmpty()) {
            return "";
        }

        final StringBuilder builder = new StringBuilder();

        builder.append("<")
                .append(generics.stream()
                        .filter(filterWildcards())
                        .map(g -> g.getTypeParameter().literal())
                        .collect(Collectors.joining("," + Codestyle.space())))
                .append(">");

        return builder.toString();
    }

    private static Predicate<? super Generic> filterWildcards() {
        return g -> {
            if (g.isWildcard()) {
                System.out.println("Wildcards are not supported in C#");
            }
            return !g.isWildcard();
        };
    }

    private static String formatNestedGeneric(@Nonnull final Generic generic) {
        final TypeBootstrap bootstrap = TypeBootstrap.instance();
        if (generic.getType() != null) {
            return bootstrap.translate(generic.getType(), 0);
        }

        if (generic.getBoundedType().isEmpty()) {
            return bootstrap.translate(generic.getTypeParameter(), 0);
        }

        final String type = bootstrap.translate(generic.getBoundedType().get(0).getType(), 0);

        final StringBuilder builder = new StringBuilder();

        return builder.append(type)
                .append("<")
                .append(generic.getBoundedType().stream()
                        .filter(filterWildcards())
                        .map(Generic::getTypeParameter)
                        .map(t -> bootstrap.translate(t, 0))
                        .collect(Collectors.joining("," + Codestyle.space())))
                .append(">")
                .toString();
    }

}
