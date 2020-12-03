import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class part2 {
    public static void main(String... args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        ArrayList<ArrayList<Character>> list = new ArrayList<ArrayList<Character>>();
        ArrayList<Character>row;
        int count = 0;
        int trueCount = 1;
        int rowSize, listSize;
        int[] steps = new int[]{1,1,3,1,5,1,7,1,1,2};

        while(sc.hasNextLine()){
            row = new ArrayList<Character>();
            char[] arr = sc.nextLine().toCharArray();
            for(char c : arr)
                row.add(c);
            list.add(row);
            
        }
        rowSize = list.get(0).size();
        listSize = list.size();
        int j = 1;
        int i = 1;

        for(int s = 0; s < steps.length-1; s+=2){
            j = steps[s];
            i = steps[s+1];
            count = 0;

            while(i < listSize){
                                
                j = j >= rowSize ? j-rowSize : j;
                
                count += list.get(i).get(j).equals('#') ? 1 : 0;
            
                j += steps[s];
                i += steps[s+1];
           }
           trueCount *= count;
        }
        System.out.println(trueCount);




    }
}