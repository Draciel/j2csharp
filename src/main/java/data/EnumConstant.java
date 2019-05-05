package data;

import java.util.List;

public class EnumConstant {

    private final String contantName;

    private final List<String> passedParameters;

    public EnumConstant(final String contantName, final List<String> passedParameters) {
        this.contantName = contantName;
        this.passedParameters = passedParameters;
    }

    public String getContantName() {
        return contantName;
    }

    public List<String> getPassedParameters() {
        return passedParameters;
    }

    @Override
    public String toString() {
        return "EnumConstant{" +
                "contantName='" + contantName + '\'' +
                ", passedParameters=" + passedParameters +
                '}';
    }
}

