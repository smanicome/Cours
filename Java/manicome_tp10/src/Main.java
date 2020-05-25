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

        printAndTime(list2, "33");

        System.out.println(count(list, "hello"));
        System.out.println(count2(list, "hello"));
        System.out.println(count3(list, "hello"));

        System.out.println(upperCase(list));
        System.out.println(upperCase2(list));
        System.out.println(upperCase3(list));
        System.out.println(upperCase4(list));

    }

    public static long count(List<String> list, String value) {
        if (list == null || value == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }

        int i = 0;

        for (String elt : list)
            if (elt.equals(value))
                i++;

        return i;
    }

    public static long count2(List<String> list, String value) {
        return list.stream()
                .filter(w -> w.equals(value))
                .count();
    }

    public static long count3(List<String> list, String value) {
        return list.stream()
                .filter(w -> w.equals(value))
                .mapToInt(w -> 1)
                .reduce(0, Integer::sum);
    }

    public static List<String> upperCase(List<String> list) {
        List<String> l = new ArrayList<>();
        list.forEach(w -> l.add(w.toUpperCase()));
        return l;
    }

    public static List<String> upperCase2(List<String> list) {
        List<String> l = new ArrayList<String>();
        list.stream().map(e -> e.toUpperCase()).forEach(e->l.add(e));
        return l;
    }

    public static List<String> upperCase3(List<String> list) {
        List<String> l = new ArrayList<String>();
        list.stream().map(String::toUpperCase).forEach(l::add);
        return l;
    }

    public static List<String> upperCase4(List<String> list) {
        return list.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    public static void printAndTime(List<String> list, String value, BiFunction<List<String>, String, Integer> count) {
        var start = System.nanoTime();

        long result = count3(list, value);

        var end = System.nanoTime();
        System.out.println("result " + result);
        System.out.println(" elapsed time " + (end - start));
    }
}
