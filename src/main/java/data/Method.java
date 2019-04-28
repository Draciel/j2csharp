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

    @Nonnull
    private final List<Annotation> annotations;

    public Method(@Nonnull final String name,
                  @Nonnull final List<Parameter> parameters,
                  @Nonnull final List<Statement> statements,
                  @Nonnull final List<Modifier> modifiers,
                  @Nonnull final  List<Annotation> annotations) {
        this.name = name;
        this.parameters = parameters;
        this.statements = statements;
        this.modifiers = modifiers;
        this.annotations = annotations;
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

    @Nonnull
    public List<Annotation> getAnnotations() {
        return annotations;
    }

    @Override
    public String toString() {
        return "Method{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                ", statements=" + statements +
                ", modifiers=" + modifiers +
                ", annotations=" + annotations +
                '}';
    }
}
