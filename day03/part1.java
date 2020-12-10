import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class part1 {
    public static void main(String... args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        ArrayList<ArrayList<Character>> list = new ArrayList<ArrayList<Character>>();
        ArrayList<Character>row;
        int count = 0;
        int rowSize, listSize;

        while(sc.hasNextLine()){
            row = new ArrayList<Character>();
            char[] arr = sc.nextLine().toCharArray();
            for(char c : arr)
                row.add(c);
            list.add(row);
            
        }
        rowSize = list.get(0).size();
        listSize = list.size();
        int j = 3;
        
        for(int i = 1; i < listSize; i++){
                
            j = j >= rowSize ? j-rowSize : j;
                
            count += list.get(i).get(j).equals('#') ? 1 : 0;
            
            j+=3;
        }

        System.out.println(count);




    }
}