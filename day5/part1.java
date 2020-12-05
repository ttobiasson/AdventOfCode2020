import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class part1 {
    public static void main(String... args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        int max = 0;
        while(sc.hasNextLine()){
            char[] line = sc.nextLine().toCharArray();
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