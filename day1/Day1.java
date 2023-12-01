
// Standard imports for reading files
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import static java.nio.charset.StandardCharsets.UTF_8;
// 

class Day1 {

    private static List<String> fileContent;

    public static void main(String[] args) throws IOException {
        readFromFile("input.txt");
        problem1(fileContent);
        problem2(fileContent);
    }

    private static void readFromFile(String filePath) throws IOException{
        File file = new File(filePath);
        fileContent = Files.readAllLines(file.toPath(), UTF_8);
    }

    private static void problem1(List<String> list) {

        int sum = 0;

        for(int i = 0; i<list.size(); i++) {
            int num1 = 0;
            for(char c: list.get(i).toCharArray()) {
                if(Character.isDigit(c)) {
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

            sum += (num1 * 10) + num2;
        }
        
        System.out.println("Solution 1: " + sum);
    }

    private static void problem2(List<String> list) {
        int sum = 0;

        for(int i = 0; i<list.size(); i++) {

            StringBuilder sb = new StringBuilder();

            int num1 = 0;

            for(char c: list.get(i).toCharArray()) {
                if(Character.isDigit(c)) {
                    num1 = Character.getNumericValue(c);
                    break;
                } else {
                    sb.append(c);
                    int potentialNumber = numberInString(sb.toString());
                    if(potentialNumber > 0) {
                        num1 = potentialNumber;
                        break;
                    }
                }
            }

            sb = new StringBuilder();

            int num2 = 0;

            for(int j = list.get(i).toCharArray().length - 1; j >= 0; j--) {
                if(Character.isDigit(list.get(i).toCharArray()[j])) {
                    num2 = Character.getNumericValue(list.get(i).toCharArray()[j]);
                    break;
                } else {
                    sb.insert(0, list.get(i).toCharArray()[j]);
                    int potentialNumber = numberInString(sb.toString());
                    if(potentialNumber > 0) {
                        num2 = potentialNumber;
                        break;
                    }
                }
            }

            sum += (num1 * 10) + num2;

        }

        System.out.println("Solution 2: " + sum);
    } 

    private static int numberInString(String chars) {
        for(int i = 0; i<chars.length(); i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = i; j<chars.length(); j++) {
                sb.append(chars.charAt(j));
                int num = isNumber(sb.toString());
                if(num != 0) return num;
            }
        }
        return 0;
    }

    private static int isNumber(String s) {
        if(s.equals("one")) {
            return 1;
        } else if (s.equals("two")) {
            return 2;
        } else if (s.equals("three")) {
            return 3;
        } else if (s.equals("four")) {
            return 4;
        } else if (s.equals("five")) {
            return 5;
        } else if (s.equals("six")) {
            return 6;
        } else if (s.equals("seven")) {
            return 7;
        } else if (s.equals("eight")) {
            return 8;
        } else if (s.equals("nine")) {
            return 9;
        } else {
            return 0;
        }
    }

    
}