import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
//https://adventofcode.com/2020/day/1
public class part1 {
    public static void main(String... args) throws IOException {
        List<String>numbers = Files.readAllLines(Path.of("input.txt"));
        
        int product = numbers.stream()
                             .map(Integer::parseInt)
                             .filter(x -> numbers.contains(String.valueOf((2020 - x))) )
                             .reduce(1, (x, y) -> x * y);

        System.out.println(product);
    }
}