import java.util.Scanner;

/**
 * Main application for Alicia's Cozy Oasis.
 * Provides user input validation and record management.
 */
public class Main {
    public static void main(String[] args) {
        GameManager manager = new GameManager();
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Alicia's Cozy Oasis DMS ---");
            System.out.println("1. Batch Load (games.txt)\n2. Add Manual Record\n3. Display All Records\n4. Update Rating (by ID)\n5. Remove Game (by ID)\n6. Average Rating\n7. Exit");
            System.out.print("Choice: ");
            int choice = getIntInput(input);

            switch (choice) {
                case 1:
                    System.out.print("Enter Filename: ");
                    System.out.println("Added: " + manager.loadGamesFromFile(input.nextLine()));
                    break;
                case 2:
                    System.out.print("Enter Unique ID: "); int id = getIntInput(input);
                    System.out.print("Title: "); String t = input.nextLine();
                    String g = getStringWithLetters(input, "Genre");
                    System.out.print("Developer: "); String d = input.nextLine();
                    String p = getStringWithLetters(input, "Platform");
                    System.out.print("Year (1958-2026): "); int y = getValidYear(input);
                    System.out.print("Rating (1-10): "); int r = getValidRating(input);
                    manager.addGame(new Game(id, t, g, d, p, y, r));
                    break;
                case 3:
                    if (manager.getAllGames().isEmpty()) System.out.println("Oasis is empty.");
                    for (Game game : manager.getAllGames()) System.out.println(game.getDetails());
                    break;
                case 4:
                    System.out.print("Enter ID to update: "); int upId = getIntInput(input);
                    System.out.print("New Rating: "); int upRate = getValidRating(input);
                    System.out.println(manager.updateComfyRatingByID(upId, upRate) ? "Updated" : "ID not found");
                    break;
                case 5:
                    System.out.print("Enter ID to remove: "); int remId = getIntInput(input);
                    System.out.println(manager.removeGameByID(remId) ? "Removed" : "ID not found");
                    break;
                case 6:
                    System.out.printf("Avg Comfy Rating: %.2f/10%n", manager.calculateAverageComfyRating());
                    break;
                case 7:
                    running = false;
                    break;
            }
        }
    }

    // Input Validation Helper Methods
    public static int getIntInput(Scanner s) {
        while (!s.hasNextInt()) { s.next(); System.out.print("Enter a number: "); }
        int val = s.nextInt(); s.nextLine(); return val;
    }

    public static String getStringWithLetters(Scanner s, String field) {
        while (true) {
            System.out.print(field + ": ");
            String res = s.nextLine();
            if (res.matches(".*[a-zA-Z].*")) return res;
            System.out.println("Invalid! Must contain letters.");
        }
    }

    public static int getValidYear(Scanner s) {
        while (true) {
            int y = getIntInput(s);
            if (y >= 1958 && y <= 2026) return y;
            System.out.print("Invalid Year. Try again: ");
        }
    }

    public static int getValidRating(Scanner s) {
        while (true) {
            int r = getIntInput(s);
            if (r >= 1 && r <= 10) return r;
            System.out.print("Rating must be 1-10: ");
        }
    }
}