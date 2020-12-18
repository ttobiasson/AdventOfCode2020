import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
//https://adventofcode.com/2020/day/14
public class part2 {
    public static void main(String... args) throws IOException {
        Iterator<String>iter = Files.lines(Path.of("input.txt")).iterator();
        readlines(iter);
    }
    private static void readlines(Iterator<String>iter){
        HashMap<Long, Integer>memory = new HashMap<Long, Integer>();
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
                char[] addressChars = Integer.toBinaryString(Integer.valueOf(mem.get(0).replaceAll("[a-z\\[\\]]", ""))).toCharArray();
                List<Character>addressCharsWithZeros = new ArrayList<Character>();
                List<List<Character>> addresses = new ArrayList<List<Character>>();

                addBeginningZeros(maskChars, addressChars, addressCharsWithZeros);
                
                addValueAfterZeros(addressChars, addressCharsWithZeros);
                
                buildAddressFromMask(addressCharsWithZeros, maskChars);

                generateAddresses(addressCharsWithZeros, addresses);

                addValuesToAddressesInMemory(memory, mem, addresses);
                

            }
        }
        printMemorySum(memory);
    }
    private static void addBeginningZeros(char[] maskChars, char[] addressChars, List<Character> addressCharsWithZeros) {
        for(int i = 0; i < maskChars.length-addressChars.length; i++){
            addressCharsWithZeros.add('0');
        }
    }
    private static void addValueAfterZeros(char[] addressChars, List<Character> addressCharsWithZeros) {
        for(char c : addressChars){
            addressCharsWithZeros.add(c);
        }
    }
    private static void buildAddressFromMask(List<Character> addressCharsWithZeros, char[] maskChars) {
        for(int i = 0; i < addressCharsWithZeros.size(); i++){
            if(maskChars[i] == '1')
                addressCharsWithZeros.set(i, '1');
            else if(maskChars[i] == 'X'){
                addressCharsWithZeros.set(i, 'X');
            }
        }
    }
    private static void generateAddresses(List<Character> addressCharsWithZeros, List<List<Character>> addresses) {
        List<String>binarys = new ArrayList<String>();
        makeAllBinaryNumbers(addressCharsWithZeros, binarys);
        makeAllPossibleAddresses(addressCharsWithZeros, addresses, binarys);
    }
    private static void makeAllBinaryNumbers(List<Character> addressCharsWithZeros, List<String> binarys) {
        int xs = countx(addressCharsWithZeros);
        int addressesToGenerate = 1<<xs;
        for(int i = 0; i < addressesToGenerate; i++){
            String bin = Integer.toBinaryString(i);
            StringBuilder sb = new StringBuilder();
            for(int k = 0; k < xs-bin.length(); k++){
                sb.append("0");
            }
            binarys.add(sb.toString()+bin);
        }
    }
    private static int countx(List<Character> addressCharsWithZerosList){
        int count = 0;
        for(char c : addressCharsWithZerosList)
            count+= c == 'X' ? 1 : 0;
        return count;
    }
    private static void makeAllPossibleAddresses(List<Character> addressCharsWithZeros, List<List<Character>> addresses, List<String> binarys) {
        for(int i = 0; i < binarys.size(); i++){
            List<Character>address = new ArrayList<Character>();
            int number = 0;

            address.addAll(addressCharsWithZeros);
            for(int j = 0; j < address.size(); j++){
                char[] binary = binarys.get(i).toCharArray();
                
                if(address.get(j) == 'X'){
                    address.set(j, binary[number]);
                    number++;
                }
            }
            addresses.add(address);
        }
    }
    private static void addValuesToAddressesInMemory(HashMap<Long, Integer> memory, List<String> mem, List<List<Character>> addresses) {
        int value = Integer.valueOf(mem.get(1));

        for(List<Character>address : addresses){
            char[] chs = new char[address.size()];
            for(int i = 0; i < chs.length; i++)
                chs[i] = address.get(i);
            
            String addressString = String.valueOf(chs);
            long key = Long.parseLong(addressString, 2);
            memory.put(key, value);
        }
    }
    private static void printMemorySum(HashMap<Long, Integer> memory) {
        BigInteger sum = BigInteger.ZERO;
        for(long key : memory.keySet()){
            sum = sum.add(BigInteger.valueOf(memory.get(key)));
        }
        System.out.println(sum);
    }
}