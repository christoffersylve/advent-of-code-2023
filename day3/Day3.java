// Standard imports for reading files
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.charset.StandardCharsets.UTF_8;
// 

public class Day3 {

    private static int VMAX, HMAX;

    private static ArrayList<ArrayList<String>> matrix;


    public static void main(String[] args) throws IOException {
        readFromFile("input.txt");
        problem1();
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

        VMAX = matrix.size();
        HMAX = matrix.get(0).size(); // Assuming all strings have the same length. 

        System.out.println("HMAX: " + HMAX + " VMAX: " + VMAX);

        int sum = 0;

        StringBuilder sb = new StringBuilder();

        boolean validNum = false;

        int nrbAdded = 0;

        for(int r = 0; r<VMAX; r++) {
            for(int c = 0; c<HMAX; c++) {

                String current = matrix.get(r).get(c); 

                // Check if spot is a number

                if(!isNumeric(current) || c == HMAX-1){

                    if(isNumeric(current) && validNum) sb.append(current); 

                    if(isNumeric(sb.toString()) && validNum){
                        System.out.println(sb.toString());
                        sum += Integer.parseInt(sb.toString());
                        nrbAdded++;
                    }

                    sb = new StringBuilder();
                    validNum = false;
                    continue;
                } 


                // Check each char L,R,U,D

                // // Up
                if(r != 0) {
                    String uLeft = ".";
                    String uMid = ".";
                    String uRight = "."; 
                    uMid = matrix.get(r-1).get(c);
                    if(c != 0) uLeft = matrix.get(r-1).get(c-1);
                    if(c != HMAX-1) uRight = matrix.get(r-1).get(c+1);
                    if(isSpecialChar(uLeft) || isSpecialChar(uMid) || isSpecialChar(uRight) || validNum) {
                        sb.append(current);
                        validNum = true;
                        continue;
                    }
                }

                // // Left
                if(c != 0) {
                    String left = matrix.get(r).get(c-1);
                    if(isSpecialChar(left)) {
                        sb.append(current);
                        validNum = true;
                        continue;
                    }
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
                        sb.append(current);
                        validNum = true;
                        continue;
                    };
                }

                // // Right
                if(c != HMAX-1) {
                    String right = matrix.get(r).get(c+1);
                    if(isSpecialChar(right)) {
                        sb.append(current);
                        validNum = true;
                        continue;
                    } else if (isNumeric(right)) {
                        sb.append(current);
                    } 
                }

            }
        }

        System.out.println("Sum of valid numbers: " + sum);
        System.out.println("Nbr added: " + nrbAdded);

        
    }

    private static boolean isSpecialChar(String s) {
        return 
        !(s.equals(".") ||
        s.equals("0") ||
        s.equals("1") ||
        s.equals("2") ||
        s.equals("3") ||
        s.equals("4") ||
        s.equals("5") ||
        s.equals("6") ||
        s.equals("7") ||
        s.equals("8") ||
        s.equals("9"));
    }

    public static boolean isNumeric(String string) {
        int intValue;   
        if(string == null || string.equals("")) {
            return false;
        }
        
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }

}
