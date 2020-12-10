import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class part2 {
    public static void main(String... args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        final ArrayList<String>strings = new ArrayList<String>();
        final HashSet<Character> hs = new HashSet<Character>();
        long count = 0;

        while(sc.hasNextLine()){

            while(sc.hasNextLine()){
                String line = sc.nextLine();
                if(line.equals(""))
                    break;
                strings.add(line);
                for(char c: line.toCharArray())
                    hs.add(c);
            }
            strings.stream().map(s -> s.toCharArray()).forEach(chars -> {
                for(char c : chars){
                    for(String s : strings){
                        if(s.indexOf(c) == -1)
                            hs.remove(c);
                    }
                }
            });

            count += hs.size();

            strings.clear();
            hs.clear();
        }
        System.out.println(count);

    }
}