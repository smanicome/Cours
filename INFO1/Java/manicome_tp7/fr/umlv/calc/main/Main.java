package fr.umlv.calc.main;

import fr.umlv.calc.Expr;
import fr.umlv.calc.OpOrValue;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Expr expr = Expr.parse(scanner);
        scanner.close();

        expr.display();
        System.out.println();
        System.out.println(expr.eval());
    }
}
