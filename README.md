# Project for course "Języki formalne i kompilatory"

## Configuration without Gradle:

1. Download latest ANTLR version from (in this quickstart guide I will use 4.7.2)

        http://www.antlr.org/download/

2. Move ANTLR to /usr/local/lib 

        sudo cp antlr-4.7.2-complete.jar /usr/local/lib/

3. Export classpath

        export CLASSPATH = ".:/usr/local/lib/antlr-4.7.2-complete.jar:$CLASSPATH"

4. Add some handy aliases:

        alias antlr4='java -Xmx500M -cp "/usr/local/lib/antlr-4.7.2-complete.jar:$CLASSPATH" org.antlr.v4.Tool'

        alias grun='java org.antlr.v4.gui.TestRig'

5. Install ANTLR4 plugin for Intellij

6. Once you install all necessary things, right click on grammar file, choose **configure ANTLR** option and clean output directory if necessary

7. Open grammar file and generate lexer, parser and other things (Ctrl + Shift + G or right click on file and choose **Generate ANTLR Recognizer** 
         
8. Open File -> Project Structure and mark **gen** directory as source

9. Build project and run Main.main(args) 

10. Have fun!

## Configuration with Gradle:

1. Build project from terminal via command:
    
        ./gradlew build

2. You can run project via command: 
        
        ./gradlew run --args='path'

Path can be directory or direct path to Java class

Translation output will be in dir `translated` 

## Samples

Sample files are located in `samples` directory

## Translation example

##### Heater.java

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
        
                @Override
                public int stop() {
                    System.out.println("Stoping!");
                    return 0;
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
        
##### Heater.cs
        
        using System;
        using System.Collections.Generic;
        using samples.atomic;
        using samples.comparable;
        using NotImplementedException = samples.exception.NotImplementedException;
        using NotImplementedExceptionBetter = samples.exception.NotImplementedExceptionBetter;
        
        namespace samples {
        
          internal class Heater<T, T2> : Object, Consumer<T>, Comparable<T2>
            where T : Function<string, Object>
            where T2 : JustEmpty {
        
            public const int OPTIMAL_TEMP = 10;
        
            private const string HEATING_MESSAGE = "Heating %s";
        
            private const string COOLING_MESSAGE = "Cooling %s";
        
            private const string KEEP_TEMPERATURE_MESSAGE = "Keeping temperature";
        
            private readonly int maxTemp;
        
            private readonly TimeUnit timeUnit = TimeUnit.SECONDS;
        
            private readonly FastBoolean fastBoolean = new FastBoolean();
        
            private int currentTemp = 0;
        
            private long keepTemperatureTimeInMs = 0;
        
            private bool keepStable;
        
            private T adapter;
        
            protected Heater(int maxTemp) {
              this.maxTemp = maxTemp;
            }
        
            private void switchTest() {
              char i = '0';
              switch(i) {
                case '1':
                  Console.WriteLine("Its 1");
                  break;
                case '2':
                case '3':
                  Console.WriteLine("Its 2 or 3");
                  break;
                default:
                  Console.WriteLine("Different");
                  break;
              }
            }
        
            public void whileTest() {
              int i = 0;
              while(i < 5) {
                Console.WriteLine("Looping via while " + i);
                i++;
              }
            }
        
            public void ifThenElseTest() {
              int i = 5;
              if (i > 5) {
                  Console.WriteLine("I is greater than 5");
              } else       if (i > 3) {
                  Console.WriteLine("I is greater than 3 and lower than 5");
              } else {
                  Console.WriteLine("I is lower than 3");
                  Console.WriteLine("Test!");
              }
            }
        
            public void loopTest() {
              int[] integers = new int[]{1, 2, 3, 4, 5};
              for (int i = 0; i < 10; i++) {
                  Console.WriteLine("Basic Counting " + i);
              }
              foreach (int i in integers) {
                  Console.WriteLine("Enhanced counting " + i);
              }
            }
        
            public void heat(int offset) {
              if ((maxTemp - currentTemp - offset) > 0) {
                  currentTemp += offset;
              }
              try {
                Console.WriteLine(format(HEATING_MESSAGE, currentTemp));
              } catch (NotImplementedException ex) {
                Console.WriteLine("Not implemented!");
              }
            }
        
            public void cool(int offset) {
              if (currentTemp - offset > 0) {
                  currentTemp -= offset;
              }
              try {
                Console.WriteLine(format(COOLING_MESSAGE, currentTemp));
              } catch (NotImplementedException x) {
                Console.WriteLine("Not implemented!");
              } catch (NotImplementedExceptionBetter x) {
                Console.WriteLine("Not implemented!");
              } finally {
                Console.WriteLine("Finally block!");
              }
            }
        
            public int getCurrentTemp() {
              return currentTemp;
            }
        
            public void keepTemperature(long timeInMs, bool keepStable) {
              keepTemperatureTimeInMs = timeInMs;
              this.keepStable = keepStable;
              try {
                Console.WriteLine(KEEP_TEMPERATURE_MESSAGE);
              } finally {
                Console.WriteLine("Finally!");
              }
            }
        
            public void keepTemperature(long timeInsMs, bool keepStable, int repeat) {
              keepTemperature(timeInsMs, keepStable);
              for (int i = 1; i < repeat; i++) {
                  keepTemperature(timeInsMs, keepStable);
              }
            }
        
            public void accept(T t) {
        
        
            }
        
            public int compareTo(T2 t2) {
              return 0;
            }
        
            private static string format(string message, params Object[] strings) {
              throw new NotImplementedException();
            }
        
            public abstract class BaseEngine : Engine {
        
              protected readonly int capacity;
        
              public BaseEngine(int capacity) {
                this.capacity = capacity;
              }
        
              public void start() {
                Console.WriteLine("Starting ...!");
              }
        
              public int stop() {
                Console.WriteLine("Stoping!");
                return 0;
              }
        
            }
        
            public class EngineImpl : BaseEngine {
        
              public EngineImpl() : base(16) {
        
              }
        
              public void start() {
                base.start();
              }
        
              public int stop() {
                return 0;
              }
        
            }
        
            internal interface Engine : JustEmpty {
        
               void start();
        
               int stop();
        
            }
        
            internal class EngineType {
        
              internal static readonly EngineType ELECTRIC = new EngineType("ELC");
              internal static readonly EngineType STEAM = new EngineType("STM");
        
              private readonly string digest;
        
              private EngineType(string digest) {
                this.digest = digest;
              }
        
              internal static IEnumerable<EngineType> values {
                get {
                  yield return ELECTRIC;
                  yield return STEAM;
                }
              }
            }
        
          }
        }