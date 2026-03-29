package oop.step04;

public class Animal {
    protected String name;

    Animal(String name) {
        this.name = name;
    }

    void eat() {
        System.out.println(name + " が食事をする");
    }

    String describe() {
        return "動物: " + name;
    }
}
