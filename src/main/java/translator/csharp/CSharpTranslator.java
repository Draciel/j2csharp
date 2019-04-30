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
        final String namespace = "namespace " + input.getPackageName().toUpperCase();

        //fixme investigate how imports work in c# and implement
        final String imports = "using TestImport;";

        final String translatedClass = ClassTranslator.instance().translate(input.getClazz(), 0);

        final Path path = Paths.get("src/main/java/samples/Heater.cs");
        try {
            Files.write(path, translatedClass.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished translation!");
    }
}
