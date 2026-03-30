import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        // Create a dummy object
        Game testGame = new Game(999, "Test Game", "Test Gen", "Test Dev", "PC", 2024,5);

        // Assert that the list size is 1 after adding
        assertEquals(1, manager.addGame(testGame), "Database should contain 1 object after addition.");
    }

    @Test
    void testRemoveObject() {
        Game testGame = new Game(999, "Test Game", "Test Gen", "Test Dev", "PC", 2024,5);
        manager.addGame(testGame);

        // Assert that removing by the correct ID returns true
        assertTrue(manager.removeGameByID(999), "Object should be successfully removed.");
        assertEquals(0, manager.getAllGames().size(), "List should be empty after removal.");
    }

    @Test
    void testUpdateObject() {
        Game testGame = new Game(999, "Test Game", "Test Gen", "Test Dev", "PC", 2024,5);
        manager.addGame(testGame);

        // Assert that updating the rating to 10 returns true
        assertTrue(manager.updateComfyRatingByID(999, 10), "Update should return true for valid ID.");
        assertEquals(10, manager.getAllGames().get(0).getComfyRating(), "Rating should be updated to 10.");
    }

    @Test
    void testCustomAction() {
        // Add two games with specific ratings
        manager.addGame(new Game(1, "Game A", "Test Gen", "Test Dev", "PC", 2024,10));
        manager.addGame(new Game(2, "Game B", "Test Gen", "Test Dev", "PC", 2024,6));

        // Custom Action: Average should be (10 + 6) / 2 = 8.0
        assertEquals(8.0, manager.calculateAverageComfyRating(), "Average rating calculation is incorrect.");
    }

    @Test
    void testOpenFile() {
        // Test that loading a non-existent file returns 0 (handling the error gracefully)
        int result = manager.loadGamesFromFile("non_existent_file.txt");
        assertEquals(0, result, "Should return 0 when a file cannot be opened.");
    }
}