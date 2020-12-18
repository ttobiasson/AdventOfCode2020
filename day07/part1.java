import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
//https://adventofcode.com/2020/day/7
public class part1 {
    static int count = 0;
    public static void main(String... args) throws IOException {
 
        List<String> lines = Files.readAllLines(Path.of("input.txt"));
        List<List<String>>newLines = new ArrayList<List<String>>();

        modifyEachLine(lines, newLines);

        printBagCount(lines, newLines);
        

    }
    private static void printBagCount(List<String> lines, List<List<String>> newLines) {
        for(int i = 0; i < lines.size(); i++)
            check(newLines, newLines.get(i).get(0));
        System.out.println(count);
    }
    private static void modifyEachLine(List<String> strList, List<List<String>> lines) {
        for(String str : strList){
            var list = new ArrayList<String>();
            for(String s : getString(str))
                list.add(s);
            lines.add(list);
        }
    }
    private static void check(List<List<String>>list, String b){
        String bagToCheck = b.trim();
        String target = "shiny gold";
        int counted = count;
        for(List<String>list1 : list){
            if(list1.get(0).contains(bagToCheck)){
                for(String s : list1){
                    if(!s.contains(bagToCheck)){
                        if(s.contains(target))
                            count++;
                        else{
                            if(counted == count)
                                check(list, s);   
                        }
                    }
                }
            }
        }


    }
    private static String[] getString(String line){

        String[] values =  line.substring(0, line.length()-1)
                            .replaceAll("contain", "")
                            .replaceAll(",", "")
                            .split(" [0-9]");
        
        return values;
    }
}