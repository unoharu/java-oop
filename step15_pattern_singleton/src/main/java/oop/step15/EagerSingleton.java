package oop.step15;

// Eager initialization: instance created at class loading time
// Thread-safe by JVM class loading guarantee
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();

    private EagerSingleton() {}

    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
