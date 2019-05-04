package translator.csharp;

import data.File;
import translator.Translator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CSharpTranslator implements Translator {

    @Override
    public void translate(final File input) {
        final ImportTranslator importTranslator = ImportTranslator.instance();
        final StringBuilder fileBuilder = new StringBuilder();

        input.getImports().stream()
                .map(f -> importTranslator.translate(f, 0))
                .forEach(f -> fileBuilder.append(f).append(Codestyle.newLine()));

        fileBuilder
                .append(Codestyle.newLine())
                .append("namespace")
                .append(Codestyle.space())
                .append(input.getPackageName())
                .append(Codestyle.space())
                .append("{")
                .append(Codestyle.newLine());

        fileBuilder.append(Codestyle.newLine())
                .append(ClassTranslator.instance().translate(input.getClazz(), 1))
                .append(Codestyle.newLine())
                .append("}");

        final Path path = Paths.get("src/main/java/samples/Heater.cs");
        try {
            Files.write(path, fileBuilder.toString().getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished translation!");
    }
}
