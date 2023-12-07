package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day7 {

    static ArrayList<Hand> hands = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readFromFile("day7/input.txt");
        problem1();
        problem2();
    }
    
    private static void readFromFile(String filePath) throws IOException{
        File file = new File(filePath);
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);
        for(String s : fileContent) {
            hands.add(new Hand(s.split(" ")[0],s.split(" ")[1]));
        }
        hands.sort((a,b) -> a.compareTo(b));
    }
    
    private static void problem1() {
        int sum = 0;
        hands.sort((h1,h2) -> h1.compareTo(h2));
        for(int i=1; i<=hands.size(); i++) sum += hands.get(i-1).bet*i;
        System.out.println("Problem 1: " + sum);
    }

    private static void problem2() {

    }
    
}

class Hand implements Comparable<Hand> {

    public int[] hand;
    public int[] cardsByValue = new int[15];
    ArrayList<Integer> thisCard = new ArrayList<>();
    public int bet;

    public Hand(String s, String bet) {
        this.hand = new int[5];
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(!Character.isLetter(c)) {
                hand[i] = Character.getNumericValue(c);
            } else {
                    Character[] t = {'T','J','Q','K','A'};
                    ArrayList<Character> temp = new ArrayList<Character>();
                    for(Character ch : t) temp.add(ch);
                    hand[i] = temp.indexOf(c) + 10;
            }
        }
        for(int i : hand) cardsByValue[i]++;
        for(int i : this.cardsByValue) if(i>0) thisCard.add(i);
        this.bet = Integer.parseInt(bet);
    }

    @Override public int compareTo(Hand other) {

        if(this.getRank() != other.getRank()) {
            return this.getRank() - other.getRank();
        } else {
            for(int i=0; i<hand.length; i++) {
                if(this.hand[i] != other.hand[i]) return this.hand[i] - other.hand[i];
            }
            return 0;
        }
    }
    
    private int getRank() {
        int i = thisCard.size();
        switch (i) {
            case 1:
                return 7;
            case 2:
                if (thisCard.contains(4)) {
                    return 6;
                } else {
                    return 5;
                }
            case 3:
                if (thisCard.contains(3)) {
                    return 4;
                } else {
                    return 3;
                }
            case 4:
                return 2;
            case 5:
                return 1;
            default: 
                return 0;
        }
    }





}
