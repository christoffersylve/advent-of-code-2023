
// Standard imports for reading files
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.charset.StandardCharsets.UTF_8;
// 

class Day1 {

    public static void main(String[] args) throws IOException {
        File file = new File("day1/input.txt");
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);
        System.out.println("Solution: " + problem1(fileContent));
    }

    private static int problem1(List<String> list) {
        // Pointer 1 and Pointer 2 go from each side.
        // Once both pointers have  a number, the first one is multiplied by ten and added to the second one.
        // Append to list of numbers. 
        // All numbers are then added together and returned as the sulotion. 
        int sum = 0;

        for(int i = 0; i<list.size(); i++) {
            int num1 = 0;
            for(char c: list.get(i).toCharArray()) {
                if(Character.isDigit(c)) {
                    System.out.println( "First" + c);
                    num1 = Character.getNumericValue(c);
                    break;
                }
            }
            int num2 = 0;
            for(int j = list.get(i).toCharArray().length - 1; j >= 0; j--) {
                if(Character.isDigit(list.get(i).toCharArray()[j])) {
                    num2 = Character.getNumericValue(list.get(i).toCharArray()[j]);
                    break;
                }
            }
            System.out.println("num1 " + num1 + " num2: " + num2);
            sum += (num1 * 10) + num2;
        }
        return sum;
    }
    
}