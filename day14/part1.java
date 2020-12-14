import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class part1 {
    public static void main(String... args) throws IOException {
        Iterator<String>iter = Files.readAllLines(Path.of("input.txt")).iterator();
        readlines(iter);
    }
    static void readlines(Iterator<String>iter){
        HashMap<Integer, Long>memory = new HashMap<Integer, Long>();
        BigInteger sum = BigInteger.ZERO;
        String mask = "";
        
        
        while(iter.hasNext()){
            String line = iter.next();
            
            if(line.contains("mask")){
                mask = line.split(" = ")[1];
            }
            else{
                List<String>mem = new ArrayList<String>();
                mem.addAll(Arrays.asList(line.split(" = ")));
                char[] maskChars = mask.toCharArray();
                char[] valueChars = Integer.toBinaryString(Integer.valueOf(mem.get(1))).toCharArray();
                List<Character>valueCharsWithZeros = new ArrayList<Character>();
                
                addBeginningZeros(maskChars, valueChars, valueCharsWithZeros);
                
                addValueAfterZeros(valueChars, valueCharsWithZeros);
                
                replaceXs(maskChars, valueCharsWithZeros);
                
                putValueInMemory(memory, mem, maskChars);
            }
        }
        printMemorySum(memory, sum);
    }
    private static void printMemorySum(HashMap<Integer, Long> memory, BigInteger sum) {
        for(int key : memory.keySet()){
            sum = sum.add(BigInteger.valueOf(memory.get(key)));
        }
        System.out.println(sum);
    }
    private static void replaceXs(char[] maskChars, List<Character> valueCharsWithZeros) {
        for(int i = 0; i < valueCharsWithZeros.size(); i++){
            maskChars[i] = maskChars[i] == 'X' ? valueCharsWithZeros.get(i) : maskChars[i];
        }
    }
    private static void putValueInMemory(HashMap<Integer, Long> memory, List<String> mem, char[] maskChars) {
        long value = Long.parseLong(String.valueOf(maskChars), 2);
        int index = Integer.valueOf(mem.get(0).replaceAll("[a-z\\[\\]]", ""));
        memory.put(index, value);
    }
    private static void addValueAfterZeros(char[] valueChars, List<Character> valueCharsWithZeros) {
        for(char c : valueChars){
            valueCharsWithZeros.add(c);
        }
    }
    private static void addBeginningZeros(char[] maskChars, char[] valueChars, List<Character> valueCharsWithZeros) {
        for(int i = 0; i < maskChars.length-valueChars.length; i++){
            valueCharsWithZeros.add('0');
        }
    }
}