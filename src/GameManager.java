import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Controller class that manages the list of Games.
 * Uses GameID for precise record targeting.
 */
public class GameManager {
    private List<Game> gameList;

    public GameManager() {
        this.gameList = new ArrayList<>();
    }

    // CREATE: Adds game and returns new list size
    public int addGame(Game newGame) {
        if (newGame != null) gameList.add(newGame);
        return gameList.size();
    }

    public List<Game> getAllGames() { return gameList; }

    /**
     * DELETE: Targets a specific record by its unique ID.
     * Prevents accidental deletion of duplicates (e.g., same game on different platforms).
     */
    public boolean removeGameByID(int id) {
        return gameList.removeIf(g -> g.getGameID() == id);
    }

    /**
     * UPDATE: Targets a specific record by its ID to update the rating.
     */
    public boolean updateComfyRatingByID(int id, int newRating) {
        for (Game game : gameList) {
            if (game.getGameID() == id) {
                return game.setComfyRating(newRating);
            }
        }
        return false;
    }

    // CUSTOM ACTION: Average of all ratings
    public double calculateAverageComfyRating() {
        if (gameList.isEmpty()) return 0.0;
        double total = 0;
        for (Game g : gameList) total += g.getComfyRating();
        return total / gameList.size();
    }

    /**
     * BATCH LOAD: Parses 7 fields per line.
     * Format: ID, Title, Genre, Developer, Platform, Year, Rating
     */
    public int loadGamesFromFile(String filePath) {
        int count = 0;
        try {
            File file = new File(filePath);
            Scanner fs = new Scanner(file);
            while (fs.hasNextLine()) {
                String line = fs.nextLine();
                if (line.trim().isEmpty()) continue;
                String[] d = line.split(",");
                if (d.length == 7) { // Now expecting 7 fields
                    try {
                        int id = Integer.parseInt(d[0].trim());
                        String title = d[1].trim();
                        String genre = d[2].trim();
                        String dev = d[3].trim();
                        String plat = d[4].trim();
                        int year = Integer.parseInt(d[5].trim());
                        int rate = Integer.parseInt(d[6].trim());

                        // Data Integrity Validation
                        if (genre.matches(".*[a-zA-Z].*") && year >= 1958 && year <= 2026 && rate >= 1 && rate <= 10) {
                            addGame(new Game(id, title, genre, dev, plat, year, rate));
                            count++;
                        }
                    } catch (Exception e) { /* Skip invalid rows */ }
                }
            }
            fs.close();
        } catch (Exception e) { System.out.println("File error: " + e.getMessage()); }
        return count;
    }
}