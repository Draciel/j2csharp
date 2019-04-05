import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        final Java9Lexer lexer = new Java9Lexer(CharStreams.fromPath(Paths.get("src/Heater.java")));
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final Java9Parser parser = new Java9Parser(tokens);
        final Java9Parser.CompilationUnitContext compilationUnitContext = parser.compilationUnit();
        final ParseTreeWalker walker = new ParseTreeWalker();
        final CSharpListener listener = new CSharpListener();

        walker.walk(listener, compilationUnitContext);
    }
}
