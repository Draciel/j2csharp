package pl.draciel.j2csharp.data;

import pl.draciel.j2csharp.utility.Nonnull;

import java.util.Arrays;

public enum Modifier {
    //java modifiers
    PRIVATE("private", true), PACKAGE("", true), PROTECTED("protected", true), PUBLIC("public", true),
    STATIC("static", false), FINAL("final", false), TRANSIENT("transient", false), VOLATILE("volatile", false),
    SYNCHRONIZED("synchronized", false), /*not used in c#*/ STRICTFP("strictfp", false), ABSTRACT("abstract", false),

    //c# modifiers
    /*final*/ READONLY("readonly", false), /*static final*/ CONST("const", false), INTERNAL("internal", true),
    OVERRIDE("override", false);

    private final String codeRepresentation;

    private final boolean isAccessModifier;

    Modifier(String codeRepresentation, boolean isAccessModifier) {
        this.codeRepresentation = codeRepresentation;
        this.isAccessModifier = isAccessModifier;
    }

    public static Modifier of(@Nonnull final String codeRepresentation) {
        return Arrays.stream(values()).filter(m -> m.codeRepresentation.equals(codeRepresentation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid code representation" + codeRepresentation));
    }

    public boolean isAccessModifier() {
        return isAccessModifier;
    }

    public String getCodeRepresentation() {
        return codeRepresentation;
    }
}
