package pl.draciel.j2csharp.utility;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public final class FileUtil {

    private FileUtil() {
        //no instance
    }

    @Nonnull
    public static List<File> traverseDir(@Nonnull final File dir) {
        final List<File> outputFiles = new LinkedList<>();
        if (dir.isDirectory()) {
            obtainFilesInDir(dir, outputFiles);
        } else {
            outputFiles.add(dir);
        }
        return outputFiles;
    }

    private static void obtainFilesInDir(@Nonnull final File dir, @Nonnull final List<File> outputFiles) {
        final File[] innerFiles = dir.listFiles();
        if (innerFiles != null) {
            Arrays.stream(innerFiles).forEach(f -> {
                if (f.isDirectory()) {
                    obtainFilesInDir(f, outputFiles);
                } else {
                    outputFiles.add(f);
                }
            });
        }
    }
}
