import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
//https://adventofcode.com/2020/day/15
public class part2 {
    public static void main(String... args) throws IOException {
        Iterator<String>iter = Files.lines(Path.of("input.txt")).iterator();
        Map<Integer, Integer>map = new HashMap<Integer, Integer>();
        calculateNthNumber(iter, map);
    }
    private static void calculateNthNumber(Iterator<String>iter, Map<Integer, Integer> map){
        int turns = 1;
        String[] numbers = iter.hasNext() ? iter.next().split(",") : null;
        int lastStartingNumber = 0;

        if(numbers == null){
            System.out.println("number is null");
            System.exit(0);
        }

        for(int i = 0; i < numbers.length-1; i++){
            int n = Integer.valueOf(numbers[i]);
            map.put(n, turns);
            if(i == numbers.length-2)
                lastStartingNumber = Integer.valueOf(numbers[i+1]);
            turns++;
        }
        
        while(true){
            
            Integer checkNumber = map.put(lastStartingNumber, turns);
            int zeroOrTurns = checkNumber != null ? turns - checkNumber : 0;
            
            if(turns+1 == 30000000){
                System.out.println(zeroOrTurns);
                break;
            }

            lastStartingNumber = zeroOrTurns;
            turns++;
        }
    }
}
