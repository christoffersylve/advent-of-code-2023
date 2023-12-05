import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Day5 {

    static ArrayList<Long> seeds = new ArrayList<>();
    static ArrayList<ArrayList<Long[]>> mappings = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        readFromFile("day5/input.txt");
        problem1();
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


}

    
