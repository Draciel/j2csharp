import data.File;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import pl.jcsharp.grammar.Java9Lexer;
import pl.jcsharp.grammar.Java9Parser;
import parser.SimpleParser;
import visitor.SimpleJava9Parser;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        final Java9Lexer lexer = new Java9Lexer(
                CharStreams.fromPath(Paths.get("src/main/java/samples/Heater.java")));
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final Java9Parser parser = new Java9Parser(tokens);

        // todo refactor at some point to handle multiple files
        final SimpleParser visitor = new SimpleJava9Parser();

        final File file = visitor.parse(parser.compilationUnit());

        System.out.println("Class " + file.toString());
    }
}
