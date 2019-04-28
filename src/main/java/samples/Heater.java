//package samples; //fixme package cause some problems for parser

final class Heater {

    public static final int OPTIMAL_TEMP = 10;
    private static final String HEATING_MESSAGE = "Heating ";
    private static final String COOLING_MESSAGE = "Cooling ";
    private final int maxTemp;
    private int currentTemp = 0;
    private long keepTemperatureInMs = 0;

    private Heater(final int maxTemp) {
        this.maxTemp = maxTemp;
    }

    public static Heater create(final int maxTemp) {
        return new Heater(maxTemp);
    }

    public void heat(final int offset) {
        synchronized (this) {
            if ((maxTemp - currentTemp - offset) > 0) {
                currentTemp += offset;
            }
            System.out.println(HEATING_MESSAGE + currentTemp);
        }
    }

    public synchronized void cool(final int offset) {
        if (currentTemp - offset > 0) {
            currentTemp -= offset;
        }
        System.out.println(COOLING_MESSAGE + currentTemp);
    }

    public synchronized int getCurrentTemp() {
        return currentTemp;
    }

    public synchronized void keepTemperature(final long timeInMs, final boolean keepStable) {
        keepTemperatureInMs = timeInMs;
    }
}
