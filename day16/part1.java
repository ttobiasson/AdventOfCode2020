import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
//https://adventofcode.com/2020/day/16
public class part1 {
    public static void main(String... args) throws IOException {
        Iterator<String>lines = Files.lines(Path.of("input.txt")).iterator();
        printInvalidErrorRate(lines);
    }
    private static void printInvalidErrorRate(Iterator<String> lines){
        Set<Integer>hs = new HashSet<Integer>();
        List<Integer>invalid = new ArrayList<Integer>();
        while(lines.hasNext()){
            String line = lines.next();
            if(!line.equals("")){

                if(line.contains("your ticket")){
                    while(lines.hasNext()){
                        line = lines.next();
                        if(line.equals(""))
                            break;
                        String[] numbers = line.split(",");
                        for(String number : numbers){
                            int n = Integer.valueOf(number);
                            if(!hs.contains(n)){
                                invalid.add(n);
                            }
                        }
                    }
                }
                else if(line.contains("nearby tickets")){
                    while(lines.hasNext()){
                        line = lines.next();
                        if(line.equals(""))
                            break;
                        String[] numbers = line.split(",");
                        for(String number : numbers){
                            int n = Integer.valueOf(number);
                            if(!hs.contains(n)){
                                invalid.add(n);
                            }
                        }
                    }
                }
                else if(!line.contains(",") && !line.equals("")){
                    String[] parts = line.split(" ");
                    removeNonSpansThenAdd(parts, hs);
                }
            }       
        }
        long ans = invalid.stream().reduce(0, (x,y) -> x+y).longValue();
        System.out.println(ans);
        
    }

    private static void removeNonSpansThenAdd(String[] parts, Set<Integer>hs) {
        String[] numbers = parts[1].split("-");
        int low = Integer.valueOf(numbers[0]);
        int high = Integer.valueOf(numbers[1]);
        for(int i = low; i <= high; i++){
            hs.add(i);
        }
        numbers = parts[3].split("-");
        low = Integer.valueOf(numbers[0]);
        high = Integer.valueOf(numbers[1]);
        for(int i = low; i <= high; i++){
            hs.add(i);
        }
        
    }
}