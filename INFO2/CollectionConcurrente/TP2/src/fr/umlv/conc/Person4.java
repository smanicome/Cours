package fr.umlv.conc;

public class Person4 {
    private final String name;
    private final int age;

    public Person4(String name, int age) {
        this.name = name;
        this.age = age;

        // name et age utilisés dans le thread sont des paramètres capturés du constructeur
        new Thread(() -> {
            System.out.println(name + " " + age);
        }).start();
    }
}
    