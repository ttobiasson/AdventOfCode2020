import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
//https://adventofcode.com/2020/day/3
public class part2 {
    public static void main(String... args) throws IOException {
        List<String>lines = Files.readAllLines(Path.of("input.txt"));
        printTrees(lines);
    }
    private static void printTrees(List<String> lines) {
        int totalCount = 1;
        int rowSize, listSize;
        int[] steps = new int[]{1,1,3,1,5,1,7,1,1,2};

        rowSize = lines.get(0).length();
        listSize = lines.size();

        for(int s = 0; s < steps.length-1; s+=2){
            int j = steps[s];
            int i = steps[s+1];
            int count = 0;

            while(i < listSize){
                j = j >= rowSize ? j-rowSize : j;
                
                count += lines.get(i).charAt(j) == '#' ? 1 : 0;
            
                j += steps[s];
                i += steps[s+1];
           }
           totalCount *= count;
        }
        System.out.println(totalCount);
    }
}