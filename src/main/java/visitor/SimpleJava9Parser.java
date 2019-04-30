package visitor;

import data.File;
import org.antlr.v4.runtime.tree.ParseTree;
import parser.SimpleParser;

public class SimpleJava9Parser implements SimpleParser {

    @Override
    public File parse(final ParseTree parseTree) {
        final FileVisitor fileVisitor = FileVisitor.instance();
        return fileVisitor.visit(parseTree);
    }

}
