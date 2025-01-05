package projects.project4;

import java.util.Scanner;

/**
 * Tic-Tac-Toe Game
 * We have player X and player O playing in a blank board at start.
 * The board is represented by an array with dimensions -> [3][3].
 * The game ends when a player wins by filling with his/her symbol (X or O),
 * 3 vertical or 3 diagonal or 3 horizontal spaces im the board.
 * The game also ends if the board is full, whether we have a winner or not.
 */
public class TicTacToe {
    static char[][] board = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    static char player = 'X';

    public static void main(String[] args) {
        System.out.println("Welcome to tic-tac-toe game!");
        while (true) {                                       //program runs until the board is full or finds a winner
            printBoard();
            System.out.println("Player " + player + " ,Choose row (1-3) and column (1-3)");
            int row = getRow();           //call methods to get the row and column number from user
            int col = getColumn();         // and insert them to the corresponding variables.
                                        // Also the methods check if the numbers meet the criteria so we can move on.

            if (isValid(row, col)) {           //checks if the desired position is empty or valid number
                makeMove(row, col);                //if the position is empty, the player makes a move

                if (isGameWon(row, col)) {    //checks if the player who made a move, won the gane
                    printBoard();              //if won, it prints the winning board and the loop breaks after printing
                    System.out.println("Congrats! Player " + player + " has won the game!");
                    break;
                }
                if (isBoardFull(row, col)) {       // if we don't have a winner, we check if the board is full
                    printBoard();                   //it prints the board and then stops the while loop and exiting.
                    System.out.println("It's a tie! Board is full!");
                    break;
                }
                switchPlayers();        //if the board is not full, and we don't have a winner, the next player plays.
            } else
                System.out.println("Error. This field is already full. Please try again!");
        }

        System.out.println("Thank you for playing! Goodbye!");
    }

    /**
     * Method that prints the board
     * to the console
     */
    public static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2) {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("--+---+--");
            }
        }
    }

    /**
     * Get the user's row choice
     * @return the user's choice.
     */
    public static int getRow() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please choose a row between 1-3");
        while (!in.hasNextInt()) {           //checks if the next input is an integer
            System.out.println("Row must be an integer");
            in.nextLine();               //consumes the previous string or char or white-space
        }
        int row = in.nextInt();
        while ((row < 1) || (row > 3)) {        // ask for 1-3 'cause it's more user-friendly
            System.out.println("Error. Row must be between 1-3");
            row = getRow();           //re-calls the method to get the correct row number
        }

        return row - 1;       // return row -1 to get the result which fits our table's index bounds. [0-2]
    }

    public static int getColumn() {
        Scanner in = new Scanner(System.in);
        System.out.println("Please choose a column between 1-3");
        while (!in.hasNextInt()) {               // checks if the number is an integer
            System.out.println("Error. Column must be an integer.");
            in.nextLine();                      //consumes the white-space or the previous string or char written
        }
        int col = in.nextInt();
        while ((col < 1) || (col > 3)) {           // we ask for 1-3 'cause it's more user-friendly
            System.out.println("Error. Column must be between 1-3");
            col = getColumn();          //recursive call of getColumn method to ask for the col number again.
        }
        return col - 1;           // return col number -1 so that is fits in our table's index bounds. [0-2]
    }

    public static void makeMove(int row, int col) {
        board[row][col] = player;
    }

    /**
     * Checks if the player's choice is valid or if the board at that position is full or empty.
     * @param row  the row to be checked
     * @param col  the col to be checked
     * @return true if it is valid , false otherwise
     */
    public static boolean isValid(int row, int col) {
         if ((row >= 0) && (row < 3) && (col >= 0) && (col < 3) && (board[row][col] == ' ')) {
             return true;
         } else
             return false;
    }

    public static boolean isGameWon(int row, int col) {
        //checks in every row for a winner by searching if every row contains the same symbol
       for (int i = 0; i < 3; i++) {
           if ((board[i][0] == player) && (board[i][1] == player) && (board[i][2] == player)) {
               return true;
           }
       }

           //checks every column if it contains the same symbol
           for (int i = 0; i < 3; i++) {
               if ((board[0][i] == player) && (board[1][i] == player) && (board[2][i] == player)) {
                   return true;
               }
           }

           //diagonal search for a winning combination
           for (int i = 0; i < 3; i++) {
               if ((board[0][0] == player) && (board[1][1] == player) && (board[2][2] == player)) {
                   return true;
               }
           }
        return false;
    }

    /**
     * A method that checks who played and switches players in every turn
     */
    public static void switchPlayers() {
        player = (player == 'X') ? 'O' : 'X';
    }

    public static boolean isBoardFull(int row, int col) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
