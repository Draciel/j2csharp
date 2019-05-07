package samples;

import samples.atomic.FastBoolean;
import samples.comparable.Comparable;

import static java.lang.String.format;

@Deprecated
class Heater<T extends Function<String, Object>, T2 extends Object> extends Object implements Consumer<T>, Comparable<T2> {

    public static final int OPTIMAL_TEMP = 10;
    private static final String HEATING_MESSAGE = "Heating %s";
    private static final String COOLING_MESSAGE = "Cooling %s";
    private static final String KEEP_TEMPERATURE_MESSAGE = "Keeping temperature";
    private final int maxTemp;
    private final TimeUnit timeUnit = TimeUnit.SECONDS;
    private final FastBoolean fastBoolean = new FastBoolean();
    private int currentTemp = 0;
    private long keepTemperatureTimeInMs = 0;
    private boolean keepStable;
    private T adapter;

    protected Heater(final int maxTemp) {
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
            System.out.println(format(HEATING_MESSAGE, currentTemp));
        }
    }

    public synchronized void cool(final int offset) {
        if (currentTemp - offset > 0) {
            currentTemp -= offset;
        }
        System.out.println(format(COOLING_MESSAGE, currentTemp));
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

    @Override
    public void accept(final T t) {

    }

    @Override
    public int compareTo(final T2 t2) {
        return 0;
    }

    enum EngineType {
        ELECTRIC("ELC"), STEAM("STM");

        private final String digest;

        EngineType(final String digest) {
            this.digest = digest;
        }

    }

    interface Engine extends JustEmpty {

        void start();

        int stop();

    }

    public static abstract class BaseEngine implements Engine {

        protected final int capacity;

        public BaseEngine(final int capacity) {
            this.capacity = capacity;
        }

        @Override
        public void start() {
            System.out.println("Starting ...!");
        }
    }

    public static class EngineImpl extends BaseEngine {

        public EngineImpl() {
            super(16);
        }

        @Override
        public int stop() {
            return 0;
        }
    }
}
