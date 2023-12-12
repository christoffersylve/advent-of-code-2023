package day11;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;


public class Day11 {

    static ArrayList<long[]> points = new ArrayList<>();
    static ArrayList<Long> verticalSpaces = new ArrayList<>();
    static ArrayList<Long> horizontalSpaces = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        readFromFile("day11/input.txt");
        problem(1); // Problem 1
        problem(999999); // Problem 2
    }

    private static void readFromFile(String filePath) throws IOException{
        File file = new File(filePath);
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);
        boolean isVerSpace = true;
        // Adding points and index of vertical space
        for(int i=0; i<fileContent.size(); i++) {
            for(int j=0; j<fileContent.get(i).length(); j++) {
                if(fileContent.get(i).charAt(j) != '.') {
                    long[] p = {j,i};
                    points.add(p);
                    isVerSpace = false;
                }
            }
            if(isVerSpace) verticalSpaces.add((long) i);
            isVerSpace = true;
        }
        boolean isHorSpace = true;
        // Adding index of horizontal space  
        for(int i=0; i<fileContent.get(0).length(); i++) {
            for(int j=0; j<fileContent.size(); j++) {
                if(fileContent.get(j).charAt(i) != '.') isHorSpace = false;
            }
            if(isHorSpace) horizontalSpaces.add((long) i);
            isHorSpace = true;
        }
    }

    private static void problem(long space) {
        long sum = 0;
        for(long[] start:points) {
            for(long[] end:points) {
                long addedSpace = 0;
                for(long v : verticalSpaces) if((start[1] > v && end[1] < v) || (start[1] < v && end[1] > v)) addedSpace++;
                for(long v : horizontalSpaces) if((start[0] > v && end[0] < v) || (start[0] < v && end[0] > v)) addedSpace++;
                sum += (addedSpace*space) + Math.abs(end[0]-start[0]) + Math.abs(end[1]-start[1]);
            }
        }
        System.out.println("Solution: "+sum/2);
    }
}
