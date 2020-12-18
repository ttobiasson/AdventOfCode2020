import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
//https://adventofcode.com/2020/day/11
public class part2 {
    public static void main(String... args) throws IOException {
        List<String>lines = Files.readAllLines(Path.of("input.txt"));
        List<String>newLines = makeSeatTakenOrFree(lines);
        
        printTakenSeats(newLines);
    }

    private static void printTakenSeats(List<String> newLines) {
        long prev = 1;

        while(true){
            newLines = makeSeatTakenOrFree(newLines);
            long seats = getTotalNumberOfOccupiedSeats(newLines);
            if(seats == prev){
                System.out.println(seats);
                break;
            }
            prev = seats;
        }
    }

    private static List<String> makeSeatTakenOrFree(List<String>lines){
        List<String>newLines = new ArrayList<String>();

        for(int i = 0; i < lines.size(); i++){
            char[] line = lines.get(i).toCharArray();
            for(int j = 0; j < lines.get(i).length(); j++){

                int ans = getOccupiedSeats(lines, i, j);
                if(line[j] != '.'){
                    if(ans == 0){
                        line[j] = '#';
                    }
                    else if(ans >= 5){
                        line[j] = 'L';
                    }
                }
            }
            newLines.add(String.valueOf(line));
        }
        return newLines;
    }
    private static int getOccupiedSeats(List<String>lines, int index, int jndex){
        int count = 0;
        HashSet<ArrayList<Integer>> coords = getFirstVisibleSeatCoordinates(lines, index, jndex);

        for(ArrayList<Integer> coord : coords){
            int i = coord.get(0);
            int j = coord.get(1);
            if(lines.get(i).charAt(j) == '#'){
                count++;
            }
        }
        return count;
    }
    private static long getTotalNumberOfOccupiedSeats(List<String>lines){
        int count = 0;
                                
        for(int i = 0; i < lines.size(); i++){
            for(int j = 0; j < lines.get(i).length(); j++){
                if(lines.get(i).charAt(j) == '#')
                    count++;
            }
        }
        return count;
    }
    private static HashSet<ArrayList<Integer>> getFirstVisibleSeatCoordinates(List<String>lines, int index, int jndex){
        
        HashSet<ArrayList<Integer>>coordinateList = new HashSet<ArrayList<Integer>>();
        ArrayList<Integer>firstSeatCoordinates = new ArrayList<Integer>();
        
        int[] dirs = new int[]{1,0,1,-1,0,-1,-1,-1,-1,0,-1,1,0,1,1,1};
        int lineLength = lines.get(0).length();
        int matrixSize = lines.size();

        for(int i = 0; i < dirs.length; i+=2){
            int tmpi = index;
            int tmpj = jndex;
            
            while(true){
                tmpj+=dirs[i+1];
                tmpi+=dirs[i];

                if((tmpj < 0) || (tmpj > lineLength-1) || (tmpi < 0) || (tmpi > matrixSize-1))break;
                if(lines.get(tmpi).charAt(tmpj) != '.'){
                    firstSeatCoordinates.add(tmpi);
                    firstSeatCoordinates.add(tmpj);
                    coordinateList.add(firstSeatCoordinates);
                    firstSeatCoordinates = new ArrayList<Integer>();
                    break;
                }
            }
        }
        return coordinateList;
    }
}