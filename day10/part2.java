import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
//https://adventofcode.com/2020/day/10
public class part2 {
    public static void main(String... args) throws IOException {
        List<Integer >numbers = Files.lines(Path.of("input.txt"))
                                     .map(Integer::valueOf)
                                     .sorted()
                                     .collect(Collectors.toList());

        printNumberOfWaysToConnectAdapters(numbers);
    }

    private static void printNumberOfWaysToConnectAdapters(List<Integer> numbers) {
        int max = numbers.stream()
                         .mapToInt(i -> i)
                         .max()
                         .orElseThrow(NoSuchElementException::new);

        numbers.add(numbers.size(),max+3);

        long ans = permutations(numbers);
        System.out.println(ans);
    }

    private static long permutations(List<Integer>list){
        long ans = 1;
        int ones = 1;

        for(int i = 1; i < list.size(); i++){
            int diff = list.get(i) - list.get(i-1);

            ones += diff == 1 ? 1 : 0;
            ans *= diff >= 3 ? sequence(ones) : 1;
            ones = diff >= 3 ? 0 : ones;
        }    
        return ans;
    }
    private static long sequence(int n){
        if(n >= 3)
            return sequence(n-1) + sequence(n-2) + sequence(n-3);
        if(n == 2)
            return 2;
        else
            return 1;
    }
}