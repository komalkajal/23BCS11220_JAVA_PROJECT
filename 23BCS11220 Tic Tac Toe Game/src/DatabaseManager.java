import java.sql.*;
import java.util.ArrayList;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tictactoe";
    private static final String USER = "root"; // change if needed
    private static final String PASS = "Kesh@v108";     // your MySQL password

    public void saveWinner(String winnerName) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String query = "INSERT INTO winners (winner_name) VALUES (?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, winnerName);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getWinners() {
        ArrayList<String> winners = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT winner_name, match_date FROM winners ORDER BY match_date DESC ");
            while (rs.next()) {
                winners.add(rs.getString("winner_name") + " (" + rs.getTimestamp("match_date") + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return winners;
    }
}
