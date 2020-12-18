import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
//https://adventofcode.com/2020/day/5
public class part1 {
    public static void main(String... args) throws IOException {
        Iterator<String>iter = Files.lines(Path.of("input.txt")).iterator();
        printSeatID(iter);
    }

    private static void printSeatID(Iterator<String> iter) {
        int max = 0;
        while(iter.hasNext()){
            char[] line = iter.next().toCharArray();
            int i = 0;
            int coli = 0;
            int range = 128;
            int colrange = 8;
            for(int k = 0; k < 7; k++){
                i += line[k] =='B' ? range/2 : 0;
                range/=2;
            }
            for(int k = 7; k < 10; k++){
                coli += line[k] == 'R' ? colrange/2 : 0;
                colrange /= 2;
            }
            max = i*8+coli > max ? i*8+coli : max;
        }
        System.out.println(max);
    }
}