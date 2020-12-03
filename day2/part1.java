import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class part1 {
    public static void main(String[] arg) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        int valid = 0;
        while(sc.hasNextLine()){
            // 1-3 g: gggg
            int count = 0;
            int low = 0;
            int high = 0;
            char target;
            String pwd = null;
            String s = sc.nextLine().replace(":", "");
            String[] part = s.split("-");
            ArrayList<String>strings = new ArrayList<String>();

            for(String p : part){
                if(p.contains(" ")){
                    for(String pp : p.split(" ")){
                        strings.add(pp);
                    }
                }
                else strings.add(p);
                    
            }
            
            low = Integer.parseInt(strings.get(0));
            high = Integer.parseInt(strings.get(1));
            target = strings.get(2).charAt(0);
            pwd = strings.get(3);
            
            for(char c : pwd.toCharArray()){
                if(c == target)
                    count++;
            }
            if(low <= count && count <= high){
                valid++;
            }
        }
        System.out.println(valid);



    }
}