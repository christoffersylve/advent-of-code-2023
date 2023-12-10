package day10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Day10 {


    static char[][] input;
    static int[] startPos = new int[2];
    static int[][] visitMatrix;
    static int[][] loopMatrix;
    static HashMap<Character,boolean[]> turns = new HashMap<>();
    

    public static void main(String[] args) throws IOException {
        readFromFile("day10/input.txt");
        problem1();
    }

    private static void readFromFile(String filePath) throws IOException{
        File file = new File(filePath);
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);
        input = new char[fileContent.size()][fileContent.get(0).length()];
        visitMatrix = new int[fileContent.size()][fileContent.get(0).length()];
        loopMatrix = new int[fileContent.size()][fileContent.get(0).length()];
        for(int i=0; i<fileContent.size(); i++) {
            for(int j=0; j<fileContent.get(i).length(); j++) {
                if(fileContent.get(i).charAt(j) == 'S') {
                   int[] sp = {i,j}; 
                   startPos = sp;
                } 
                input[i][j] = fileContent.get(i).charAt(j);
            }
        }
        // Laod all mappings
        // Up, Down, Left, Right
        boolean[] currentPart1 = {true,true,false,false};
        turns.put('|',currentPart1);

        boolean[] currentPart2 = {false,false,true,true};
        turns.put('-',currentPart2);

        boolean[] currentPart3 = {true,false,false,true};
        turns.put('L',currentPart3);

        boolean[] currentPart4 = {true,false,true,false};
        turns.put('J',currentPart4);

        boolean[] currentPart5 = {false,true,true,false};
        turns.put('7',currentPart5);

        boolean[] currentPart6 = {false,true,false,true};
        turns.put('F',currentPart6);

        boolean[] currentPart7 = {false,false,false,false};
        turns.put('.',currentPart7);
        boolean[] currentPart8 = {true,true,true,true};
        turns.put('S',currentPart8);

    }
    
    private static void problem1() {

        System.out.println();

        ArrayList<Integer> meetingPoints = new ArrayList<>();

        ArrayList<int[]> newPositions = new ArrayList<>();
        ArrayList<int[]> newPos = new ArrayList<>();
        int[] sp = {startPos[0],startPos[1],-1,-1};
        newPos.add(sp);
        int nbrSteps = 0;

        while(newPos.size() != 0) {
            nbrSteps++;
            newPositions = newPos;
            newPos = new ArrayList<>();
            for(int i=0;i<newPositions.size();i++) {

                int[] currentPos = newPositions.get(i);
                int x = currentPos[0];
                int y = currentPos[1];
                int oldX = currentPos[2];
                int oldY = currentPos[3];
                boolean[] connectionFrom = turns.get(input[y][x]);

                // Check all four poistions for next possible move.
                // Up
                if(y>0) {
                    int newX = x;
                    int newY = y-1;
                    boolean[] connection = turns.get(input[newY][newX]);
                    if(connection[1] && connectionFrom[0] && (newX!=oldX || newY!=oldY)) {
                        loopMatrix[newY][newX] += 1;
                        if(visitMatrix[newY][newX]==0) {
                            int[] next = {newX,newY,x,y};
                            newPos.add(next);
                            visitMatrix[newY][newX] = nbrSteps;
                        } 
                    }
                }

                // Down
                if(y<input.length-1) {
                    int newX = x;
                    int newY = y+1;
                    boolean[] connection = turns.get(input[newY][newX]);
                    if(connection[0] && connectionFrom[1] && (newX!=oldX || newY != oldY)) {
                        loopMatrix[newY][newX] += 1;
                        if(visitMatrix[newY][newX]==0) {
                            int[] next = {newX,newY,x,y};
                            newPos.add(next);
                            visitMatrix[newY][newX] = nbrSteps;
                        } 
                    }
                }

                // Left
                if(x>0) {
                    int newX = x-1;
                    int newY = y;
                    boolean[] connection = turns.get(input[newY][newX]);
                    if(connection[3] && connectionFrom[2] && (newX!=oldX || newY != oldY)) {
                        loopMatrix[newY][newX] += 1;
                        if(visitMatrix[newY][newX]==0) {
                            int[] next = {newX,newY,x,y};
                            newPos.add(next);
                            visitMatrix[newY][newX] = nbrSteps;
                        } 
                    }
                }

                // Right
                if(x<input[0].length-1) {
                    int newX = x+1;
                    int newY = y;
                    boolean[] connection = turns.get(input[newY][newX]);
                    if(connection[2] && connectionFrom[3] && (newX!=oldX || newY!=oldY)) {
                        loopMatrix[newY][newX] += 1;
                        if(visitMatrix[newY][newX]==0) {
                            int[] next = {newX,newY,x,y};
                            newPos.add(next);
                            visitMatrix[newY][newX] = nbrSteps;
                        }  
                    }
                }

                // Last part of largest loop
                //if(newPos.size() == 0 && newPositions.size()-1 == i) loopMatrix[y][x] += 1;

            }

            // System.out.print("Adding from iteration "+nbrSteps+": ");
            // for(int[] p: newPos) {
            //     System.out.print(" "+input[p[0]][p[1]]);
            // }
            // System.out.println();

        }

        int maxNbrStepsInLoop = 0;

        // System.out.println();
        // System.out.println("Loop Matrix:");
        // for(int[] y:loopMatrix) {
        //     System.out.println();
        //     for(int x:y) {
        //         System.out.print(x+" ");
        //     }
        // }
        // System.out.println();

        // System.out.println();
        // System.out.println("Visit Matrix:");
        // for(int[] y:visitMatrix) {
        //     System.out.println();
        //     for(int x:y) {
        //         System.out.print(x+" ");
        //     }
        // }
        // System.out.println();


        // System.out.println();
        
        for(int y=0; y<loopMatrix.length;y++) {
            for(int x=0;x<loopMatrix[y].length;x++) {
                if(loopMatrix[y][x] > 1) {
                    if(maxNbrStepsInLoop<visitMatrix[y][x]) maxNbrStepsInLoop = visitMatrix[y][x];
                }
            }
        }

        System.out.println("Problem 1: " + maxNbrStepsInLoop);
    }


}
