/**
 * Data Model for a Cozy Game.
 * Includes a Unique Identifier (Primary Key) to distinguish between
 * games with the same title on different platforms.
 */
public class Game {
    private int gameID; // Primary Key
    private String title;
    private String genre;
    private String developer;
    private String platform;
    private int releaseYear;
    private int comfyRating;

    // Constructor: All 7 fields must be provided
    public Game(int gameID, String title, String genre, String developer, String platform, int releaseYear, int comfyRating) {
        this.gameID = gameID;
        this.title = title;
        this.genre = genre;
        this.developer = developer;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.comfyRating = comfyRating;
    }

    // Getters
    public int getGameID() { return gameID; }
    public String getTitle() { return title; }
    public int getComfyRating() { return comfyRating; }

    // Logic Validation: Rating must be 1-10
    public boolean setComfyRating(int rating) {
        if (rating >= 1 && rating <= 10) {
            this.comfyRating = rating;
            return true;
        }
        return false;
    }

    // Logic Validation: Year must be reasonable
    public boolean setYear(int year) {
        if (year >= 1958 && year <= 2026) {
            this.releaseYear = year;
            return true;
        }
        return false;
    }

    /**
     * Returns a formatted record.
     * Note: ID is placed first to emphasize its role as the Primary Key.
     */
    public String getDetails() {
        return String.format("ID: %-5d | Title: %-20s | Platform: %-10s | Year: %d | Comfy: %d/10",
                gameID, title, platform, releaseYear, comfyRating);
    }
}