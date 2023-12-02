

//Standard imports for reading files
import java.util.HashMap;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.charset.StandardCharsets.UTF_8;
//

public class Day2 {

    private static List<String> fileContent;
    private static HashMap<String, Integer> maxValues = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        readFromfile("input.txt");
        problem1(fileContent);
        problem2(fileContent);
    }

    private static void readFromfile(String filePath) throws IOException  {
        File file = new File(filePath);
        fileContent = Files.readAllLines(file.toPath(), UTF_8);
    }

    private static void problem1(List<String> input) {
        maxValues.put("red",12);
        maxValues.put("green",13);
        maxValues.put("blue",14);
        int sum = 0;
        int index = 1;
        for(String s1 : input) {
            boolean willWork = true;
            String[] split1 = s1.split(":");
            for(String s2 : split1) {
                String[] split2  = s2.split(";");
                for(String s3 : split2) {
                    String[] split3 = s3.split(",");
                    for(String s4 : split3) {
                        if(!willWork) break;
                        String[] split4 = s4.strip().split(" ");
                        if(split4[0].equals("Game")) break;
                        if(maxValues.get(split4[1]) < Integer.parseInt(split4[0]))  {
                            willWork = false;
                        }
                    }
                }
            }
            if(willWork) sum+=index;
            index++;
        }
        System.out.println("Sum of working index: " + sum);
    }

    private static void problem2(List<String> input) {
        int[] maxVal  = {0,0,0}; // RGB
        int sum = 0;
        for(String s1 : input) {
            String[] split1 = s1.split(":");
            for(String s2 : split1) {
                String[] split2  = s2.split(";");
                for(String s3 : split2) {
                    String[] split3 = s3.split(",");
                    for(String s4 : split3) {

                        String[] split4 = s4.strip().split(" ");

                        if(split4[0].equals("Game")) break;

                        if(split4[1].equals("red")) { 
                            if(maxVal[0] < Integer.parseInt(split4[0])) maxVal[0] = Integer.parseInt(split4[0]);
                        }

                        if(split4[1].equals("blue")) {  
                            if(maxVal[1] < Integer.parseInt(split4[0])) maxVal[1] = Integer.parseInt(split4[0]);
                        }

                        if(split4[1].equals("green")) {
                            if(maxVal[2] < Integer.parseInt(split4[0])) maxVal[2] = Integer.parseInt(split4[0]);
                        }

                    }
                }
            }
            sum += maxVal[0] * maxVal[1] * maxVal[2];
            for(int i = 0; i<maxVal.length; i++) maxVal[i] = 0; // Reset for next game
        }
        System.out.println("PowerSet: " + sum);
    }



    
}
