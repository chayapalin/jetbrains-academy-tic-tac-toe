import java.util.Scanner;

public class TicTacToe {
    public static Scanner scanner = new Scanner(System.in);
    public static char[][] gameBoard;
    public static int n = 3, m = 3; // 3x3 field
    public static boolean askCoordinates = true;
    public static int counter = 1; // count X's turn or O's turn
    public static int xWin = 0, oWin = 0; // The player who completes a row, a column, or a diagonal.

    public static void main(String[] args) {
        createBoard(); // create an empty field
        printBoard(gameBoard); // print the empty field
        enterCoordinates(); // asks the user to enter the cell coordinates
    }

    public static char[][] createBoard() {
        gameBoard = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                gameBoard[i][j] = ' '; // initialise each cell with a space
            }
        }
        return gameBoard;
    }

    public static void printBoard(char[][] gameBoard) {
        System.out.println("---------");
        for (int i = 0; i < n; i++) {
            System.out.print("| ");
            for (int j = 0; j < m; j++) {
                System.out.print(gameBoard[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static void enterCoordinates() { // Analyse user input and show these messages in the following situations:

        while (askCoordinates) {
            System.out.print("Enter the coordinates: ");

            if (!scanner.hasNextInt()) { // if the user enters other symbols instead of numbers
                System.out.println("You should enter numbers!");
                askCoordinates = false;
            } else {
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                if (row < 1 || row > 3 || col < 1 || col > 3) { // if the user goes beyond the field
                    System.out.println("Coordinates should be from 1 to 3!");
                    counter--;
                } else if (gameBoard[3 - col][row - 1] != ' ') { // if the cell is not empty
                    System.out.println("This cell is occupied! Choose another one!");
                    counter--;
                } else if (counter % 2 == 0) {
                    gameBoard[3 - col][row - 1] = 'O';
                } else if (counter % 2 != 0) {
                    gameBoard[3 - col][row - 1] = 'X';
                }

                checkState();
                printBoard(gameBoard);

                if (xWin > 0) {
                    System.out.println("X wins");
                    break;
                } else if (oWin > 0) {
                    System.out.println("O wins");
                    break;
                } else if (counter == 9 && xWin == 0 && oWin == 0) {
                    System.out.println("Draw");
                    break;
                } else if (counter == 9) {
                    break;
                }

                counter++;
            }
        }
    }

    public static void checkState() {
        int line = 0;

        for (int a = 0; a <= 8; a++) {

            switch (a) {
                case 0:
                    line = gameBoard[0][0] + gameBoard[0][1] + gameBoard[0][2];
                    break;
                case 1:
                    line = gameBoard[1][0] + gameBoard[1][1] + gameBoard[1][2];
                    break;
                case 2:
                    line = gameBoard[2][0] + gameBoard[2][1] + gameBoard[2][2];
                    break;
                case 3:
                    line = gameBoard[0][0] + gameBoard[1][0] + gameBoard[2][0];
                    break;
                case 4:
                    line = gameBoard[0][1] + gameBoard[1][1] + gameBoard[2][1];
                    break;
                case 5:
                    line = gameBoard[0][2] + gameBoard[1][2] + gameBoard[2][2];
                    break;
                case 6:
                    line = gameBoard[0][0] + gameBoard[1][1] + gameBoard[2][2];
                    break;
                case 7:
                    line = gameBoard[0][2] + gameBoard[1][1] + gameBoard[2][0];
                    break;
            }

            if (line == 264) { // X + X + X = 264
                xWin++;
            } else if (line == 237) { // O + O + O = 237
                oWin++;
            }
        }
    }
}