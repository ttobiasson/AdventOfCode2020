import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class part2 {
    public static void main(String... args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        ArrayList<Integer>list = new ArrayList<Integer>();

        while(sc.hasNextLine()){
            char[] line = sc.nextLine().toCharArray();
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