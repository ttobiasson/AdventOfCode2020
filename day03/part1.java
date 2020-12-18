import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
//https://adventofcode.com/2020/day/3
public class part1 {
    public static void main(String... args) throws IOException {
        Iterator<String>iter = Files.lines(Path.of("input.txt")).iterator();
        printTrees(iter);
    }
    private static void printTrees(Iterator<String> iter) {
        int count = 0;
        int j = 3;
        iter.next();
        while(iter.hasNext()){
            String line = iter.next();
            j = j >= line.length() ? j-line.length() : j;
            count += line.charAt(j) == '#' ? 1 : 0;
            j+=3;
        }   
        System.out.println(count);
    }
}