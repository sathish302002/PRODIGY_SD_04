public class Sudoku {

    public static boolean solveSudoku(int[][] board) {
        int N = board.length;
        return solve(board, N);
    }

    private static boolean solve(int[][] board, int N) {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= N; num++) {
                        if (isOk(board, row, col, num)) {
                            board[row][col] = num;
                            if (solve(board, N)) {
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

    private static boolean isOk(int[][] board, int row, int col, int num) {
        int N = board.length;

        
        for (int x = 0; x < N; x++) {
            if (board[row][x] == num || board[x][col] == num) {
                return false;
            }
        }

        
        int subgridSize = (int) Math.sqrt(N);
        int startRow = row - row % subgridSize;
        int startCol = col - col % subgridSize;

        for (int i = 0; i < subgridSize; i++) {
            for (int j = 0; j < subgridSize; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printSudoku(int[][] board) {
        int N = board.length;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {3, 0, 6, 5, 0, 8, 4, 0, 0},
            {5, 2, 0, 0, 0, 0, 0, 0, 0},
            {0, 8, 7, 0, 0, 0, 0, 3, 1},
            {0, 0, 3, 0, 1, 0, 0, 8, 0},
            {9, 0, 0, 8, 6, 3, 0, 0, 5},
            {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
            {1, 3, 0, 0, 0, 0, 2, 5, 0},
            {0, 0, 0, 0, 0, 0, 0, 7, 4},
            {0, 0, 5, 2, 0, 6, 3, 0, 0} 
   
        };

        if (solveSudoku(board)) {
            System.out.println("You have Solved the  Sudoku:");
            printSudoku(board);
        } else {
            System.out.println("No solution exists for the input you gave.");
        }
    }
}
