package day9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day9 {

    static ArrayList<int[]> history = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readFromFile("day9/input.txt");
        problem12();
    }

    private static void problem12() {
        int sumProblem1 = 0;
        int sumProblem2 = 0;
        for(int[] entry : history) {
            // For every entry
            ArrayList<int[]> differences = new ArrayList<>();
            differences.add(entry);
            boolean allZero = false;

            while(!allZero) {
                int[] dif = new int[differences.get(differences.size()-1).length-1];
                for(int i=1; i<=dif.length; i++) {
                    int first = differences.get(differences.size()-1)[i];
                    int second = differences.get(differences.size()-1)[i-1];
                    //System.out.println("First: " + first + " Second: " + second);
                    dif[i-1] = first - second; 
                }
                differences.add(dif);
                for(int i:dif) {
                    if(i!=0) {
                        allZero = false;
                        break;
                    } else {
                        allZero = true;
                    }
                }
            }

            // for(int[] i : differences) {
            //     System.out.println();
            //     for(int n : i) System.out.print(" " + n);
            // }

            int newNumber1 = 0;

            for(int i=differences.size()-1; i>0; i--) {
                newNumber1 = differences.get(i-1)[differences.get(i-1).length-1] + newNumber1; 
                //System.out.println("NewNumber:  "+ newNumber);
            }
            sumProblem1+=newNumber1;
            
            int newNumber2 = 0;

            for(int i=differences.size()-1; i>0; i--) {
                newNumber2 = differences.get(i-1)[0] - newNumber2; 
                //System.out.println("NewNumber:  "+ newNumber);
            }

            sumProblem2 += newNumber2;
        } 
        System.out.println("Problem 1: " + sumProblem1);
        System.out.println("Problem 2: " + sumProblem2);
    }

    private static void readFromFile(String filePath) throws IOException{
        File file = new File(filePath);
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);
        for(String s : fileContent) {
            String[] t = s.split(" ");
            int[] hist = new int[t.length];
            for(int i=0; i<hist.length; i++) hist[i] = Integer.parseInt(t[i]);
            history.add(hist);
        }
    }

}
