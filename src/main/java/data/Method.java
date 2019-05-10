package data;

import utility.Nonnull;

import java.util.List;

public class Method {

    @Nonnull
    private final String name;

    @Nonnull
    private final List<Parameter> parameters;

    @Nonnull
    private final Statement block;

    // mainly used in interface methods
    @Nonnull
    private final boolean isDeclaration;

    @Nonnull
    private final List<Modifier> modifiers;

    @Nonnull
    private final List<Annotation> annotations;

    @Nonnull
    private final String result;

    public Method(@Nonnull final String name,
                  @Nonnull final List<Parameter> parameters,
                  @Nonnull final Statement block,
                  @Nonnull final List<Modifier> modifiers,
                  @Nonnull final List<Annotation> annotations,
                  @Nonnull final String result,
                  final boolean isDeclaration) {
        this.name = name;
        this.parameters = parameters;
        this.block = block;
        this.modifiers = modifiers;
        this.annotations = annotations;
        this.result = result;
        this.isDeclaration = isDeclaration;
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
    public Statement getBlock() {
        return block;
    }

    @Nonnull
    public List<Modifier> getModifiers() {
        return modifiers;
    }

    @Nonnull
    public List<Annotation> getAnnotations() {
        return annotations;
    }

    @Nonnull
    public String getResult() {
        return result;
    }

    public boolean isDeclaration() {
        return isDeclaration;
    }

    @Override
    public String toString() {
        return "Method{" +
                "name='" + name + '\'' +
                ", parameters=" + parameters +
                ", block=" + block +
                ", isDeclaration=" + isDeclaration +
                ", modifiers=" + modifiers +
                ", annotations=" + annotations +
                ", result='" + result + '\'' +
                '}';
    }
}
