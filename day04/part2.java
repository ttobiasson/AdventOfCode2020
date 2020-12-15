import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
//https://adventofcode.com/2020/day/4
public class part2 {
    public static void main(String... ags) throws IOException {
        Iterator<String>iter = Files.readAllLines(Path.of("input.txt")).iterator();
        printValidPasswords(iter);
        
    }
    private static void printValidPasswords(Iterator<String> iter) {
        String[] strings = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"};
        ArrayList<StringBuilder>stringlist = new ArrayList<StringBuilder>();
        int count = 0;
        filterInvalidPasswords(iter, strings, stringlist);

        for(StringBuilder string : stringlist){
            boolean valid = true;
            String[] fields = string.toString().split(" ");

            for(String field : fields){
                String[] values = field.split(":");
                String type = values[0];
                String val = values[1];
                
                switch(type){
                    case "byr":
                        valid = val.matches("(19[2-9][0-9])|(200[0-2])") ? valid : false;
                        break;

                    case "iyr": 
                        valid = val.matches("(201[0-9])|(2020)") ? valid : false;
                        break;

                    case "eyr": 
                        valid = val.matches("(202[0-9])|(2030)") ? valid : false;
                        break;
                        
                    case "hgt": 
                        valid = val.matches("(1[5-8][0-9]cm)|(19[0-3]cm)|(6[0-9]in)|(59in)|(7[0-6]in)") ? valid : false;
                        break;

                    case "hcl": 
                        valid = val.matches("#[a-f0-9]{6}") ? valid : false;
                        break;

                    case "ecl": 
                        valid = val.matches("(amb|blu|brn|gry|grn|oth|hzl)") ? valid : false;
                        break;

                    case "pid":
                        valid = val.matches("[0-9]{9}") ? valid : false;
                        break;

                    case "cid": break;
                    
                }
            }
            count += valid ? 1 : 0;
        }
        System.out.println(count);
    }
    private static void filterInvalidPasswords(Iterator<String> iter, String[] strings, ArrayList<StringBuilder> stringlist) {
        while(iter.hasNext()){
            boolean valid = true;
            StringBuilder sb = new StringBuilder();

            while(iter.hasNext()){
                String line = iter.next();
                if(line.equals(""))
                    break;
                sb.append(line + " ");
            }

            for(String s : strings)
                valid = sb.indexOf(s) == -1 && !s.equals("cid") ? false : valid;
            
            if(valid)
                stringlist.add(sb);
        }
    }
}