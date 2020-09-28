package fr.umlv.fight;

public class Arena {
    public static Robot fight(Robot oppo1, Robot oppo2) {
        Robot murderer = oppo1;
        Robot victim = oppo2;

        while (!oppo1.isDead() && !oppo2.isDead()) {
            // Mettre le swap à la fin inverserais le vainqueur et le perdant
            Robot tmpRobot = murderer;
            murderer = victim;
            victim = tmpRobot;

            murderer.fire(victim);
        }

        System.out.println("Le vainqueur est le " + murderer.toString() + " !");
        return murderer;
    }
}