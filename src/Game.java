/**
 * Data Model for a Cozy Game.
 * Includes a Unique Identifier (Primary Key) to distinguish between
 * games with the same title on different platforms.
 */
/**
 * Data Model for a Cozy Game (Phase 4).
 * Updated to include 'Hours Played' for database and GUI synchronization.
 *Synchronized with Phase 4 Database schema (8 fields).
 */
public class Game {
    private int gameID;
    private String title;
    private String genre;
    private String developer;
    private String platform;
    private int releaseYear;
    private double hoursPlayed;
    private int comfyRating;

    // Constructor - Must take all 8 parameters in this exact order
    public Game(int gameID, String title, String genre, String developer, String platform, int releaseYear, double hoursPlayed, int comfyRating) {
        this.gameID = gameID;
        this.title = title;
        this.genre = genre;
        this.developer = developer;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.hoursPlayed = hoursPlayed;
        this.comfyRating = comfyRating;
    }

    // Getters
    public int getGameID() { return gameID; }
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public String getDeveloper() { return developer; }
    public String getPlatform() { return platform; }
    public int getReleaseYear() { return releaseYear; }
    public double getHoursPlayed() { return hoursPlayed; }
    public int getComfyRating() { return comfyRating; }
}