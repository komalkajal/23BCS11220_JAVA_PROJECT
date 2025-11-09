import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        String playerX = JOptionPane.showInputDialog("Enter Player X Name:");
        String playerO = JOptionPane.showInputDialog("Enter Player O Name:");

        if (playerX == null || playerO == null || playerX.trim().isEmpty() || playerO.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Both names are required.");
            System.exit(0);
        }

        Player p1 = new Player(playerX, "X");
        Player p2 = new Player(playerO, "O");

        SwingUtilities.invokeLater(() -> new GameBoard(p1, p2));
    }
}
