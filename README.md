### Alicia's Cozy Oasis - DMS Phase 1
Welcome to the Cozy Oasis, a Database Management System specifically designed to track and rate "Cozy Games." This project was built for Software Development 1 to demonstrate Core OOP principles, CRUD functionality, and robust data validation.

#### Key Features
6-Point Data Model: Each game tracks Title, Genre, Developer, Platform, Release Year, and a "Comfy Rating."

Batch Import: Load 20+ game samples instantly from a .txt file.

Data Integrity: Implements Regex validation to ensure text fields contain letters and years fall within a reasonable range (1958–2026).

Custom Action: Automatically calculates the Average Comfy Rating of the entire collection.

#### How to Run
Open the project in IntelliJ.

Ensure the games.txt file is in the root directory.

Run the Main.java file.

Use the menu to interact with the Oasis!

#### Project Structure
Game.java: The Object class (Data Model).

GameManager.java: The Logic class (Controller).

Main.java: The Application class (User Interface).
