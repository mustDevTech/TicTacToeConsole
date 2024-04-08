import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Class to represent the Tic Tac Toe game
 */
public class TicTacToe {
    //region Constants for player symbols
    private static final char PLAYER_X = 'X';
    private static final char PLAYER_O = 'O';
    private static final char EMPTY_CELL = '-';
    //endregion
    private static final int boardSize = 3;
    private final char[][] board;
    private char currentPlayer;


    /**
     * Constructor method to initialize the board and current player
     */
    public TicTacToe() {
        this.board = new char[boardSize][boardSize];
        this.currentPlayer = PLAYER_X;
        initializeBoard();
    }


    /**
     * Method to initialize the board with empty cells
     */
    private void initializeBoard() {
        for (char[] chars : board) {
            Arrays.fill(chars, EMPTY_CELL);
        }
    }

    /**
     * Method to 'Visually' print the Tic Tac Toe board
     */
    private void printBoard() {
        System.out.print("   ");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < board.length; i++) {
            System.out.print(i + "| ");
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Method to check if there is a winner by checking the rows, columns, and diagonals
     * @return true if there is a winner, false otherwise
     */
    private boolean checkWin() {
        int boardSize = board.length;

        // Check rows and columns simultaneously
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j <= boardSize - 3; j++) {
                boolean rowWin = true;
                boolean colWin = true;
                for (int k = 1; k < 3; k++) {
                    if (board[i][j] == EMPTY_CELL || board[i][j] != board[i][j + k]) {
                        rowWin = false;
                    }
                    if (board[j][i] == EMPTY_CELL || board[j][i] != board[j + k][i]) {
                        colWin = false;
                    }
                }
                if (rowWin || colWin) {
                    return true;
                }
            }
        }

        // Check diagonals
        for (int i = 0; i <= boardSize - 3; i++) {
            for (int j = 0; j <= boardSize - 3; j++) {
                boolean mainDiagonalWin = true;
                boolean otherDiagonalWin = true;
                for (int k = 1; k < 3; k++) {
                    if (board[i][j] == EMPTY_CELL || board[i][j] != board[i + k][j + k]) {
                        mainDiagonalWin = false;
                    }
                    if (board[i][j + 2] == EMPTY_CELL || board[i][j + 2] != board[i + k][j + 2 - k]) {
                        otherDiagonalWin = false;
                    }
                }
                if (mainDiagonalWin || otherDiagonalWin) {
                    return true;
                }
            }
        }

        return false; // No winner found
    }

    /**
     * Method to make a move on the board for the current player
     * @param row the row to make the move in
     * @param col the column to make the move in
     */
    private void makeMove(int row, int col) {
        if (board[row][col] == EMPTY_CELL) {
            board[row][col] = currentPlayer;
            switchPlayer();
        } else {
            System.out.println(LanguageManager.getResource("occupiedCell"));
        }
    }

    /**
     * Method to make a move on the board for the computer AI
     */
    private void makeAIMove() {
        // Check for winning move
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    board[i][j] = currentPlayer;
                    if (checkWin()) {
                        switchPlayer();
                        return;
                    } else {
                        board[i][j] = EMPTY_CELL;
                    }
                }
            }
        }

        // Check for blocking opponent's winning move
        char opponentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    board[i][j] = opponentPlayer;
                    if (checkWin()) {
                        board[i][j] = currentPlayer;
                        switchPlayer();
                        return;
                    } else {
                        board[i][j] = EMPTY_CELL;
                    }
                }
            }
        }

        // If no winning or blocking move, make a random move
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(board.length);
            col = random.nextInt(board.length);
        } while (board[row][col] != EMPTY_CELL);
        board[row][col] = currentPlayer;
        switchPlayer();
    }

    /**
     * Method to play the Tic Tac Toe game against the computer AI
     */
    public void playWithAI() {
        Scanner scanner = new Scanner(System.in);
        while (!checkWin()) {
            printBoard();
            if (currentPlayer == PLAYER_X) {
                System.out.println(LanguageManager.getResource("playerTurnRow", currentPlayer) + " ");
                int row = scanner.nextInt();
                System.out.println(LanguageManager.getResource("playerTurnColumn", currentPlayer) + " ");
                int col = scanner.nextInt();
                makeMove(row, col);
            } else {
                System.out.println(LanguageManager.getResource("computersTurn"));
                makeAIMove();
            }
        }
        printBoard();
        if (currentPlayer == PLAYER_X) {
            System.out.println(LanguageManager.getResource("playerOWins"));
        } else {
            System.out.println(LanguageManager.getResource("playerXWins"));
        }
        scanner.close();
    }

    /**
     * Method to play the Tic Tac Toe game with a friend
     */
    public void playWithFriend() {
        Scanner scanner = new Scanner(System.in);
        while (!checkWin()) {
            printBoard();
            System.out.print(LanguageManager.getResource("playerTurnRow", currentPlayer) + " ");
            int row = scanner.nextInt();
            System.out.print(LanguageManager.getResource("playerTurnColumn", currentPlayer) + " ");
            int col = scanner.nextInt();
            makeMove(row, col);
        }
        printBoard();
        System.out.print(LanguageManager.getResource("playerWins", currentPlayer));
        scanner.close();
    }

    /**
     * Method to switch the current player
     */
    private void switchPlayer() {
        currentPlayer = (currentPlayer == PLAYER_X) ? PLAYER_O : PLAYER_X;
    }
}