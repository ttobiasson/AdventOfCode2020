import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Iterator;
//https://adventofcode.com/2020/day/6
public class part1 {
    public static void main(String... args) throws IOException {
        Iterator<String>iter = Files.readAllLines(Path.of("input.txt")).iterator();
        HashSet<Character> hs = new HashSet<Character>();
        long count = 0;

        while(iter.hasNext()){
            while(iter.hasNext()){
                
                String line = iter.next();
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