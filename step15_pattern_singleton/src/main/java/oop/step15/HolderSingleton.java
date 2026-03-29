package oop.step15;

// Initialization-on-demand holder idiom
// Lazy + thread-safe without synchronized overhead
public class HolderSingleton {
    private HolderSingleton() {}

    // Inner class is not loaded until getInstance() is called
    private static final class Holder {
        static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    public static HolderSingleton getInstance() {
        return Holder.INSTANCE;
    }
}
