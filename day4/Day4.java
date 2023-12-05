//Standard imports for reading files
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import static java.nio.charset.StandardCharsets.UTF_8;
//

public class Day4 {

    static ArrayList<HashSet<Integer>> tickets = new ArrayList<>();
    static ArrayList<HashSet<Integer>> winningTickets = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        readFromfile("input.txt");
        problem1();
        problem2();
    }
    
    private static void readFromfile(String filePath) throws IOException  {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        int index = 0;
        while(sc.hasNextLine()) {
            sc.next(); sc.next();
            int nbr;
            tickets.add(new HashSet<Integer>());
            while(sc.hasNextInt()) {
                nbr = Integer.parseInt(sc.next());
                tickets.get(index).add(nbr);
            }
            sc.next();
            
            winningTickets.add(new HashSet<Integer>());
            while(sc.hasNextInt()) {
                nbr = Integer.parseInt(sc.next());
                winningTickets.get(index).add(nbr);
            }
            index++;
        }
    }

    private static void problem1() {
        int sum = 0;
        for(int i = 0; i<tickets.size(); i++) {
            tickets.get(i).retainAll(winningTickets.get(i));
            sum += tickets.get(i).size() < 1 ? 0 : (int) Math.pow(2, tickets.get(i).size()-1);
        }
        System.out.println("Sum of score: " + sum);
    }

    private static void problem2() {
        int[] copies = new int[tickets.size()];
        Arrays.fill(copies,1);
        for(int i = 0; i<tickets.size(); i++) {
            tickets.get(i).retainAll(winningTickets.get(i));
                for(int j = i+1; j<=i+tickets.get(i).size(); j++) {
                    if(j>=copies.length) {
                        copies[copies.length-1] += 1 * copies[i];
                    } else {
                        copies[j] += 1 * copies[i];
                    }
                } 
        }
        System.out.println("Sum of copies: " + IntStream.of(copies).sum());
    }
}
