package oop.step13;

public sealed interface Shape permits Circle, Rectangle, Triangle {
    double area();
}
