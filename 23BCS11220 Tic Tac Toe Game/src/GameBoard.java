import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameBoard extends JFrame {
    private JButton[][] buttons = new JButton[3][3];
    private JLabel statusLabel;
    private GameLogic logic;
    private Player playerX, playerO;
    private DatabaseManager db;

    public GameBoard(Player pX, Player pO) {
        this.playerX = pX;
        this.playerO = pO;
        this.logic = new GameLogic();
        this.db = new DatabaseManager();

        setTitle("Tic-Tac-Toe");
        setSize(800, 800);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel grid = new JPanel(new GridLayout(3, 3));
        Font btnFont = new Font("Arial", Font.BOLD, 40);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton btn = new JButton("");
                btn.setFont(btnFont);
                final int row = i, col = j;
                btn.addActionListener(e -> handleClick(row, col, btn));
                buttons[i][j] = btn;
                grid.add(btn);
            }
        }

        statusLabel = new JLabel("Turn: " + playerX.getName() + " (" + playerX.getSymbol() + ")");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel bottomPanel = new JPanel(new GridLayout(1, 2));
        JButton playAgain = new JButton("Play Again");
        JButton exit = new JButton("Exit");

        playAgain.addActionListener(e -> resetGame());
        exit.addActionListener(e -> System.exit(0));

        bottomPanel.add(playAgain);
        bottomPanel.add(exit);

        JTextArea historyArea = new JTextArea(5, 30);
        historyArea.setEditable(false);
        updateHistory(historyArea);

        add(grid, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.SOUTH);
        add(new JScrollPane(historyArea), BorderLayout.EAST);

        setVisible(true);
    }

    private void handleClick(int row, int col, JButton btn) {
        if (logic.markCell(row, col)) {
            btn.setText(logic.getCurrentSymbol());
            String winner = logic.checkWinner();
            if (winner != null) {
                Player winnerPlayer = winner.equals("X") ? playerX : playerO;
                statusLabel.setText("Winner: " + winnerPlayer.getName());
                db.saveWinner(winnerPlayer.getName());
                disableButtons();
            } else if (logic.isDraw()) {
                statusLabel.setText("It's a draw!");
            } else {
                logic.switchTurn();
                Player current = logic.getCurrentSymbol().equals("X") ? playerX : playerO;
                statusLabel.setText("Turn: " + current.getName() + " (" + current.getSymbol() + ")");
            }
        }
    }

    private void resetGame() {
        logic.resetGame();
        for (JButton[] row : buttons)
            for (JButton btn : row) {
                btn.setText("");
                btn.setEnabled(true);
            }
        statusLabel.setText("Turn: " + playerX.getName() + " (" + playerX.getSymbol() + ")");
    }

    private void disableButtons() {
        for (JButton[] row : buttons)
            for (JButton btn : row)
                btn.setEnabled(false);
    }

    private void updateHistory(JTextArea historyArea) {
        ArrayList<String> winners = db.getWinners();
        historyArea.setText("Previous Winners:\n");
        for (String s : winners) {
            historyArea.append(s + "\n");
        }
    }
}
