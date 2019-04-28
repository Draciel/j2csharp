package data;

import utility.Nonnull;

import java.util.Arrays;

public enum Modifier {
    PRIVATE("private", true), PACKAGE("", true), PROTECTED("protected", true), PUBLIC("public", true), STATIC("static", false), FINAL("final", false), TRANSIENT("transient", false), VOLATILE("volatile", false),
    SYNCHRONIZED("synchronized", false), /*not used in c#*/ STRICTFP("strictfp", false), ABSTRACT("abstract", false);

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

}
