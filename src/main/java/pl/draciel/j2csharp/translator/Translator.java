package pl.draciel.j2csharp.translator;

import pl.draciel.j2csharp.data.File;

import java.nio.file.Path;

public interface Translator {
    void translate(final File input, final Path path);

    boolean supports(final Path path);
}
