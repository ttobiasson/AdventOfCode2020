import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class part2 {
    static int count = 0;
    public static void main(String... args) throws IOException {

        List<String> strList = Files.readAllLines(Path.of("input.txt"));
        List<List<String>>lines = new ArrayList<List<String>>();

        for(String str : strList){
            String[] line = str.substring(0, str.length()-1).replaceAll(" contain", ",").split(",");
            var list = new ArrayList<String>();
            for(String s : line)
                list.add(s.trim());
            lines.add(list);
        }

        int ans = check(lines, "shiny gold")-1;
        System.out.println(ans);
    }
    static int check(List<List<String>>list, String bagToCheck){

        int ans = 1;
        for(List<String> list1  : list){
            if(list1.get(0).contains(bagToCheck) && !list1.get(1).contains("no other")){
                for(int i = 1; i < list1.size(); i++){
                    String newBagToCheck = list1.get(i).substring(2,list1.get(i).length());
                    ans += Integer.valueOf(list1.get(i).substring(0,1)) * check(list, newBagToCheck);
                }
            }      
        }
        return ans;
    }
}