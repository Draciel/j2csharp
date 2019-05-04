package data;

public class Import {

    private final boolean isStatic;
    private final String packageName;
    private final String className;

    public Import(final boolean isStatic, final String packageName, final String className) {
        this.isStatic = isStatic;
        this.packageName = packageName;
        this.className = className;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "Import{" +
                "isStatic=" + isStatic +
                ", packageName='" + packageName + '\'' +
                '}';
    }
}
