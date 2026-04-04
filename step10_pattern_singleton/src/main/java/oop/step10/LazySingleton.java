package oop.step10;

// Lazy initialization with double-checked locking
// Thread-safe and avoids synchronization overhead after first creation
public class LazySingleton {
    // volatile ensures visibility across threads
    private static volatile LazySingleton instance;

    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null) {
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }
}
