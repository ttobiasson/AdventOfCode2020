import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class part1 {
    static final int NORTH = 3;
    static final int WEST = 2;
    static final int SOUTH = 1;
    static final int EAST = 0;
    static int heading = 0;
    public static void main(String... args) throws IOException {
        Iterator<String> iter = Files.lines(Path.of("input.txt")).iterator();
        findPosition(iter);
    }
    static void findPosition(Iterator<String> iter) {
        int[] positions = new int[]{0,0,0,0};
        
        while (iter.hasNext()) {
            String line = iter.next();
            String action = line.substring(0,1);
            int value = Integer.valueOf(line.substring(1));
            
            switch(action){
                case "F":
                    positions[heading]+=value;
                    break;
                case "N":
                    positions[NORTH]+=value;
                    break;
                case "E":
                    positions[EAST]+=value;
                    break;
                case "S":
                    positions[SOUTH]+=value;
                    break;
                case "W":
                    positions[WEST]+=value;
                    break;
                default:
                    turn(value, action);
                    break;
            }
        }
        System.out.println(Math.abs(positions[0]-positions[2]) + Math.abs(positions[1]-positions[3]));
    }
    static void turn(int value, String dir){
        switch(value){
            case 270:
                if(dir.equals("R"))
                    turn(90, "L");
                else
                    turn(90, "R");
                break;
            case 90:
                if(dir.equals("R"))
                    heading = heading == NORTH ? EAST : heading+1;
                else
                    heading = heading == EAST ? NORTH : heading-1;
                break;
            default:
                heading = heading > 1 ? heading-2 : heading+2;
                break;
        }
    }
}
