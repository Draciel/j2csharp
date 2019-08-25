package pl.draciel.j2csharp.parser;

import pl.draciel.j2csharp.data.File;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Parses file to the user-friendly format.
 */
public interface SimpleParser {
    File parse(final ParseTree parseTree);
}
