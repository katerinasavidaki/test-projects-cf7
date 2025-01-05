package projects.project1;

/**
 * An app that reads numbers from a file and place them into an array
 * The file has to have at least 6 numbers from 1 - 49.
 * Then it produces all the possible combinations of 6 numbers
 * and store them into an array.
 * After filtering all the combinations, it writes them into a new file.
 */

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class LottoApp {

    public static void main(String[] args) {
        File inFile = new File("C:/tmp/lotto.txt");
        File outFile = new File("C:/tmp/lotto-final.txt");
        int[] lottoNumbers;

        try {
            lottoNumbers = createNumbersArray(inFile);
            Arrays.sort(lottoNumbers);

            final int SIZE = 6;
            int[] finalNumbers = new int[SIZE];         // the final array of 6 numbers
            int window = lottoNumbers.length - SIZE;
            for (int i = 0; i <= window; i++) {               // after generating a combination, we increment the start
                for (int j = i + 1; j <= window + 1; j++) {   // index and the window by one to take the next combo
                    for (int k = j + 1; k <= window + 2; k++) {
                        for (int l = k + 1; l <=window + 3; l++) {
                            for (int m = l + 1; m <= window + 4; m++) {
                                for (int n = m + 1; n <= window + 5; n++) {
                                        finalNumbers[0] = lottoNumbers[i];    //everytime the loop ends, a new combo
                                        finalNumbers[1] = lottoNumbers[j];    //of numbers is created and after passing
                                        finalNumbers[2] = lottoNumbers[k];    //all the necessary checks, it prints the
                                        finalNumbers[3] = lottoNumbers[l];    //result into a new txt file.
                                        finalNumbers[4] = lottoNumbers[m];
                                        finalNumbers[5] = lottoNumbers[n];

                                        if ((moreThanFourEvens(finalNumbers)) && moreThanFourOdds(finalNumbers)
                                                && moreThanTwoConsecutive(finalNumbers) && sameDecades(finalNumbers) &&
                                                sameEndings(finalNumbers)) {
                                            try {
                                                printArrayInFile(finalNumbers, outFile);
                                            } catch (FileNotFoundException e) {
                                                System.err.println(e.getMessage());
                                            }
                                        }
                                }
                            }
                        }
                    }
                }
            }

            printArray(lottoNumbers);
            System.out.println();
            printArray(finalNumbers);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }


    public static int[] createNumbersArray(File file) throws FileNotFoundException {
       int[] numbers = new int[49];
       int indexer = 0;        // we set an indexer at 0 to traverse the array and check if the number of the file
                              //already exists in the array and if not, to add it.

       try (Scanner in = new Scanner(file)) {
           while (in.hasNextInt() && indexer <= 48) {
               int num = in.nextInt();

               if (num >= 1 && num <= 49) {
                   if (numbers[indexer] != num) {
                       numbers[indexer] = num;
                       indexer++;                       // we increment the indexer each time to move to the next position
                   } else {
                       System.out.println("Number " + num + " already exists");
                   }
               }
           }
       } catch (FileNotFoundException e) {
           System.err.println("File was not found");
           throw e;
       }
       return Arrays.copyOfRange(numbers, 0, indexer);
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
                System.out.println("The number " + array[i] + " is at position " + (i+1) );
        }
    }

    /**
     * Method to print in file the array elements. We use FileOutputStream to append the numbers
     * at the end of the file because we don't want to lose the previous elements
     * @param array
     * @param file
     * @throws FileNotFoundException
     */
    public static void printArrayInFile(int[] array, File file) throws FileNotFoundException {

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(file, true))) {

            pw.printf("%d %d %d %d %d %d\n", array[0], array[1], array[2], array[3], array[4], array[5]);

        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    /**
     * traverses the array and each time an even number is found we increase the counter
     * @param array
     * @return true if there are up to 4 evens, false otherwise
     */
    public static boolean moreThanFourEvens(int[] array) {
        if (array == null) return false;
        int countEvens = 0;

        for (int el : array) {
            if (el % 2 == 0) {
                countEvens++;
            }
        }
        return countEvens <= 4;
    }

    /**
     * raverses the array and each time an odd number is found we increase the counter
     * @param array
     * @return false if the odds are more than 4, true otherwise
     */
    public static boolean moreThanFourOdds(int[] array) {
        if (array == null) return false;
        int countOdds = 0;

        for (int el : array) {
            if (el % 2 != 0) {
                countOdds++;
            }
        }
        return countOdds <= 4;
    }

    public static boolean moreThanTwoConsecutive(int[] array) {
        if (array == null) return false;

        for (int i = 0; i < array.length - 2; i++) {           // we take the length -2 'cause we're comparing 2 numbers ahead it's time
            if ((array[i] == array[i+1] - 1) && (array[i] == array[i+2] - 2)) {    //we compare the 1st element with the 2nd - 3rd, etc.
                return true;
            }
        }
        return false;
    }

    public static boolean sameEndings(int[] array) {
        if (array == null) return false;
        int[] endings = new int[10];

        for (int el : array) {
            endings[el % 10]++;
        }

        for (int el : endings) {
            if (el <= 3) {
                return true;
            }
        }
        return false;
    }

    public static boolean sameDecades(int[] array) {
        int decade = 0;
        int[] decades = new int[5];        // we need 5 decades for numbers 1-49

        for (int num : array) {
            decade = (num - 1) / 10;         // we subtract 1 to get the right decade
            decades[decade]++;              //we increment the sub-array which contains the decades each time a decade is found

            if (decades[decade] > 3) {
                return false;
            }
        }
        return true;
    }
}
