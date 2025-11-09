public class GameLogic {
    private String[][] board;
    private String currentSymbol;

    public GameLogic() {
        board = new String[3][3];
        currentSymbol = "X";
    }

    public String getCurrentSymbol() {
        return currentSymbol;
    }

    public void switchTurn() {
        currentSymbol = currentSymbol.equals("X") ? "O" : "X";
    }

    public boolean markCell(int row, int col) {
        if (board[row][col] == null) {
            board[row][col] = currentSymbol;
            return true;
        }
        return false;
    }

    public String checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (same(board[i][0], board[i][1], board[i][2])) return board[i][0];
            if (same(board[0][i], board[1][i], board[2][i])) return board[0][i];
        }
        if (same(board[0][0], board[1][1], board[2][2])) return board[0][0];
        if (same(board[0][2], board[1][1], board[2][0])) return board[0][2];
        return null;
    }

    public boolean isDraw() {
        for (String[] row : board)
            for (String cell : row)
                if (cell == null) return false;
        return checkWinner() == null;
    }

    public void resetGame() {
        board = new String[3][3];
        currentSymbol = "X";
    }

    private boolean same(String a, String b, String c) {
        return a != null && a.equals(b) && b.equals(c);
    }
}
