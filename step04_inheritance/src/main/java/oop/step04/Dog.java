package oop.step04;

public class Dog extends Animal {
    private String breed;

    Dog(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    void bark() {
        System.out.println(name + " が吠える: ワン！");
    }

    @Override
    String describe() {
        return super.describe() + " / 犬種: " + breed;
    }
}
