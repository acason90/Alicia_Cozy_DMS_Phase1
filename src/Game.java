/**
 * Data Model for a Cozy Game.
 * Includes a Unique Identifier (Primary Key) to distinguish between
 * games with the same title on different platforms.
 /**
 * Data Model for a Cozy Game.
 * Represents a single game record in the Cozy Oasis Management System.
 * Synchronized with Phase 4 Database schema (8 fields).
 * * @author Alicia
 * @version 4.0
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

    /**
     * Constructs a new Game object with all required attributes.
     * * @param gameID unique identifier for the record
     * @param title the name of the game
     * @param genre the category of the game
     * @param developer the studio that created the game
     * @param platform the system the game is played on
     * @param releaseYear the year the game was published
     * @param hoursPlayed total time spent in the game
     * @param comfyRating the user-assigned cozy score (1-10)
     */

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

    /** @return the unique game ID */
    public int getGameID() { return gameID; }

    /** @return the title of the game */
    public String getTitle() { return title; }

    /** @return the genre of the game */
    public String getGenre() { return genre; }

    /** @return the developer of the game */
    public String getDeveloper() { return developer; }

    /** @return the platform of the game */
    public String getPlatform() { return platform; }

    /** @return the release year of the game */
    public int getReleaseYear() { return releaseYear; }

    /** @return the total hours played */
    public double getHoursPlayed() { return hoursPlayed; }

    /** @return the user-assigned cozy rating */
    public int getComfyRating() { return comfyRating; }
}