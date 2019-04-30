package parser;

import data.File;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Parses file to the user-friendly format.
 */
public interface SimpleParser {
    File parse(final ParseTree parseTree);
}
