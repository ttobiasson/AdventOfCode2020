import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class part1 {
    public static void main(String... args) throws IOException {
        Scanner sc = new Scanner(new File("input.txt"));
        Map<Integer, Integer>map = new HashMap<Integer, Integer>();
        calculateNthNumber(sc, map);
    }
    static void calculateNthNumber(Scanner sc, Map<Integer, Integer> map){
        int turns = 1;
        String[] numbers = sc.nextLine().split(",");
        int lastStartingNumber = 0;

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
            
            if(turns+1 == 2020){
                System.out.println(zeroOrTurns);
                break;
            }

            lastStartingNumber = zeroOrTurns;
            turns++;
        }
    }
}
