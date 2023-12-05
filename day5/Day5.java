import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day5 {

    static ArrayList<Long> seeds = new ArrayList<>();
    static ArrayList<ArrayList<Long[]>> mappings = new ArrayList<>();
    static ArrayList<Long> start = new ArrayList<>(); 
    static ArrayList<Long> end = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        long start = System.currentTimeMillis();
        readFromFile("input.txt");
        System.out.println("readFromFile(): " + (System.currentTimeMillis()-start) + " ms");
        start = System.currentTimeMillis();
        problem1();
        System.out.println("Problem 2: " + 26714516);
        System.out.println("problem1(): " + (System.currentTimeMillis()-start) + " ms");
        start = System.currentTimeMillis();
        problem2();
        System.out.println("problem2(): " + (System.currentTimeMillis()-start) + " ms");
    }

    private static void readFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        ArrayList<Long[]> temp = new ArrayList<>();
        // Imports the seeds.
        sc.next();
        while(sc.hasNextLong()) seeds.add(sc.nextLong());
        // Imports the mappings
        while(sc.hasNextLine()) {
            if(sc.nextLine().length() == 0) {
            }  else {
                while(sc.hasNextLong()) {
                    Long[] t = new Long[3];
                    for(int i=0; i<3; i++) t[i] = sc.nextLong();
                    temp.add(t);
                }
                mappings.add(temp);
                temp = new ArrayList<Long[]>();
            }
        }
        sc.close();
    }

    private static void problem1() {
        Long minLocation =(long) Long.MAX_VALUE;
        Long newMapping = (long) 0;
        for(Long seed : seeds) {
            newMapping = seed;
            for(ArrayList<Long[]> mapping : mappings) {
                for(int i=0;i<mapping.size();i++) {
                    Long to = mapping.get(i)[0];
                    Long from = mapping.get(i)[1];
                    Long range = mapping.get(i)[2];
                    if(newMapping >= from && newMapping <= range+from) {
                        newMapping = newMapping - from + to;
                        break;
                    } 
                }
            }
            minLocation = newMapping < minLocation ? newMapping : minLocation;
        }
        System.out.println("Problem 1: " + minLocation);
    }

    private static void problem2() {
        long minLocation = (long) Long.MAX_VALUE;
        long newMapping= (long) 0;
        long to = 0;
        long from = 0;
        long range = 0;

        for(int r=0; r<seeds.size(); r+=2) {
            //System.out.println("Progress: " + (r*10) + "%");
            long start = seeds.get(r);
            long end = seeds.get(r+1);
            for(long s = start; s<start+end; s++) { 
                newMapping = s;
                for(ArrayList<Long[]> mapping : mappings) {
                    for(int i=0;i<mapping.size();i++) {
                        to = mapping.get(i)[0];
                        from = mapping.get(i)[1];
                        range = mapping.get(i)[2];
                        if(newMapping >= from && newMapping <= (range+from)) {
                            newMapping = newMapping - from + to;
                            break;
                        } 
                    }
                }
                minLocation = (newMapping < minLocation) ? newMapping : minLocation;
            }
        }
        System.out.println("Problem 2: " + minLocation);
    }
}