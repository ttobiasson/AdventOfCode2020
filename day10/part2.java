import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class part2 {
    public static void main(String... args) throws IOException {
        List<Integer >numbers = Files.readAllLines(Path.of("input.txt"))
                                     .stream()
                                     .map(Integer::valueOf)
                                     .sorted()
                                     .collect(Collectors.toList());

        int max = numbers.stream()
                         .mapToInt(i -> i)
                         .max()
                         .orElseThrow(NoSuchElementException::new);

        numbers.add(numbers.size(),max+3);

        long ans = permutations(numbers);
        System.out.println(ans);
    }

    static long permutations(List<Integer>list){
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
    static long sequence(int n){
        if(n >= 3)
            return sequence(n-1) + sequence(n-2) + sequence(n-3);
        if(n == 2)
            return 2;
        else
            return 1;
    }
}