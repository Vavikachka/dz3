
// На шахматной доске расставить 8 ферзей так, чтобы они не били друг друга.
import java.util.ArrayList;
import java.util.List;

public class task7 {
    List<List<String>> ans = new ArrayList<>();
    char[][] board;

    public static void main(String[] args) {
        task7 sol = new task7();
        sol.solveNQueens(8);

        System.out.println(sol.ans.size());
        for (var board : sol.ans) {
            board.forEach(System.out::println);
            System.out.println("   ");
        }

    }

    public List<List<String>> solveNQueens(int n) {
        init(n);

        backtack(0, n);
        return ans;
    }

    void backtack(int col, int n) {
        if (col == n) {
            addBoard();
            return;
        }
        for (int row = 0; row < n; row++) {
            if (isSafe(row, col)) {
                board[row][col] = 'Q';
                backtack(col + 1, n);
                board[row][col] = '-';
            }
        }
    }

    boolean isSafe(int row, int col) {
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 'Q')
                return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q')
                return false;
        }
        for (int i = row + 1, j = col - 1; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 'Q')
                return false;
        }
        return true;
    }

    private void addBoard() {
        List<String> newBoard = new ArrayList<>();
        for (char[] chars : board) {
            newBoard.add(new String(chars));
        }
        ans.add(newBoard);
    }

    private void init(int n) {
        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            for (int j = 0; j < n; j++) {
                row[j] = '-';
            }
            board[i] = row;
        }
    }
}
