import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;

public class part1{
    public static void main(String...args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("day1/input.txt"));
        ArrayList<Integer>list = new ArrayList<Integer>();
        
        while(sc.hasNextInt())
            list.add(sc.nextInt());
        
        
        int product = list.stream().filter(x -> list.contains(2020 - x))
                                   .reduce(1, (x, y) -> x * y);

        System.out.println(product);
    }
}