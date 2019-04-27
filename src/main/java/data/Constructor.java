package data;

import utility.Nonnull;

import java.util.List;

public class Constructor {

    @Nonnull
    private final List<Parameter> parameters;

    @Nonnull
    private final List<Statement> statements;

    @Nonnull
    private final List<Modifier> modifiers;

    public Constructor(@Nonnull final List<Parameter> parameters,
                       @Nonnull final List<Statement> statements,
                       @Nonnull final List<Modifier> modifiers) {
        this.parameters = parameters;
        this.statements = statements;
        this.modifiers = modifiers;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public List<Statement> getStatements() {
        return statements;
    }

    public List<Modifier> getModifiers() {
        return modifiers;
    }

    @Override
    public String toString() {
        return "Constructor{" +
                "parameters=" + parameters +
                ", statements=" + statements +
                ", modifiers=" + modifiers +
                '}';
    }
}
