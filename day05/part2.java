import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.IntStream;

public class part2 {
    public static void main(String... args) throws IOException {
        Iterator<String>iter = Files.lines(Path.of("input.txt")).iterator();
        printMySeatID(iter);
    }

    private static void printMySeatID(Iterator<String> iter) {
        ArrayList<Integer>list = new ArrayList<Integer>();

        while(iter.hasNext()){
            char[] line = iter.next().toCharArray();
            int i = 0;
            int range = 128;
            int colrange = 8;
            int coli = 0;
            for(int k = 0; k < 7; k++){
                i += line[k] == 'B' ? range/2 : 0;
                range/=2;
            }
            for(int k = 7; k < 10; k++){
                coli += line[k] == 'R' ? colrange/2 : 0;
                colrange/=2;
            }
            list.add(i*8+coli);
        }
        IntStream.range(23, 828).filter(n -> !list.contains(n)).forEach(System.out::println);
    }
}