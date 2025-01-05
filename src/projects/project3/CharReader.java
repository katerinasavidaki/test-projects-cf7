package projects.project3;


/**
 * An app that reads chars from a file and place then into a 2D-array of [128][2]
 * in the 1st column [row][0] of every row[], we store the char which we have read (if it's not already exists)
 * and in the 2nd column [row][1] of every row[], we store how many times each character has been found
 * The program ignores the whitespaces.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CharReader {


    public static void main(String[] args) {
        int[][] charsCount = new int[128][2];
        File file = new File("C:/tmp/project3.txt");
        int rowIndexer = 0;    //our array is empty so we start from position 0 to store the 1st value at the 1st (row)
        try (Scanner in = new Scanner(file)) {
            while (in.hasNextLine()) {        //we keep reading as long as there are lines to read
                String line = in.nextLine();
                char[] lineCharacters = line.toCharArray();    //converts the string line into a char array (splits every line char by char)

                for (char character : lineCharacters) {        //for every char of the char array , we check if it's a whitespace
                    if (Character.isWhitespace(character)) {   //and if it is, we ignore it.
                        continue;
                    }
                    int i;
                    for (i = 0; i < rowIndexer; i++) {                    //checks until reaches the first empty space
                        if (charsCount[i][0] == (int) character) {       // if the char already exists in our 2d array (1st position of the i number of rows)
                            charsCount[i][1]++;                         //we stop searching and we increment the 2nd column of our array which states the counter
                            break;                                     // of every char
                        }
                    }
                    if (i == rowIndexer) {
                        charsCount[rowIndexer][0] = character;        // we store the char in the 1st available position
                        charsCount[rowIndexer][1] = 1;               //we set the counter value to 1 for the first time of it's appearance
                        rowIndexer++;                               //we increment the rowIndexer value by one to move on to the next empty position
                    }
                }

            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }

        Arrays.sort(charsCount, Comparator.comparingInt(row -> row[0])); //sorts the array by character
        printArray(charsCount);

        Arrays.sort(charsCount, Comparator.comparingInt(row -> -row[1]));   //sorting by number of appearance in desceding order
        printArray(charsCount);


    }

    //method that prints the 2D array
    public static void printArray(int[][] array) {
        for (int[] row : array) {
            if (row[0] == 0) {      //if the value is 0 it means that there are no chars stored, so we escape them.
                continue;
            }
            System.out.println("Character " + (char) row[0] + " appears " + row[1] + (row[1] == 1 ? " time" : " times") );
        }
        System.out.println();
    }
}