import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
//https://adventofcode.com/2020/day/4
public class part1 {
    public static void main(String... ags) throws IOException {
        Iterator<String>iter = Files.readAllLines(Path.of("input.txt")).iterator();
        printValidPassports(iter);
    }
    private static void printValidPassports(Iterator<String> iter) {
        String[] strings = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"};

        int count = 0;

        while(iter.hasNext()){
            boolean valid = true;
            StringBuilder sb = new StringBuilder();
            while(true){
                String line = iter.hasNext() ? iter.next() : "";
                if(line.equals("")){
                    break;
                }
                sb.append(line);
            }
            for(String s : strings){
                if(sb.indexOf(s) == -1 && !s.equals("cid")){
                    valid = false;
                    break;
                }
            }
            count += valid ? 1 : 0;
            
        }
        System.out.println(count);
    }
}