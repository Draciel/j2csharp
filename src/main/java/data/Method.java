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

    @Nonnull
    private final List<Modifier> modifiers;

    public Method(@Nonnull final String name,
                  @Nonnull final List<Parameter> parameters,
                  @Nonnull final List<Statement> statements,
                  @Nonnull final List<Modifier> modifiers) {
        this.name = name;
        this.parameters = parameters;
        this.statements = statements;
        this.modifiers = modifiers;
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

    @Nonnull
    public List<Modifier> getModifiers() {
        return modifiers;
    }

    @Override
    public String toString() {
        return "Method{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                ", statements=" + statements +
                ", modifiers=" + modifiers +
                '}';
    }
}
