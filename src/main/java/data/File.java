package data;

import java.util.List;

public class File {

    private final String packageName;

    private final List<Import> imports;

    private final Class clazz;

    public File(final String packageName, final List<Import> imports, final Class clazz) {
        this.packageName = packageName;
        this.imports = imports;
        this.clazz = clazz;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<Import> getImports() {
        return imports;
    }

    public Class getClazz() {
        return clazz;
    }

    @Override
    public String toString() {
        return "File{" +
                "packageName='" + packageName + '\'' +
                ", imports=" + imports +
                ", clazz=" + clazz +
                '}';
    }
}
