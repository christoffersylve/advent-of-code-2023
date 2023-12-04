// Standard imports for reading files
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.charset.StandardCharsets.UTF_8;
// 
public class Day3 {

    private static int VMAX, HMAX;
    private static ArrayList<ArrayList<String>> matrix;
    private static HashMap<String,ArrayList<Integer>> gearsCoord;

    public static void main(String[] args) throws IOException {
        readFromFile("inputE.txt");
        problem1();
        System.out.println("----------------------------");
        newProblem1();
    }

    private static void readFromFile(String filePath) throws IOException{
        File file = new File(filePath);
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);
        matrix = new ArrayList<>(); 
        for(String s : fileContent) {
            matrix.add(new ArrayList<String>(Arrays.asList(s.split(""))));
        }
    }

    private static void problem1() {
        gearsCoord = new HashMap<>();
        VMAX = matrix.size();
        HMAX = matrix.get(0).size(); // Assuming all strings have the same length. 
        int sum = 0;
        int totalGearRatio = 0;
        StringBuilder sb = new StringBuilder();
        boolean validNum = false;
        boolean addedNumber = false;
        for(int r = 0; r<VMAX; r++) {
            HashSet<String> foundCoords = new HashSet<>();
            for(int c = 0; c<HMAX; c++) {
                addedNumber = false;
                String current = matrix.get(r).get(c); 
                // Check if spot is a number
                if(!isNumeric(current) || c == HMAX-1){
                    if(isNumeric(current) && validNum) sb.append(current); 
                    if(isNumeric(sb.toString()) && validNum){
                        int finNum =Integer.parseInt(sb.toString());
                        sum += finNum;
                        for(String s : foundCoords) {
                            if(gearsCoord.containsKey(s)) {
                                gearsCoord.get(s).add(finNum);
                            } else {
                                gearsCoord.put(s, new ArrayList<>());
                                gearsCoord.get(s).add(finNum);
                            }
                        }
                        foundCoords = new HashSet<>();
                    }
                    sb = new StringBuilder();
                    validNum = false;
                    continue;
                } 
                // // Up
                if(r != 0) {
                    String uLeft = ".";
                    String uMid = ".";
                    String uRight = "."; 
                    uMid = matrix.get(r-1).get(c);
                    if(c != 0) uLeft = matrix.get(r-1).get(c-1);
                    if(c != HMAX-1) uRight = matrix.get(r-1).get(c+1);
                    if(isSpecialChar(uLeft) || isSpecialChar(uMid) || isSpecialChar(uRight) || validNum) {
                        if(uLeft.equals("*")) foundCoords.add((r-1) + " " + (c-1));
                        if(uMid.equals("*")) foundCoords.add((r-1) + " " + (c));
                        if(uRight.equals("*")) foundCoords.add((r-1) + " " + (c+1));
                        if(!addedNumber) {
                            sb.append(current);
                            addedNumber = true;
                        }
                        validNum = true;
                        //continue;
                    }
                }
                // // Left
                if(c != 0) {
                    String left = matrix.get(r).get(c-1);
                    if(isSpecialChar(left)) {
                        if(!addedNumber) {
                            sb.append(current);
                            addedNumber = true;
                        }
                        validNum = true;
                        //continue;
                    }
                    if(left.equals("*")) foundCoords.add((r) + " " + (c-1));
                }
                // // Down 
                if(r != VMAX-1) {
                    String dLeft = ".";
                    String dMid = ".";
                    String dRight = "."; 
                    dMid = matrix.get(r+1).get(c);
                    if(c != 0) dLeft = matrix.get(r+1).get(c-1);
                    if(c != HMAX-1) dRight = matrix.get(r+1).get(c+1);
                    if(isSpecialChar(dLeft) || isSpecialChar(dMid) || isSpecialChar(dRight) || validNum) {
                        if(dLeft.equals("*")) foundCoords.add((r+1) + " " + (c-1));
                        if(dMid.equals("*")) foundCoords.add((r+1) + " " + (c));
                        if(dRight.equals("*")) foundCoords.add((r+1) + " " + (c+1));
                        if(!addedNumber) {
                            sb.append(current);
                            addedNumber = true;
                        }
                        validNum = true;
                        //continue;
                    };
                }
                // // Right
                if(c != HMAX-1) {
                    String right = matrix.get(r).get(c+1);
                    if(isSpecialChar(right)) {
                        if(!addedNumber) {
                            sb.append(current);
                            addedNumber = true;
                        }
                        validNum = true;
                        //continue;
                    } else if (isNumeric(right)) {
                        if(!addedNumber) {
                            sb.append(current);
                            addedNumber = true;
                        }
                    } 
                    if(right.equals("*")) foundCoords.add((r) + " " + (c+1));
                }
            }
        }
        for(String key : gearsCoord.keySet()) {
            int l = 1;
            if(gearsCoord.get(key).size() <= 1) continue;
            for(Integer i : gearsCoord.get(key)) {
                l = i * l;
            }
            totalGearRatio += l;
        }
        System.out.println("Sum of valid numbers: " + sum);
        System.out.println("Sum of gear ratio: " + totalGearRatio);
    }
    
    private static boolean isSpecialChar(String s) {
        return !".0123456789".contains(s);
    }

    public static boolean isNumeric(String string) { 
        if(string == null || string.equals("")) return false;
        try {
            int intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

}
