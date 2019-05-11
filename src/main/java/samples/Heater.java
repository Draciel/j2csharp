package samples;

import samples.atomic.FastBoolean;
import samples.comparable.Comparable;
import samples.exception.NotImplementedException;
import samples.exception.NotImplementedExceptionBetter;

@Deprecated
class Heater<T extends Function<String, Object>, T2 extends JustEmpty> extends Object implements Consumer<T>,
        Comparable<T2> {

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

    private void switchTest() {
        final char i = '0';
        switch (i) {
            case '1':
                System.out.println("Its 1");
                break;

            case '2':
            case '3':
                System.out.println("Its 2 or 3");
                break;

            default:
                System.out.println("Different");
                break;
        }
    }

    public void whileTest() {
        int i = 0;
        while (i < 5) {
            System.out.println("Looping via while " + i);
            i++;
        }
    }

    public void ifThenElseTest() {
        final int i = 5;
        if (i > 5) {
            System.out.println("I is greater than 5");
        } else if (i > 3) {
            System.out.println("I is greater than 3 and lower than 5");
        } else {
            System.out.println("I is lower than 3");
            System.out.println("Test!");
        }
    }

    public void loopTest() {
        final int[] integers = new int[]{1, 2, 3, 4, 5};

        for (int i = 0; i < 10; i++) {
            System.out.println("Basic Counting " + i);
        }

        for (int i : integers) {
            System.out.println("Enhanced counting " + i);
        }
    }

    public void heat(final int offset) {
        if ((maxTemp - currentTemp - offset) > 0) {
            currentTemp += offset;
        }
        try {
            System.out.println(format(HEATING_MESSAGE, currentTemp));
        } catch (NotImplementedException ex) {
            System.out.println("Not implemented!");
        }
    }

    public synchronized void cool(final int offset) {
        if (currentTemp - offset > 0) {
            currentTemp -= offset;
        }
        try {
            System.out.println(format(COOLING_MESSAGE, currentTemp));
        } catch (NotImplementedException | NotImplementedExceptionBetter x) {
            System.out.println("Not implemented!");
        } finally {
            System.out.println("Finally block!");
        }
    }

    public synchronized int getCurrentTemp() {
        return currentTemp;
    }

    public synchronized void keepTemperature(final long timeInMs, final boolean keepStable) {
        keepTemperatureTimeInMs = timeInMs;
        this.keepStable = keepStable;
        try {
            System.out.println(KEEP_TEMPERATURE_MESSAGE);
        } finally {
            System.out.println("Finally!");
        }
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

    private static String format(String message, Object... strings) {
        throw new NotImplementedException();
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
        public void start() {
            super.start();
        }

        @Override
        public int stop() {
            return 0;
        }
    }
}
