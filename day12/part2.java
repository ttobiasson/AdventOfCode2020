import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;

public class part2 {
    static final int NORTH = 3;
    static final int WEST = 2;
    static final int SOUTH = 1;
    static final int EAST = 0;
    static int wposx = 10;
    static int wposy = 1;

    public static void main(String... args) throws IOException {
        Iterator<String> iter = Files.lines(Path.of("input.txt")).iterator();
        findPosition(iter);

    }
    static void findPosition(Iterator<String> iter) {
        int shipx = 0;
        int shipy = 0;

        while(iter.hasNext()) {
            String line = iter.next();
            String action = line.substring(0,1);
            int value = Integer.valueOf(line.substring(1));
            
            switch(action){
                case "F":
                    shipy+=value*wposy;
                    shipx+=value*wposx;
                    break;
                case "N":
                    wposy+=value;
                    break;
                case "E":
                    wposx+=value;
                    break;
                case "S":
                    wposy-=value;
                    break;
                case "W":
                    wposx-=value;
                    break;
                default:
                    turn(value, action);
                    break;
            }
        }
        System.out.println(Math.abs(shipx) + Math.abs(shipy));
    }
    static void turn(int value, String dir){
        switch(value){
            case 270:
                String direction = dir.equals("L") ? "R" : "L";
                turn(90, direction);
                break;
            case 180:
                wposy *= -1;
                wposx *= -1;
                break;
            default:
                if(dir.equals("R")){
                    int tmp = wposx;
                    wposx = wposy;
                    wposy = tmp*-1;      
                }
                else{
                    int tmp = wposx;
                    wposx = wposy*-1;
                    wposy = tmp;       
                }
                break;
        }
    }
}