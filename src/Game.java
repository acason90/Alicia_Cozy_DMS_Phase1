/**
 * Represents a single "Cozy Game" object.
 * Synchronized to prevent IllegalFormatConversionException.
 */
public class Game {
    private String title;
    private String genre;
    private String developer;
    private String platform;
    private int releaseYear;
    private int comfyRating;

    // Constructor - The order here MUST match how you call it in GameManager/Main
    public Game(String title, String genre, String developer, String platform, int releaseYear, int comfyRating) {
        this.title = title;
        this.genre = genre;
        this.developer = developer;
        this.platform = platform;
        this.releaseYear = releaseYear;
        this.comfyRating = comfyRating;
    }

    // Getters
    public String getTitle() { return title; }
    public String getGenre() { return genre; }
    public int getComfyRating() { return comfyRating; }

    // Validation Setters
    public boolean setComfyRating(int rating) {
        if (rating >= 1 && rating <= 10) {
            this.comfyRating = rating;
            return true;
        }
        return false;
    }

    public boolean setYear(int year) {
        if (year >= 1958 && year <= 2026) {
            this.releaseYear = year;
            return true;
        }
        return false;
    }

    /**
     * FORMAT CHECK:
     * %s = String, %d = Decimal Integer
     * Order: Title (s), Genre (s), Dev (s), Platform (s), Year (d), Rating (d)
     */
    public String getDetails() {
        return String.format("Title: %-20s | Genre: %-12s | Dev: %-15s | Platform: %-10s | Year: %d | Comfy: %d/10",
                title, genre, developer, platform, releaseYear, comfyRating);
    }
}