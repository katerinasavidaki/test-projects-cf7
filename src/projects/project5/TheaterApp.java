package projects.project5;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An app that manages bookings and cancellations of a theater which contains 30 rows and 12 columns.
 * The name of each seat consists of columns (letter from A - L) and rows (1-30)
 */
public class TheaterApp {

    static Scanner in = new Scanner(System.in);
    static boolean[][] seats = new boolean[30][12];

    public static void main(String[] args) {

        while (true) {

            try {
                printOptions();
                int choice = getChoice();

                switch (choice) {
                    case 1:
                        book(getColumn(), getRow());
                        break;
                    case 2:
                        cancel(getColumn(), getRow());
                    case 3:
                        System.out.println("Thank you for using Theater App.");
                        return;
                    default:
                        System.out.println("Choose between 1-3");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Row must be between 1 - 30");
            }
        }
    }

    public static void printOptions() {
        System.out.println("Choose an option: ");
        System.out.println("1. Book a seat");
        System.out.println("2. Cancel booking");
        System.out.println("3. Exit");
    }

    /**
     * Get the user's choice and checks if it's an integer
     * @return  an integer
     */
    public static int getChoice() {
       while (!in.hasNextInt()) {
           System.out.println("Choice must be an integer");
           in.nextLine();         //consumes the previous input
       }
       return in.nextInt();
    }


    /**
     * Ask the row number from the user
     * @return row number
     */
    public static int getRow() throws IndexOutOfBoundsException, InputMismatchException {
        System.out.println("Enter row number");
        int row = in.nextInt();
        try {
           if (row > 30 || row < 1) {
               System.err.println("Row must be between 1 - 30");
           }
           try {
               if (!in.hasNextInt()) {
                   System.out.println("Row must be a number");
               }
           } catch (InputMismatchException e) {
               System.out.println(e.getMessage());
               throw e;
           }
           return row;
        } catch (IndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
            throw e;
        }
    }

    /**
     * Method that reads the user's input and after necessary checks it
     * @return the 1st character of the string line (column which represents the seat)
     */
    public static char getColumn() throws InputMismatchException, IndexOutOfBoundsException {
        String line = in.next();

        try {
            if (!Character.isLetter(line.charAt(0)) || line.length() != 1) {
                throw new InputMismatchException("Column must be exactly one letter");
            }
            char column = line.charAt(0);
            try {
                if (column < 'A' || column > 'L') {
                    throw new IndexOutOfBoundsException("Column must be between A - L");
                }
                return column;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                throw e;
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }




    /**
     * Check if a seat is booked. If it's empty, it books the seat.
     * Otherwise, it prints a message to the user.
     * @param column    letter of the seat
     * @param row       row number of the seat
     */
    public static void book(char column, int row) {
        int columnNumber = column - 'A';
        int rowNumber = row - 1;        //array's first element start from 0


        //check if the position of the seat is empty (false) or taken (true)
        //if it is empty, we switch it to taken (from false to true)
        if (!(seats[rowNumber][columnNumber])) {
            seats[rowNumber][columnNumber] = true;
            System.out.println("Booking of seat " + row + column + " was successful.");
        } else {
            System.out.println("The seat " + row + column + " is already booked.");
        }
    }

    public static void cancel(char column, int row) {
        int columnNumber = column - 'A';
        int rowNumber = row - 1;        //array's first element start from 0

        if (seats[rowNumber][columnNumber]) {
            seats[rowNumber][columnNumber] = false;
            System.out.println("Booking of seat " + row + column + " was canceled.");
        } else {
            System.out.println("Seat " + row + column + " is empty");
        }
    }

}
