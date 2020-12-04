import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class part1 {
    public static void main(String... ags) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("day4/input.txt"));
        String[] strings = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid"};

        int count = 0;

        //eyr:2021 hgt:168cm hcl:#fffffd pid:180778832 byr:1923 ecl:amb iyr:2019 cid:241
        while(sc.hasNextLine()){
            boolean valid = true;
            StringBuilder sb = new StringBuilder();
            while(true){
                String line = sc.hasNextLine() ? sc.nextLine() : "";
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