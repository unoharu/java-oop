package oop.step01;

public class Car {
    String make;
    String model;
    int speed;

    void init(String make, String model) {
        this.make = make;
        this.model = model;
        this.speed = 0;
    }

    void accelerate(int amount) {
        speed += amount;
    }

    void brake(int amount) {
        // speed が 0 未満にならないよう制限する
        speed = Math.max(0, speed - amount);
    }

    String describe() {
        return make + " " + model + " (speed: " + speed + " km/h)";
    }
}
