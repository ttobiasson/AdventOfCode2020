import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
//https://adventofcode.com/2020/day/13
public class part1 {
    public static void main(String... args) throws IOException {
        List<String>lines = Files.readAllLines(Path.of("input.txt"));

        printEarliestTimestamp(lines);
    }

    private static void printEarliestTimestamp(List<String> lines) {
        String a = lines.get(0);
        String b = lines.get(1);
        
        int timestamp = Integer.valueOf(a);
        int minutes = timestamp;
        String[] strs = b.replaceAll(",x", "").split(",");


        int remainder = 1;
        while(remainder != 0){
            for(String str : strs){
                int value = Integer.valueOf(str);
                if(minutes%value == 0){
                    System.out.println(((minutes - timestamp) * value));
                    System.exit(0);
                }
            }
            minutes++;
        }
    }
}