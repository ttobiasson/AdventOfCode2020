import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;

public class part1 {
    public static void main(String... args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("input.txt"));
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
                    if(!hs.add(index+inc)){
                        System.out.println(acc);
                        System.exit(0);
                    }
                    break;
            }
            index += inc;
        }
    }
}