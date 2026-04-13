import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for Database Operations.
 * Handles CRUD (Create, Read, Update, Delete) and Custom Actions using JDBC.
 * Connects to the 'cozy_oasis.db' SQLite file.
 */
public class GameManager {
    // Relative path
    private final String url = "jdbc:sqlite:cozy_oasis.db";

    /**
     * Retrieves all game records from the SQLite database.
     * @return a List of Game objects populated from the database
     */
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
    /**
     * Inserts a new game record into the database.
     * @param g the Game object to be added
     * @return true if insertion was successful, false otherwise
     */
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

    /**
     * Removes a specific game record from the database using its unique ID.
     * This method executes a SQL DELETE command.
     * * @param id the unique integer identifier of the game to be deleted
     * @return true if a record was successfully found and removed;
     * false if the ID was not found or a database error occurred
     */
    public boolean removeGameByID(int id) {
        String sql = "DELETE FROM games WHERE gameID = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    /**
     * Custom Action: Calculates the average cozy rating across all games.
     * @return the average rating as a double
     */
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
     * Updates the comfy rating for an existing game.
     * @param id the ID of the game to update
     * @param newRating the new rating value (1-10)
     * @return true if the update was successful
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