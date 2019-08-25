package pl.draciel.j2csharp.data;

import pl.draciel.j2csharp.utility.Nullable;

import java.util.List;

public class File {

    private final String packageName;

    private final List<Import> imports;

    @Nullable
    private final Class clazz;

    @Nullable
    private final Enum enumm;

    @Nullable
    private final Interface interfacee;

    public File(final String packageName, final List<Import> imports, final Class clazz,
                final Enum enumm, final Interface interfacee) {
        this.packageName = packageName;
        this.imports = imports;
        this.clazz = clazz;
        this.enumm = enumm;
        this.interfacee = interfacee;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<Import> getImports() {
        return imports;
    }

    @Nullable
    public Class getClazz() {
        return clazz;
    }

    @Nullable
    public Enum getEnum() {
        return enumm;
    }

    @Nullable
    public Interface getInterface() {
        return interfacee;
    }

    @Override
    public String toString() {
        return "File{" +
                "packageName='" + packageName + '\'' +
                ", imports=" + imports +
                ", clazz=" + clazz +
                ", enumm=" + enumm +
                ", interfacee=" + interfacee +
                '}';
    }
}
