package functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Concordance {
    private static final Pattern WORD_BREAK = Pattern.compile("\\W+");
    private static final Comparator<Map.Entry<String, Long>> valueOrder =
            Map.Entry.comparingByValue();
    private static final Comparator<Map.Entry<String, Long>> reversedValue =
            valueOrder.reversed();
    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get("PrideAndPrejudice.txt"))
                //.flatMap(l -> WORD_BREAK.splitAsStream(l))
                .flatMap(WORD_BREAK::splitAsStream)
                .filter(w -> w.length() > 0)
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(reversedValue)
                .limit(200)
                .map(l -> String.format("%20s : %3d\n", l.getKey(), l.getValue()))
                .forEach(System.out::println);
    }
}
