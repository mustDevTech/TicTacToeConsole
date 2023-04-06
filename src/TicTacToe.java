import java.util.Arrays;
import java.util.Scanner;

/**
 * Class to represent the Tic Tac Toe game
 */
public class TicTacToe
{
    private final char[][] board; // 2D array to represent the Tic Tac Toe board
    private char currentPlayer; // current player, can be X or O
    private static final char PLAYER_X = 'X'; // constant for player X
    private static final char PLAYER_O = 'O'; // constant for player O
    private static final char EMPTY_CELL = '-'; // constant for empty cells on the board

    /**
     * Constructor method to initialize the board and current player
     */
    public TicTacToe()
    {
        board = new char[3][3];
        currentPlayer = PLAYER_X;
        initializeBoard();
    }

    /**
     * Method to initialize the Tic Tac Toe board with empty cells
     */
    private void initializeBoard()
    {
        for (int i = 0; i < 3; i++)
        {
            Arrays.fill(board[i], EMPTY_CELL);
        }
    }

    /**
     * Method to 'Visually' print the Tic Tac Toe board
     */
    private void printBoard()
    {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++)
        {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Method to check if there is a winner by checking the rows, columns, and diagonals
     * @return true if there is a winner, false otherwise
     */
    private boolean checkWin()
    {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    /**
     * Method to check if there is a winner in the rows
     * @return true if there is a winner in the rows, false otherwise
     */
    private boolean checkRows()
    {
        for (int i = 0; i < 3; i++)
        {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] != EMPTY_CELL)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check if there is a winner in the columns
     * @return true if there is a winner in the columns, false otherwise
     */
    private boolean checkColumns()
    {
        for (int i = 0; i < 3; i++)
        {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] != EMPTY_CELL)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check if there is a winner in the diagonals
     * @return true if there is a winner in the diagonals, false otherwise
     */
    private boolean checkDiagonals()
    {
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != EMPTY_CELL)
        {
            return true;
        }
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != EMPTY_CELL)
        {
            return true;
        }
        return false;
    }

    /**
     * Method to make a move on the board for the current player
     * @param row the row to make the move in
     * @param col the column to make the move in
     */
    private void makeMove(int row, int col)
    {
        if (board[row][col] == EMPTY_CELL)
        {
            board[row][col] = currentPlayer;
            if (currentPlayer == PLAYER_X)
            {
                currentPlayer = PLAYER_O;
            } else
            {
                currentPlayer = PLAYER_X;
            }
        } else
        {
            System.out.println(LanguageManager.getResource("occupiedCell"));
        }
    }

    /**
     * Method to make a move on the board for the computer AI
     */
    private void makeAIMove()
    {
        int row, col;
        do
        {
            row = (int) (Math.random() * 3);
            col = (int) (Math.random() * 3);
        } while (board[row][col] != EMPTY_CELL);
        board[row][col] = currentPlayer;
        if (currentPlayer == PLAYER_X)
        {
            currentPlayer = PLAYER_O;
        } else
        {
            currentPlayer = PLAYER_X;
        }
    }

    /**
     * Method to play the Tic Tac Toe game against the computer AI
     */
    public void play()
    {
        Scanner scanner = new Scanner(System.in);
        while (!checkWin())
        {
            printBoard();
            if (currentPlayer == PLAYER_X)
            {
                System.out.println(LanguageManager.getResource("playerTurnRow", currentPlayer) + " ");
                int row = scanner.nextInt();
                System.out.println(LanguageManager.getResource("playerTurnColumn", currentPlayer) + " ");
                int col = scanner.nextInt();
                makeMove(row, col);
            } else
            {
                System.out.println(LanguageManager.getResource("computersTurn"));
                makeAIMove();
            }
        }
        printBoard();
        if (currentPlayer == PLAYER_X)
        {
            System.out.println(LanguageManager.getResource("playerOWins"));
        } else
        {
            System.out.println(LanguageManager.getResource("playerXWins"));
        }
        scanner.close();
    }

    /**
     * Method to make a move on the board for the current player in a two-player game
     * @param row the row to make the move in
     * @param col the column to make the move in
     */
    private void makeMoveWithFriend(int row, int col)
    {
        if (board[row][col] == EMPTY_CELL)
        {
            board[row][col] = currentPlayer;
            if (currentPlayer == PLAYER_X)
            {
                currentPlayer = PLAYER_O;
            } else
            {
                currentPlayer = PLAYER_X;
            }
        } else
        {
            System.out.println(LanguageManager.getResource("occupiedCell"));
        }
    }

    /**
     * Method to play the Tic Tac Toe game with a friend
     */
    public void playWithFriend()
    {
        Scanner scanner = new Scanner(System.in);
        while (!checkWin())
        {
            printBoard();
            System.out.print(LanguageManager.getResource("playerTurnRow", currentPlayer) + " ");
            int row = scanner.nextInt();
            System.out.print(LanguageManager.getResource("playerTurnColumn", currentPlayer) + " ");
            int col = scanner.nextInt();
            makeMoveWithFriend(row, col);
        }
        printBoard();
        System.out.print(LanguageManager.getResource("playerWins", currentPlayer));
        scanner.close();
    }
}