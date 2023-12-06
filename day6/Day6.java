package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import static java.nio.charset.StandardCharsets.UTF_8;

public class Day6 {

        static ArrayList<Integer> time = new ArrayList<>();
        static ArrayList<Integer> record = new ArrayList<>();

        static long start;
        static long end;

        public static void main(String[] args) throws IOException {
            readFromFile("day6/input.txt");
            problem1();
        }


        private static void readFromFile(String filePath) throws IOException {
            // TOTO:
            // Refactor to read in strings of numbers. data structures are created in problem1() and problem2()
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            // Imports the seeds.
            sc.next();
            while(sc.hasNextLong()) time.add(sc.nextInt());
            sc.next();
            while(sc.hasNextInt()) record.add(sc.nextInt());
            sc.close();
            StringBuilder sb = new StringBuilder();
            sc = new Scanner(file);
            sc.next();
            while(sc.hasNextLong()) sb.append(sc.nextInt());
            start = Long.parseLong(sb.toString().strip());
            sb = new StringBuilder();
            sc.next();
            while(sc.hasNextLong()) sb.append(sc.nextInt());
            end = Long.parseLong(sb.toString().strip());
        }

        private static void problem1() {
            int product = 1; 
            int waysToWin = 0;
            for(int i=0; i<time.size(); i++) {
                for(int j=0; j<time.get(i); j++){
                    int loading = j;
                    int dis = (time.get(i)-j) * (loading);
                    if(dis>record.get(i)) waysToWin++;
                }
                System.out.println("ways to win " + i + " " + waysToWin);
                product *= waysToWin;
                waysToWin=0;
            }
            System.out.println("Problem 2: "+product);
        }
        // For problem 2       
        private static boolean isRecord(long start) {
            return start * (end-start)>end;
        }
}

    


    

    

