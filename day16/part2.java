import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
//https://adventofcode.com/2020/day/16
public class part2 {
    public static void main(String... args) throws IOException {
        Iterator<String>lines = Files.lines(Path.of("input.txt")).iterator();
        printInvalidErrorRate(lines);
    }
    private static void printInvalidErrorRate(Iterator<String> lines) throws IOException {
        Set<Integer>hs = new HashSet<Integer>();
        List<String>valid = new ArrayList<String>();
        List<List<Integer>>spans = new ArrayList<List<Integer>>();
        while(lines.hasNext()){
            String line = lines.next();
            if(!line.equals("")){

                if(line.contains("your ticket")){
                    while(lines.hasNext()){
                        boolean good = true;
                        line = lines.next();
                        if(line.equals(""))
                            break;
                        String[] numbers = line.split(",");
                        for(String number : numbers){
                            int n = Integer.valueOf(number);
                            if(!hs.contains(n)){
                                good = false;
                            }
                        }
                        if(good)
                            valid.add(line);
                    }
                }
                else if(line.contains("nearby tickets")){
                    while(lines.hasNext()){
                        boolean good = true;
                        line = lines.next();
                        if(line.equals(""))
                            break;
                        String[] numbers = line.split(",");
                        for(String number : numbers){
                            int n = Integer.valueOf(number);
                            if(!hs.contains(n)){
                                good = false;
                            }
                        }
                        if(good)
                            valid.add(line);
                    }       
                }
                else if(!line.contains(",") && !line.equals("")){
                    String[] parts = line.split(" ");
                    
                    removeNonSpansThenAdd(parts, hs, spans);
                }
            }       
        }
        List<String>lines1 = Files.readAllLines(Path.of("input.txt"));
        calcWhichIsWhich(lines1, valid, spans);
    }
    private static void calcWhichIsWhich(List<String> lines, List<String> valid, List<List<Integer>> spans) {
        Map<Integer, List<Integer>>map = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < spans.size(); i++)
            map.put(i, new ArrayList<Integer>());

        for(int i = 0; i < valid.size(); i++){
            String[] stringnumber = valid.get(i).split(",");
            for(int j = 0; j < stringnumber.length; j++){
                
                int number = Integer.valueOf(stringnumber[j]);
                for(int k = 0; k < spans.size(); k++){
                    int a = spans.get(k).get(0);
                    int b = spans.get(k).get(1);
                    int c = spans.get(k).get(2);
                    int d = spans.get(k).get(3);
                    if(!(( (number >= a) && (number <= b)) ||   ((number >= c) && (number <= d)) )){    
                        List<Integer>inner = map.get(k);
                        inner.add(j);
                        map.put(k, inner);
                    }
                }
            }   
        }
        
        List<ArrayList<Integer>>list = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i < map.size(); i++){
            ArrayList<Integer>ny = new ArrayList<Integer>();
            for(int j = 0; j < map.get(i).size(); j++)  
                ny.add(map.get(i).get(j));
            
                list.add(ny);
        }
        System.out.println(map);
        // Use output to calculate which categories can be in each index 
        // then reduce each index by removing the smallest list from each of the other lists
        
    }
    private static void removeNonSpansThenAdd(String[] parts, Set<Integer> hs, List<List<Integer>> spans) {
        List<Integer>span = new ArrayList<Integer>();
        String[] numbers = parts[1].split("-");
        int low = Integer.valueOf(numbers[0]);
        int high = Integer.valueOf(numbers[1]);
        for(int i = low; i <= high; i++){
            hs.add(i);
        }
        span.add(low);
        span.add(high);
        numbers = parts[3].split("-");
        low = Integer.valueOf(numbers[0]);
        high = Integer.valueOf(numbers[1]);
        for(int i = low; i <= high; i++){
            hs.add(i);
        }
        span.add(low);
        span.add(high);
        spans.add(span);
    }
}