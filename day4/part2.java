import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class part2 {
    public static void main(String... ags) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day4/input.txt"));
        String[] strings = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"};
        ArrayList<StringBuilder>stringlist = new ArrayList<StringBuilder>();
        int count = 0;

        while(sc.hasNextLine()){
            boolean valid = true;
            StringBuilder sb = new StringBuilder();

            while(true){
                String line = sc.hasNextLine() ? sc.nextLine() : "";
                if(line.equals(""))
                    break;
                sb.append(line + " ");
            }

            for(String s : strings)
                valid = sb.indexOf(s) == -1 && !s.equals("cid") ? false : valid;
            
            if(valid)
                stringlist.add(sb);
        }

        for(int i = 0; i < stringlist.size(); i++){
            boolean valid = true;
            String[] fields = stringlist.get(i).toString().split(" ");

            for(String field : fields){
                String[] values = field.split(":");
                String type = values[0];
                String val = values[1];
                
                switch(type){
                    case "byr":
                        valid = val.matches("(19[2-9][0-9])|(2000)|(2001)|(2002)") ? valid : false;
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
}