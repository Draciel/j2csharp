package data;

import utility.Nonnull;

import java.util.Arrays;

public enum Modifier {
    PRIVATE("private"), PACKAGE(""), PROTECTED("protected"), PUBLIC("public"), STATIC("static"), FINAL("final"),
    TRANSIENT("transient"), VOLATILE("volatile");

    private final String codeRepresentation;

    Modifier(String codeRepresentation) {
        this.codeRepresentation = codeRepresentation;
    }

    public static Modifier of(@Nonnull final String codeRepresentation) {
        return Arrays.stream(values()).filter(m -> m.codeRepresentation.equals(codeRepresentation))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid code representation"));
    }

}
