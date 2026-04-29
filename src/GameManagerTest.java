import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for the Cozy Game DMS.
 * Verifies CRUD operations and custom actions using JUnit 5.
 */
public class GameManagerTest {
    private GameManager manager;

    @BeforeEach
    void setUp() {
        // Start each test with a fresh, empty manager
        manager = new GameManager();
    }

    @Test
    void testAddObject() {
        GameManager manager = new GameManager();
        // Added 0.0 for hoursPlayed to match the new constructor
        Game testGame = new Game(999, "Test", "Genre", "Dev", "PC", 2024, 0.0, 10);
        assertTrue(manager.addGame(testGame));
    }

    @Test
    void testRemoveObject() {
        Game testGame =new Game(999, "Test", "Genre", "Dev", "PC", 2024, 0.0, 10);
        manager.addGame(testGame);

        // Assert that removing by the correct ID returns true
        assertTrue(manager.removeGameByID(999), "Object should be successfully removed.");
        assertEquals(0, manager.getAllGames().size(), "List should be empty after removal.");
    }

    @Test
    void testUpdateObject() {
        // 1. Setup: Use a unique ID to avoid conflicts with existing database records
        int testID = 999;
        Game testGame = new Game(testID, "Test Update", "Genre", "Dev", "PC", 2024, 0.0, 5);
        manager.addGame(testGame);

        // 2. Execute: Use the renamed database method 'updateRatingByID'
        // This returns true if the SQL 'UPDATE' command affected 1 or more rows.
        assertTrue(manager.updateRatingByID(testID, 10), "Update should return true for valid ID.");

        // 3. Verify: Pull the list fresh from the database to see the change
        List<Game> games = manager.getAllGames();

        // Find our specific game in the list to verify the rating changed
        int updatedRating = 0;
        for (Game g : games) {
            if (g.getGameID() == testID) {
                updatedRating = g.getComfyRating();
                break;
            }
        }

        assertEquals(10, updatedRating, "Rating should be updated to 10 in the database.");
    }

    @Test
    void testCustomAction() {
        // Clear old data if necessary or just add unique test IDs
        // Added 0.0 for the hoursPlayed parameter
        manager.addGame(new Game(901, "Game A", "Test Gen", "Test Dev", "PC", 2024, 0.0, 10));
        manager.addGame(new Game(902, "Game B", "Test Gen", "Test Dev", "PC", 2024, 0.0, 6));

        // Updated method name to match the new GameManager
        assertEquals(8.0, manager.calculateAverageRating(1), 0.01, "Average rating calculation is incorrect.");
    }
    @Test
    void testDatabaseRead() {
        // Ensure the manager can pull the list from the DB
        List<Game> games = manager.getAllGames();

        // As long as you ran your SQL script, this should not be null
        assertNotNull(games, "The game list should be successfully retrieved from the database.");
    }

}