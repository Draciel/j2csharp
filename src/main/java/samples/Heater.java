//package samples; //fixme package cause some problems for parser

class Heater {

    public static final int OPTIMAL_TEMP = 10;
    private final int maxTemp;
    private int currentTemp = 0;

    private Heater(final int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public static Heater create(final int maxTemp) {
        return new Heater(maxTemp);
    }

    public void heat(final int offset) {
        if ((maxTemp - currentTemp - offset) > 0) {
            currentTemp += offset;
        }
        System.out.println("Heating " + currentTemp);
    }

    public void cool(final int offset) {
        if (currentTemp - offset > 0) {
            currentTemp -= offset;
        }
        System.out.println("Cool " + currentTemp);
    }
}
