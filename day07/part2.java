import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
//https://adventofcode.com/2020/day/7
public class part2 {
    static int count = 0;
    public static void main(String... args) throws IOException {

        List<String> lines = Files.readAllLines(Path.of("input.txt"));
        List<List<String>>newLines = new ArrayList<List<String>>();

        modifyLines(lines, newLines);

        int ans = check(newLines, "shiny gold")-1;
        System.out.println(ans);
    }
    private static void modifyLines(List<String> strList, List<List<String>> lines) {
        for(String str : strList){
            String[] line = str.substring(0, str.length()-1).replaceAll(" contain", ",").split(",");
            var list = new ArrayList<String>();
            for(String s : line)
                list.add(s.trim());
            lines.add(list);
        }
    }
    static int check(List<List<String>>list, String bagToCheck){

        int ans = 1;
        for(List<String> list1  : list){
            if(list1.get(0).contains(bagToCheck) && !list1.get(1).contains("no other")){
                for(int i = 1; i < list1.size(); i++){
                    String newBagToCheck = list1.get(i).substring(2);
                    ans += Integer.valueOf(list1.get(i).substring(0,1)) * check(list, newBagToCheck);
                }
            }      
        }
        return ans;
    }
}