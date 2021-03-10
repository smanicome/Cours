package fr.umlv.conc;

public class Person3 {
    private final String name;
    private final int age;

    public Person3(String name, int age) {
        this.name = name;
        this.age = age;
        // Fonctionne car le start assure la visibilité des écritures précédentes
        new Thread(() -> {
            System.out.println(this.name + " " + this.age);
        }).start();
    }
}