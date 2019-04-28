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

    @Nonnull
    private final List<Annotation> annotations;

    public Constructor(@Nonnull final List<Parameter> parameters,
                       @Nonnull final List<Statement> statements,
                       @Nonnull final List<Modifier> modifiers,
                       @Nonnull final List<Annotation> annotations) {
        this.parameters = parameters;
        this.statements = statements;
        this.modifiers = modifiers;
        this.annotations = annotations;
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

    public List<Annotation> getAnnotations() {
        return annotations;
    }

    @Override
    public String toString() {
        return "Constructor{" +
                "parameters=" + parameters +
                ", statements=" + statements +
                ", modifiers=" + modifiers +
                ", annotations=" + annotations +
                '}';
    }
}
