//package samples; //fixme package cause some problems for parser

@Deprecated
final class Heater {

    public static final int OPTIMAL_TEMP = 10;
    private static final String HEATING_MESSAGE = "Heating ";
    private static final String COOLING_MESSAGE = "Cooling ";
    private static final String KEEP_TEMPERATURE_MESSAGE = "Keeping temperature ";
    private final int maxTemp;
    private int currentTemp = 0;
    private long keepTemperatureTimeInMs = 0;
    private boolean keepStable;

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
        keepTemperatureTimeInMs = timeInMs;
        this.keepStable = keepStable;
        System.out.println(KEEP_TEMPERATURE_MESSAGE);
    }

    public synchronized void keepTemperature(final long timeInsMs, final boolean keepStable, final int repeat) {
        keepTemperature(timeInsMs, keepStable);
        for (int i = 1; i < repeat; i++) {
            keepTemperature(timeInsMs, keepStable);
        }
    }
}
