package pl.draciel.j2csharp.data;

import pl.draciel.j2csharp.utility.Nonnull;
import pl.draciel.j2csharp.utility.Nullable;

import java.util.List;

public class Constructor {

    @Nonnull
    private final String className;

    @Nonnull
    private final List<Parameter> parameters;

    @Nonnull
    private final List<Statement> statements;

    @Nonnull
    private final List<Modifier> modifiers;

    @Nonnull
    private final List<Annotation> annotations;

    @Nonnull
    private final Statement.StatementExpression explicitConstructorInvocation;

    public Constructor(@Nonnull final String className,
                       @Nonnull final List<Parameter> parameters,
                       @Nonnull final List<Statement> statements,
                       @Nonnull final List<Modifier> modifiers,
                       @Nonnull final List<Annotation> annotations,
                       @Nullable Statement.StatementExpression explicitConstructorInvocation) {
        this.className = className;
        this.parameters = parameters;
        this.statements = statements;
        this.modifiers = modifiers;
        this.annotations = annotations;
        this.explicitConstructorInvocation = explicitConstructorInvocation;
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

    @Nonnull
    public Statement.StatementExpression getExplicitConstructorInvocation() {
        return explicitConstructorInvocation;
    }

    @Nonnull
    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "Constructor{" +
                "className='" + className + '\'' +
                ", parameters=" + parameters +
                ", statements=" + statements +
                ", modifiers=" + modifiers +
                ", annotations=" + annotations +
                ", explicitConstructorInvocation=" + explicitConstructorInvocation +
                '}';
    }
}
