import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class part1 {
    public static void main(String... args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        HashSet<Character> hs = new HashSet<Character>();;
        long count = 0;

        while(sc.hasNextLine()){
            
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.equals(""))
                    break;
                for(char c: line.toCharArray())
                    hs.add(c);
            }
            count += hs.size();
            hs.clear();
        }
        System.out.println(count);

    }
}