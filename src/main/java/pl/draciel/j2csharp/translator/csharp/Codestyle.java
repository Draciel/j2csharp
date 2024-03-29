package pl.draciel.j2csharp.translator.csharp;

final class Codestyle {

    private Codestyle() {
        //no instance
    }

    private static final String SPACE = " ";
    private static final String INDENTATION = "  ";
    private static final String NEW_LINE = "\n";

    public static String space() {
        return SPACE;
    }

    public static String indentation() {
        return INDENTATION;
    }

    public static String newLine() {
        return NEW_LINE;
    }
}
