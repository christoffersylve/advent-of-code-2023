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
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        // Imports the seeds.
        sc.next();
        while(sc.hasNextLong()) time.add(sc.nextInt());
        sc.next();
        while(sc.hasNextInt()) record.add(sc.nextInt());
        sc.close();
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
}

    


    

    

