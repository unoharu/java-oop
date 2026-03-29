package oop.step10;

public class Box<T> {
    private T value;

    Box(T value) {
        this.value = value;
    }

    T get() {
        return value;
    }

    void set(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Box<" + value.getClass().getSimpleName() + ">: " + value;
    }
}
