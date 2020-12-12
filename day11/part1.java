import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class part1 {
    public static void main(String... args) throws IOException {
        List<String>lines = Files.readAllLines(Path.of("input.txt"));
        List<String>newLines = calc(lines);
        int prev = 1;
        while(true){
            newLines = calc(newLines);
            int seats = getTotalNumberOfOccupiedSeats(newLines);
            
            if(seats == prev){
                System.out.println(seats);
                break;
            }
            prev = seats;
        }
    }
    static List<String> calc(List<String>lines){
        List<String>newLines = new ArrayList<String>();

        for(int i = 0; i < lines.size(); i++){
            char[] line = lines.get(i).toCharArray();
            for(int j = 0; j < lines.get(i).length(); j++){
                int ans = getOccupiedSeats(lines, i, j);
                if(line[j] != '.'){
                    if(ans == 0){
                        line[j] = '#';
                    }
                    else if(ans >= 4){
                        line[j] = 'L';
                    }
                }
            }
            newLines.add(String.valueOf(line));
        }
        return newLines;
    }
    static int getOccupiedSeats(List<String>lines, int index, int jndex){
        int count = 0;
        int lineLength = lines.get(index).length();
        int matrixSize = lines.size();
            for(int i = index-1; i < index+2; i++){
                for(int j = jndex-1; j < jndex+2; j++){
                    if(i == index && j == jndex){}
                    else if(j >= lineLength || j < 0 || i >= matrixSize || i < 0){}
                    else{
                        char c = lines.get(i).charAt(j);
                        count += c == '#' ? 1 : 0;
                    }
                }
            }
        return count;
    }
    static int getTotalNumberOfOccupiedSeats(List<String>lines){
        int count = 0;
        for(int i = 0; i < lines.size(); i++){
            for(int j = 0; j < lines.get(i).length(); j++){
                char c = lines.get(i).charAt(j);
                count += c == '#' ? 1 : 0;
            }
        }
        return count;
    }
}