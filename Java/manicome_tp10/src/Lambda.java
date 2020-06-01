import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Lambda {
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

    public static void printAndTime(List<String> list, String value, BiFunction<List<String>, String, Long> count) {
        var start = System.nanoTime();

        long result = count(list, value);

        var end = System.nanoTime();
        System.out.println("result " + result);
        System.out.println(" elapsed time " + (end - start));
    }

    public static void printAndTime(FunctionalCountInterface count) {
        var start = System.nanoTime();

        long result = count.compute();

        var end = System.nanoTime();
        System.out.println("result " + result);
        System.out.println(" elapsed time " + (end - start));
    }
}
