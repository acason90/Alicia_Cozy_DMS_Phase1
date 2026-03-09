import java.util.Scanner;

/**
 * Entry point for Alicia's Cozy Oasis DMS.
 * Provides a Command Line Interface (CLI) for user interaction.
 */
public class Main {
    public static void main(String[] args) {
        GameManager manager = new GameManager();
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Alicia's Cozy Oasis DMS ---");
            System.out.println("1. Load Batch (games.txt)\n2. Add Manual\n3. Display All\n4. Remove Game\n5. Average Rating\n6. Exit");
            System.out.print("Choice: ");
            int choice = getIntInput(input);

            switch (choice) {
                case 1:
                    System.out.print("Filename: ");
                    System.out.println("Added: " + manager.loadGamesFromFile(input.nextLine()));
                    break;
                case 2:
                    // Manual entry with input loop validation for each field
                    System.out.print("Title: "); String t = input.nextLine();
                    String g = getStringWithLetters(input, "Genre");
                    System.out.print("Developer: "); String d = input.nextLine();
                    String p = getStringWithLetters(input, "Platform");
                    System.out.print("Year (1958-2026): "); int y = getValidYear(input);
                    System.out.print("Rating (1-10): "); int r = getValidRating(input);
                    manager.addGame(new Game(t, g, d, p, y, r));
                    break;
                case 3:
                    // Display all objects currently in the manager's list
                    for (Game game : manager.getAllGames()) System.out.println(game.getDetails());
                    break;
                case 4:
                    System.out.print("Title to remove: ");
                    System.out.println(manager.removeGameByTitle(input.nextLine()) ? "Removed" : "Not Found");
                    break;
                case 5:
                    // Mathematical Custom Action output
                    System.out.printf("Avg Comfy Rating: %.2f/10%n", manager.calculateAverageComfyRating());
                    break;
                case 6:
                    running = false; // Breaks the while loop
                    break;
            }
        }
    }

    // --- INPUT VALIDATION HELPERS ---

    // Forces user to enter an integer, prevents crash if they enter text
    public static int getIntInput(Scanner s) {
        while (!s.hasNextInt()) { s.next(); System.out.print("Enter a number: "); }
        int val = s.nextInt(); s.nextLine(); return val;
    }

    // Validates that the string is not just numbers
    public static String getStringWithLetters(Scanner s, String field) {
        while (true) {
            System.out.print(field + ": ");
            String res = s.nextLine();
            if (res.matches(".*[a-zA-Z].*")) return res;
            System.out.println("Invalid! Must contain letters.");
        }
    }

    // Ensures the year stays within the specified range
    public static int getValidYear(Scanner s) {
        while (true) {
            int y = getIntInput(s);
            if (y >= 1958 && y <= 2026) return y;
            System.out.print("Invalid Year. Try again: ");
        }
    }

    // Ensures the rating is between 1 and 10
    public static int getValidRating(Scanner s) {
        while (true) {
            int r = getIntInput(s);
            if (r >= 1 && r <= 10) return r;
            System.out.print("Rating must be 1-10: ");
        }
    }
}