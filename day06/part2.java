import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
//https://adventofcode.com/2020/day/6
public class part2 {
    public static void main(String... args) throws IOException {
        Iterator<String>iter = Files.lines(Path.of("input.txt")).iterator();
        printEverybodysYesses(iter);
    }

    private static void printEverybodysYesses(Iterator<String> iter) {
        final ArrayList<String>strings = new ArrayList<String>();
        final HashSet<Character> hs = new HashSet<Character>();
        long count = 0;

        while(iter.hasNext()){
            while(iter.hasNext()){
                
                String line = iter.next();
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