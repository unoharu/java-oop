package oop.step04;

public class Cat extends Animal {
    private String type;

    Cat(String name, String type) {
        super(name);
        this.type = type;
    }

    void meow() {
        System.out.println(name + " が鳴く: ニャー！");
    }

    @Override
    String describe() {
        return super.describe() + " / 種類: " + type;
    }
}
