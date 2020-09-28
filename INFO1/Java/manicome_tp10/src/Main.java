import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        var list = List.of("hello", "world", "hello", "lambda");
        var list2 =
            new Random(0)
                .ints(1_000_000, 0, 100)
                .mapToObj(Integer::toString)
                .collect(Collectors.toList());

        System.out.println("printAndTime(List<String> list, String value, BiFunction<List<String>, String, Long> count)");
        Lambda.printAndTime(list2, "33", Lambda::count);
        Lambda.printAndTime(list2, "33", Lambda::count2);
        Lambda.printAndTime(list2, "33", Lambda::count3);

        System.out.println();

        System.out.println("printAndTime(FunctionalCountInterface count)");
        Lambda.printAndTime(() -> Lambda.count(list2, "33"));
        Lambda.printAndTime(() -> Lambda.count2(list2, "33"));
        Lambda.printAndTime(() -> Lambda.count3(list2, "33"));

        System.out.println();

        System.out.println(Lambda.count(list, "hello"));
        System.out.println(Lambda.count2(list, "hello"));
        System.out.println(Lambda.count3(list, "hello"));

        System.out.println();

        System.out.println(Lambda.upperCase(list));
        System.out.println(Lambda.upperCase2(list));
        System.out.println(Lambda.upperCase3(list));
        System.out.println(Lambda.upperCase4(list));

    }
}
