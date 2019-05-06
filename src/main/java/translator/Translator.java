package translator;

import data.File;

import java.nio.file.Path;

public interface Translator {
    void translate(final File input, final Path path);
}
