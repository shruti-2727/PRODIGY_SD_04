import java.util.Scanner;

public class SudokuSolver {

    private static final int GRID_SIZE = 9;

    public static void main(String[] args) {
        int[][] board = new int[GRID_SIZE][GRID_SIZE];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the Sudoku puzzle row by row. Use 0 for empty cells:");
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                board[i][j] = scanner.nextInt();
            }
        }

        if (solveSudoku(board)) {
            System.out.println("Sudoku solved successfully:");
            printBoard(board);
        } else {
            System.out.println("Unsolvable Sudoku.");
        }

        scanner.close();
    }

    private static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= GRID_SIZE; num++) {
                        if (isValidPlacement(board, num, row, col)) {
                            board[row][col] = num;

                            if (solveSudoku(board)) {
                                return true;
                            }

                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isValidPlacement(int[][] board, int num, int row, int col) {
        // Check the row
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check the column
        for (int i = 0; i < GRID_SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check the 3x3 subgrid
        int subGridRowStart = (row / 3) * 3;
        int subGridColStart = (col / 3) * 3;
        for (int i = subGridRowStart; i < subGridRowStart + 3; i++) {
            for (int j = subGridColStart; j < subGridColStart + 3; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void printBoard(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}
