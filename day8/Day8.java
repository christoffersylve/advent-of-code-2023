package day8;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day8 {

    static HashMap<String,String[]> map = new HashMap<>();
    static ArrayList<String> startingNodes = new ArrayList<>();
    static String instructions;
    public static void main(String[] args) throws IOException {
        readFromFile("day8/input.txt");
        problem1();
        problem3();
    }

    private static void readFromFile(String filePath) throws IOException{
        File file = new File(filePath);
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);
        int index = -1;
        for(String s : fileContent) {
            index++;
            if (index==0) {
                instructions = s;
                continue;
            }
            if (index==1) continue; 
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<s.length();i++) 
                if (s.charAt(i)>64 && s.charAt(i)<=122) 
                    sb.append(s.charAt(i)); 
            String[] temp = {sb.substring(3, 6),sb.substring(6, 9)};
            String node = sb.substring(0, 3);
            if(node.charAt(2) == 'A') {
                startingNodes.add(node);
                // System.out.println("Adding Node " + node);
            }
            map.put(node,temp);
            //System.out.println("Node: " + sb.substring(0, 3) + " L: " + temp[0] + " R:" + temp[1]);
        }
    }
    
    private static void problem1() {
        boolean notFound = true;
        int nbrSteps = 0;
        String nextNode = "AAA"; // Starting node.
        while(notFound) {
            for(int i = 0; i<instructions.length(); i++) {
                nbrSteps++;
                nextNode = instructions.charAt(i) == 'L' ? map.get(nextNode)[0] : map.get(nextNode)[1];
                if(nextNode.equals("ZZZ")) {
                    notFound = false;
                    break;
                }
            }
        }
        System.out.println("Problem 1: " + nbrSteps);
    }

    private static void problem3() {
        ArrayList<Integer> loopSize = new ArrayList<>();
        for(int i=0; i<startingNodes.size();i++){
            boolean notFound = true;
            int nbrSteps = 0;
            String nextNode = startingNodes.get(i);
            while(notFound) {
                for(int j = 0; j<instructions.length(); j++) {
                    nbrSteps++;
                    nextNode = instructions.charAt(j) == 'L' ? map.get(nextNode)[0] : map.get(nextNode)[1];
                    if(nextNode.charAt(2) == 'Z') {
                        notFound = false;
                        break;
                    }
                }
            }
            loopSize.add(nbrSteps);
        }
        int[] loops = new int[loopSize.size()];
        for(int i=0; i<loopSize.size(); i++) {
            loops[i] = loopSize.get(i);
        }
        System.out.println("Problem 2: " + lcm_of_array_elements(loops));
     }

    // Code supplied from Geeks for Geeks
    public static long lcm_of_array_elements(int[] element_array)
    {
        long lcm_of_array_elements = 1;
        int divisor = 2;
         
        while (true) {
            int counter = 0;
            boolean divisible = false;
             
            for (int i = 0; i < element_array.length; i++) {
 
                // lcm_of_array_elements (n1, n2, ... 0) = 0.
                // For negative number we convert into
                // positive and calculate lcm_of_array_elements.
 
                if (element_array[i] == 0) {
                    return 0;
                }
                else if (element_array[i] < 0) {
                    element_array[i] = element_array[i] * (-1);
                }
                if (element_array[i] == 1) {
                    counter++;
                }
 
                // Divide element_array by devisor if complete
                // division i.e. without remainder then replace
                // number with quotient; used for find next factor
                if (element_array[i] % divisor == 0) {
                    divisible = true;
                    element_array[i] = element_array[i] / divisor;
                }
            }
 
            // If divisor able to completely divide any number
            // from array multiply with lcm_of_array_elements
            // and store into lcm_of_array_elements and continue
            // to same divisor for next factor finding.
            // else increment divisor
            if (divisible) {
                lcm_of_array_elements = lcm_of_array_elements * divisor;
            }
            else {
                divisor++;
            }
 
            // Check if all element_array is 1 indicate 
            // we found all factors and terminate while loop.
            if (counter == element_array.length) {
                return lcm_of_array_elements;
            }
        }
    }

    


}
