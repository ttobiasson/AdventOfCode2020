import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
//https://adventofcode.com/2020/day/8
public class part2 {
    static int count = 0;
    public static void main(String... args) throws IOException {
        List<String> file = Files.readAllLines(Path.of("input.txt"));
        printValueAfterTermination(file);
    }
    private static void printValueAfterTermination(List<String> file) {
        int index = 0;
        boolean terminate = false;

        while(true){
            List<String> lines = new ArrayList<String>();
            lines.addAll(file);
            
            if(terminate){
                System.out.println(count);
                System.exit(0);
            }
            while(lines.get(index).contains("acc"))
                index++;
            if(lines.get(index).contains("jmp"))
                lines.set(index, "nop +1");
            else 
                lines.set(index, "jmp"+lines.get(index).substring(3));
            index++;
            terminate = mainloop(lines);
        }
    }
    static boolean mainloop(List<String>lines){
        HashSet<Integer>hs = new HashSet<Integer>();
        int index = 0;
        int acc = 0;

        while(true){
            int inc = 1;
            String[] line = lines.get(index).split(" ");
            String op = line[0];
            String val_op = line[1].substring(0);
            int val = Integer.valueOf(line[1].split("\\+|-")[1]);
            hs.add(index);

            switch(op){
                case "acc": 
                    acc += val_op.contains("+") ? val : -val;
                    break;
                case "jmp":
                    inc = val_op.contains("+") ? val : -val;
                    break;
            }
            count = acc;
            if(!hs.add(index+inc)){
                return false;
            }
            if(index == 625)
                return true;
            index += inc;
        }
    }
}