# 🍵 Cozy Oasis Management System 
**Developer:** Alicia  
**Course:** Software Development 1 (CEN 3024C)

## 🌸 Overview
Cozy Oasis is a Java-based Desktop Management System (DMS) designed to help users track their "cozy" gaming library.
## 🛠️ Key Features
- **Persistent Storage:** All game data is stored in `cozy_oasis.db`.
- **Full CRUD Operations:** Add, Read, Update, and Delete games directly through the GUI.
- **Custom SQL Analytics:** Automatically calculates average "Comfy Ratings" using SQL aggregate functions.
- **Cozy Aesthetics:** A custom Swing UI featuring a Matcha and Strawberry color palette.

## 🚀 Setup & Installation
To run this project on your local machine, follow these steps:

1. **Clone the Repository:**
   `git clone [Your GitHub Link Here]`
2. **Add the SQLite Driver:**
   Ensure the `sqlite-jdbc-3.45.1.0.jar` is added to your project libraries in IntelliJ (File > Project Structure > Libraries).
3. **Initialize the Database:**
   Run the `database_setup.sql` script within IntelliJ to create the `games` table and populate the 20 sample records.
4. **Launch the GUI:**
   Run `CozyGamesGUI.java`. The data will auto-populate upon startup.

## 📊 Database Schema
The database contains 8 columns to maintain high data integrity:
- `gameID` (Primary Key)
- `title`, `genre`, `developer`, `platform`
- `releaseYear`
- `hoursPlayed`
- `comfyRating`
