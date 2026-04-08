import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for Database Operations.
 * Handles CRUD and Custom Actions using JDBC.
 */
public class GameManager {
    // Relative path
    private final String url = "jdbc:sqlite:cozy_oasis.db";

    public List<Game> getAllGames() {
        List<Game> games = new ArrayList<>();
        String sql = "SELECT * FROM games";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                games.add(new Game(
                        rs.getInt("gameID"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getString("developer"),
                        rs.getString("platform"),
                        rs.getInt("releaseYear"),
                        rs.getDouble("hoursPlayed"),
                        rs.getInt("comfyRating")
                ));
            }
        } catch (SQLException e) { System.out.println("Read Error: " + e.getMessage()); }
        return games;
    }

    public boolean addGame(Game g) {
        String sql = "INSERT INTO games VALUES(?,?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, g.getGameID());
            pstmt.setString(2, g.getTitle());
            pstmt.setString(3, g.getGenre());
            pstmt.setString(4, g.getDeveloper());
            pstmt.setString(5, g.getPlatform());
            pstmt.setInt(6, g.getReleaseYear());
            pstmt.setDouble(7, g.getHoursPlayed());
            pstmt.setInt(8, g.getComfyRating());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean removeGameByID(int id) {
        String sql = "DELETE FROM games WHERE gameID = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public double calculateAverageRating() {
        String sql = "SELECT AVG(comfyRating) FROM games";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) { System.out.println(e.getMessage()); }
        return 0.0;
    }
    /**
     * UPDATE: Modifies the rating of a specific game in the database.
     */
    public boolean updateRatingByID(int id, int newRating) {
        String sql = "UPDATE games SET comfyRating = ? WHERE gameID = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newRating);
            pstmt.setInt(2, id);

            // Returns true if 1 or more rows were changed
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Update Error: " + e.getMessage());
            return false;
        }
    }


}