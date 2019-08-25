package pl.draciel.j2csharp;

import generated.Java9Lexer;
import generated.Java9Parser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import pl.draciel.j2csharp.parser.SimpleParser;
import pl.draciel.j2csharp.translator.Translator;
import pl.draciel.j2csharp.translator.csharp.CSharpTranslator;
import pl.draciel.j2csharp.utility.FileUtil;
import pl.draciel.j2csharp.visitor.SimpleJava9Parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Please specify input file path");
            return;
        }

        final SimpleParser visitor = new SimpleJava9Parser();
        final Translator translator = new CSharpTranslator();
        final List<Path> classes = Arrays.stream(args)
                .map(File::new)
                .map(FileUtil::traverseDir)
                .flatMap(Collection::stream)
                .map(File::toPath)
                .filter(translator::supports)
                .collect(Collectors.toList());

        for (final Path p : classes) {
            final Java9Lexer lexer = new Java9Lexer(CharStreams.fromPath(p));
            final CommonTokenStream tokens = new CommonTokenStream(lexer);
            final Java9Parser parser = new Java9Parser(tokens);
            translator.translate(visitor.parse(parser.compilationUnit()), p);
        }
    }
}
