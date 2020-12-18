import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
//https://adventofcode.com/2020/day/10
public class part1 {
    
    public static void main(String... args) throws IOException {
        List<Integer >numbers = Files.lines(Path.of("input.txt"))
                                     .map(Integer::valueOf)
                                     .sorted()
                                     .collect(Collectors.toList());
        printOnesTimesThrees(numbers);
    }

    private static void printOnesTimesThrees(List<Integer> numbers) {
        int one  = 1;
        int three = 1;

        for(int i = 1; i < numbers.size(); i++){
            int x = numbers.get(i-1);
            int y = numbers.get(i);

            one += y-x == 1 ? 1 : 0;
            three += y-x == 3 ? 1 : 0;
        }
        
        System.out.println(one*three);
    }  
}