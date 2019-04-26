import data.Class;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import pl.jcsharp.grammar.Java9Lexer;
import pl.jcsharp.grammar.Java9Parser;
import visitor.CSharpVisitor;

import java.io.IOException;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {
        final Java9Lexer lexer = new Java9Lexer(
                CharStreams.fromPath(Paths.get("src/main/java/samples/Heater.java")));
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final Java9Parser parser = new Java9Parser(tokens);
        final CSharpVisitor visitor = new CSharpVisitor();
        final Class clz = visitor.parse(parser.classDeclaration());
        System.out.println("Class " + clz.toString());
    }
}
