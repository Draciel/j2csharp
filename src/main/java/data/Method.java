package data;

import utility.Nonnull;

import java.util.List;

public class Method {

    @Nonnull
    private final String name;

    @Nonnull
    private final List<Parameter> parameters;

    @Nonnull
    private final List<Statement> statements;

    public Method(@Nonnull final String name,
                  @Nonnull final List<Parameter> parameters,
                  @Nonnull final List<Statement> statements) {
        this.name = name;
        this.parameters = parameters;
        this.statements = statements;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public List<Parameter> getParameters() {
        return parameters;
    }

    @Nonnull
    public List<Statement> getStatements() {
        return statements;
    }

    @Override
    public String toString() {
        return "Method{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                ", statements=" + statements +
                '}';
    }
}
