import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Manages the collection of Game objects.
 * Handles CRUD operations, file I/O, and mathematical calculations.
 */
public class GameManager {
    private List<Game> gameList;

    public GameManager() {
        this.gameList = new ArrayList<>(); // Initializes the storage list
    }

    // Adds a Game object to the list and returns current count (Verification)
    public int addGame(Game newGame) {
        if (newGame != null) gameList.add(newGame);
        return gameList.size();
    }

    public List<Game> getAllGames() { return gameList; }

    // Deletes a game by searching for a matching title (Case-Insensitive)
    public boolean removeGameByTitle(String title) {
        return gameList.removeIf(g -> g.getTitle().equalsIgnoreCase(title));
    }

    /**
     * Custom Action: Calculates the arithmetic mean of all comfy ratings.
     * Prevents division by zero if the list is empty.
     */
    public double calculateAverageComfyRating() {
        if (gameList.isEmpty()) return 0.0;
        double total = 0;
        for (Game g : gameList) total += g.getComfyRating();
        return total / gameList.size();
    }

    /**
     * Reads a CSV file and converts each line into a Game object.
     * Implements strict data validation to filter out "junk" data.
     */
    public int loadGamesFromFile(String filePath) {
        int count = 0;
        try {
            File file = new File(filePath);
            Scanner fs = new Scanner(file);
            while (fs.hasNextLine()) {
                String line = fs.nextLine();
                if (line.trim().isEmpty()) continue; // Skip blank lines

                String[] d = line.split(",");
                if (d.length == 6) { // Ensure all 6 fields exist
                    try {
                        String title = d[0].trim();
                        String genre = d[1].trim();
                        String dev = d[2].trim();
                        String plat = d[3].trim();
                        int year = Integer.parseInt(d[4].trim());
                        int rate = Integer.parseInt(d[5].trim());

                        // Data integrity check: Letters required in text, valid year/rating ranges
                        if (genre.matches(".*[a-zA-Z].*") && year >= 1958 && year <= 2026 && rate >= 1 && rate <= 10) {
                            addGame(new Game(title, genre, dev, plat, year, rate));
                            count++;
                        }
                    } catch (Exception e) {
                        // Catches parsing errors for a specific row so the whole process doesn't stop
                    }
                }
            }
            fs.close();
        } catch (Exception e) {
            System.out.println("File Error: " + e.getMessage());
        }
        return count; // Returns number of successfully loaded games
    }
}