package pl.draciel.j2csharp.visitor;

import pl.draciel.j2csharp.data.File;
import org.antlr.v4.runtime.tree.ParseTree;
import pl.draciel.j2csharp.parser.SimpleParser;

public class SimpleJava9Parser implements SimpleParser {

    @Override
    public File parse(final ParseTree parseTree) {
        final FileVisitor fileVisitor = FileVisitor.instance();
        return fileVisitor.visit(parseTree);
    }

}
