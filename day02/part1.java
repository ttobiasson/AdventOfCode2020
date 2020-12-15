import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
//https://adventofcode.com/2020/day/2
public class part1 {
    public static void main(String[] arg) throws IOException {
        Iterator<String>iter = Files.readAllLines(Path.of("input.txt")).iterator();
        getValidPasswords(iter);
    }

    private static void getValidPasswords(Iterator<String> iter) {
        int valid = 0;
        while(iter.hasNext()){
            String nextLine = iter.next();
            String[] parts = nextLine.replace(":", "").split(" ");
            String[] lowAndHigh = parts[0].split("-");
        
            int count = 0;
            int low = Integer.parseInt(lowAndHigh[0]);
            int high = Integer.parseInt(lowAndHigh[1]);
            char target = parts[1].charAt(0);
            String pwd = parts[2];
          
            for(char c : pwd.toCharArray())
                count += c == target ? 1 : 0;
            
            valid += (low <= count) && (count <= high) ? 1 : 0;
        }
        System.out.println(valid);
    }
}