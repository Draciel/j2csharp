import data.File;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import pl.jcsharp.grammar.Java9Lexer;
import pl.jcsharp.grammar.Java9Parser;
import visitor.JavaVisitor;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        final Java9Lexer lexer = new Java9Lexer(
                CharStreams.fromPath(Paths.get("src/main/java/samples/Heater.java")));
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final Java9Parser parser = new Java9Parser(tokens);
        final JavaVisitor visitor = new JavaVisitor();
        final File file = visitor.parse(parser);

        System.out.println("Class " + file.toString());
    }
}
