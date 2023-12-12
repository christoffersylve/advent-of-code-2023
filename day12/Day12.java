package day12;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;


public class Day12 {

    static ArrayList<String[]> springs = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> record = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readFromFile("day12/input.txt");
        int[] a = {1,1,3};
        //valid(".#...#.....##", a);
        problem1();
    }

    private static void readFromFile(String filePath) throws IOException{

        File file = new File(filePath);
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);

        for(String s: fileContent) {
            String[] input = s.split(" ");
            springs.add(input[0].strip().split(""));
            ArrayList<Integer> rec = new ArrayList<>();
            for(String n : input[1].split(",")) {
                rec.add(Integer.parseInt(n));
            }
            record.add(rec);
        }

        // for(String[] s : springs) {
        //     System.out.println();
        //     for(String l : s) {
        //         System.out.print(l);
        //     }
        // }

        // for(ArrayList<Integer> n : record) {
        //     System.out.println();
        //     for(Integer l : n) {
        //         System.out.print(l);
        //     }
        // }

        System.out.println();

    }
    
    private static void problem1() {
        // Brute force
        // 1 ´= #
        // 0 = .
        int sum = 0;
        // springs.size()
        for(int i = 0; i<springs.size(); i++) {
            int nbrQuestionMarks = 0;
            for(String s : springs.get(i)) if(s.equals("?")) nbrQuestionMarks++;
            String[] combinations = new String[(int) Math.pow(2, nbrQuestionMarks)];
            //System.out.println();
            //System.out.println(combinations.length);

            for(int n = 0; n<combinations.length; n++) {
                combinations[n]= Integer.toBinaryString(n);
                while(combinations[n].length() < nbrQuestionMarks) combinations[n] = "0" + combinations[n];
                //System.out.println(combinations[n]);
            }

            for(String comb : combinations) {
                // Check one combination!
                StringBuilder sb = new StringBuilder();
                int index = 0;
                for(String s : springs.get(i)) {
                    if(!s.equals("?")) {
                        sb.append(s);
                    } else {
                        if(comb.charAt(index) == '1') {
                            sb.append("#");
                        } else {
                            sb.append(".");
                        }
                        index++;
                    }
                }
                // Check if valid arrangement
                int[] l = new int[record.get(i).size()];
                for(int n=0; n<l.length; n++) l[n] = record.get(i).get(n);
                if(valid(sb.toString(), l)) {
                    //System.out.println("Valid: " + sb);
                    sum++;
                }
            }
            // System.out.println("Iteration:  " + i + " Sum: " + sum);
            // System.out.println();
        }

        System.out.println("Problem 1: "+sum);
    }

    private static boolean valid(String s, int[] n) {

        //System.out.println("Testing String: " + s);
        
        String[] st = s.split("\\.");

        ArrayList<Integer> temp = new ArrayList<>();

        for(String sl: st) {
            if(!sl.equals("")) {
                temp.add(sl.length());
            }
        }

        if(temp.size() != n.length) {
            return false;
        } else {
            for(int i=0; i<temp.size(); i++) {
                if(n[i] != temp.get(i)) return false;
            }
        }

        return true;
    }

    private static void problem2() {

    }

    public static BigInteger ncr(int n, int r) {
        BigInteger result = BigInteger.ONE;
        for (int i = 1; i <= r; i++) result = result.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
        return result;
    }

}
