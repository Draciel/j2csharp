import data.File;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.SimpleParser;
import pl.jcsharp.grammar.Java9Lexer;
import pl.jcsharp.grammar.Java9Parser;
import translator.Translator;
import translator.csharp.CSharpTranslator;
import visitor.SimpleJava9Parser;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        final SimpleParser visitor = new SimpleJava9Parser();
        final Translator translator = new CSharpTranslator();

        final List<Path> files = new ArrayList<>();
        files.add(Paths.get("src/main/java/samples/Heater.java"));
        files.add(Paths.get("src/main/java/samples/Function.java"));
        files.add(Paths.get("src/main/java/samples/Consumer.java"));
        files.add(Paths.get("src/main/java/samples/JustEmpty.java"));
        files.add(Paths.get("src/main/java/samples/TimeUnit.java"));
        files.add(Paths.get("src/main/java/samples/atomic/FastBoolean.java"));
        files.add(Paths.get("src/main/java/samples/comparable/Comparable.java"));
        files.add(Paths.get("src/main/java/samples/exception/NotImplementedException.java"));
        files.add(Paths.get("src/main/java/samples/exception/NotImplementedExceptionBetter.java"));

        for (int i = 0; i < files.size(); i++) {
            final Java9Lexer lexer = new Java9Lexer(CharStreams.fromPath(files.get(i)));
            final CommonTokenStream tokens = new CommonTokenStream(lexer);
            final Java9Parser parser = new Java9Parser(tokens);

            final File file = visitor.parse(parser.compilationUnit());
            translator.translate(file, files.get(i));
        }
    }
}
