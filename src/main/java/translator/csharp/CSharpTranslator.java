package translator.csharp;

import data.File;
import translator.Translator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class CSharpTranslator implements Translator {

    @Override
    public void translate(final File input, final Path path) {
        final StringBuilder fileBuilder = new StringBuilder();
        final ImportTranslator importTranslator = ImportTranslator.instance();
        final ClassTranslator classTranslator = ClassTranslator.instance();
        final InterfaceTranslator interfaceTranslator = InterfaceTranslator.instance();
        final EnumTranslator enumTranslator = EnumTranslator.instance();

        input.getImports().stream()
                .map(f -> importTranslator.translate(f, 0))
                .forEach(f -> fileBuilder.append(f).append(Codestyle.newLine()));

        final String translationOutput;
        if (input.getClazz() != null) {
            translationOutput = classTranslator.translate(input.getClazz(), 1);
        } else if (input.getInterface() != null) {
            translationOutput = interfaceTranslator.translate(input.getInterface(), 1);
        } else if (input.getEnum() != null) {
            translationOutput = enumTranslator.translate(input.getEnum(), 1);
        } else {
            throw new IllegalArgumentException("Unsupported object type!");
        }

        fileBuilder.append(Codestyle.newLine())
                .append("namespace")
                .append(Codestyle.space())
                .append(input.getPackageName())
                .append(Codestyle.space())
                .append("{")
                .append(Codestyle.newLine())
                .append(Codestyle.newLine())
                .append(translationOutput)
                .append(Codestyle.newLine())
                .append("}");

        final String fileName = path.getFileName().toString();

        final Path outputPath = path.getParent().resolve(fileName.replace(".java", ".cs"));
        try {
            Files.write(outputPath, fileBuilder.toString().getBytes(), StandardOpenOption.CREATE);
            System.out.println(fileName + " have been successfully translated!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Translation finished with exception");
        }
    }
}
