package day13;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;


public class Day13 {

    static ArrayList<ArrayList<String>> fields = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        readFromFile("day13/input.txt");
        problem1();
        problem2();
    }

    private static void readFromFile(String filePath) throws IOException{
        File file = new File(filePath);
        List<String> fileContent = Files.readAllLines(file.toPath(), UTF_8);
        ArrayList<String> list = new ArrayList<>();
        for(String s: fileContent) {
            if(s.length()==0) {
                fields.add(list);
                list = new ArrayList<>();
            } else {
                list.add(s);
            }
        }
        fields.add(list); // Add last list
        // for(ArrayList<String> a : matrix) {
        //     System.out.println("dhjoashdasio");
        //     for(String s : a) {
        //         System.out.println();
        //         System.out.print(s + "");
        //     }
        // }
     }
    
    private static void problem1() {


        int sum = 0;

        System.out.println(fields.size());

        // Need to check vertical and horizontal line.
        for(ArrayList<String> field : fields) {

            System.out.println("NEW FIELD");

            // Start by checking horizontal line, two at a time.
            // Seem like it is working... 

            boolean isSame = false;
            StringBuilder line1 = new StringBuilder();
            StringBuilder line2 = new StringBuilder();

            for(int i = 1; i<field.size(); i++) {

                //System.out.println("index i: "+i);

                line1 = new StringBuilder();
                line2 = new StringBuilder();

                line1.append(field.get(i-1).strip());
                line2.append(field.get(i).strip());

                //System.out.println("Comparing line: " + line1 + " " + line2);

                // Mirror found
                if(line1.toString().equals(line2.toString())) {
                    // compare each String going out from both sides
                    for(int a = 0; (i-1)-a >= 0 && a+i<field.size(); a++) {
                        if(!field.get((i-1)-a).toString().equals(field.get(i+a).toString())) {
                            isSame = false;
                            break;
                        } else {
                            isSame = true;
                        }
                    }
                } 

                if(isSame) {
                    System.out.println("YAY Horizontal");
                    sum += i * 100;
                    break;
                } 

            }

            if(isSame) continue;

            // Checking veritcal line
            for(int i = 1; i<field.get(0).length(); i++) {

                line1 = new StringBuilder();
                line2 = new StringBuilder();

                for(int j = 0; j<field.size(); j++) {
                    line1.append(field.get(j).charAt(i-1));
                    line2.append(field.get(j).charAt(i));
                }

                if(line1.toString().equals(line2.toString())) {
                    // compare each String going out from both sides
                    for(int a = 0; (i-1)-a >= 0 && a+i<field.get(0).length(); a++) {

                        line1 = new StringBuilder();
                        line2 = new StringBuilder();

                        for(int j = 0; j<field.size(); j++) {
                            line1.append(field.get(j).charAt(i-1-a));
                            line2.append(field.get(j).charAt(i+a));
                        }

                        if(!line1.toString().equals(line2.toString())) {
                            isSame = false;
                            break;
                        } else {
                            isSame = true;
                        }

                    }
                } 

                if(isSame) {
                    System.out.println("YAY Vertical");
                    sum += i;
                    break;
                }
                
            }

        }
        System.out.println("Problem 1: "+ sum);
    }

    private static void problem2() {


        int sum = 0;

        System.out.println(fields.size());

        // Need to check vertical and horizontal line.
        for(ArrayList<String> field : fields) {

            System.out.println("NEW FIELD");

            // Start by checking horizontal line, two at a time.
            // Seem like it is working... 

            boolean isSame = false;
            StringBuilder line1 = new StringBuilder();
            StringBuilder line2 = new StringBuilder();

            for(int i = 1; i<field.size(); i++) {

                //System.out.println("index i: "+i);

                line1 = new StringBuilder();
                line2 = new StringBuilder();

                line1.append(field.get(i-1).strip());
                line2.append(field.get(i).strip());

                //System.out.println("Comparing line: " + line1 + " " + line2);

                // Mirror found
                if(line1.toString().equals(line2.toString())) {
                    // compare each String going out from both sides
                    for(int a = 0; (i-1)-a >= 0 && a+i<field.size(); a++) {
                        if(!field.get((i-1)-a).toString().equals(field.get(i+a).toString())) {
                            isSame = false;
                            break;
                        } else {
                            isSame = true;
                        }
                    }
                } 

                if(isSame) {
                    System.out.println("YAY Horizontal");
                    sum += i * 100;
                    break;
                } 

            }

            if(isSame) continue;

            // Checking veritcal line
            for(int i = 1; i<field.get(0).length(); i++) {

                line1 = new StringBuilder();
                line2 = new StringBuilder();

                for(int j = 0; j<field.size(); j++) {
                    line1.append(field.get(j).charAt(i-1));
                    line2.append(field.get(j).charAt(i));
                }

                if(line1.toString().equals(line2.toString())) {
                    // compare each String going out from both sides
                    for(int a = 0; (i-1)-a >= 0 && a+i<field.get(0).length(); a++) {

                        line1 = new StringBuilder();
                        line2 = new StringBuilder();

                        for(int j = 0; j<field.size(); j++) {
                            line1.append(field.get(j).charAt(i-1-a));
                            line2.append(field.get(j).charAt(i+a));
                        }

                        if(!line1.toString().equals(line2.toString())) {
                            isSame = false;
                            break;
                        } else {
                            isSame = true;
                        }

                    }
                } 

                if(isSame) {
                    System.out.println("YAY Vertical");
                    sum += i;
                    break;
                }
                
            }

        }
        System.out.println("Problem 1: "+ sum);
    }

}
