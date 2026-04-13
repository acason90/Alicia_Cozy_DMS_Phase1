import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Main User Interface for the Cozy Oasis Management System.
 * Provides a Matcha and Strawberry themed dashboard for interacting
 * with the game database.
 * manages data via SQLite.
 */
public class CozyGamesGUI extends JFrame {
    private JTable gameTable;
    private DefaultTableModel tableModel;
    private GameManager manager; // The bridge to the Database

    // --- COZY PALETTE ---
    private final Color MATCHA_GREEN = new Color(216, 226, 210);
    private final Color STRAWBERRY_PINK = new Color(255, 225, 225);
    private final Color CREAM_WHITE = new Color(255, 253, 247);
    private final Color TEXT_BROWN = new Color(80, 70, 60);
    private final Font COZY_FONT = new Font("Segoe UI", Font.BOLD, 13);

    /**
     * Initializes the GUI components, sets the theme, and auto-loads
     * data from the database.
     */
    public CozyGamesGUI() {
        manager = new GameManager(); // Initialize the controller

        // --- 1. WINDOW SETUP ---
        setTitle("Cozy Oasis Management System | Phase 4 Database");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 0));
        getContentPane().setBackground(CREAM_WHITE);

        // --- 2. THE DISPLAY (8 COLUMNS) ---
        String[] columns = {"ID", "Title", "Genre", "Developer", "Platform", "Year", "Hours", "Cozy Rating"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; } // Keep data integrity
        };
        gameTable = new JTable(tableModel);

        // Visual Styling
        gameTable.setRowHeight(35);
        gameTable.setSelectionBackground(STRAWBERRY_PINK);
        gameTable.setSelectionForeground(TEXT_BROWN);

        JScrollPane scrollPane = new JScrollPane(gameTable);
        scrollPane.getViewport().setBackground(CREAM_WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // --- 3. SIDEBAR ---
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(220, 600));
        sidePanel.setBackground(MATCHA_GREEN);
        sidePanel.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));

        // Note: Renamed button for Database context
        JButton btnRefresh = createCozyButton("🔄 Refresh DB");
        JButton btnAdd = createCozyButton("➕ Add New Game");
        JButton btnUpdate = createCozyButton("✏️ Update Rating");
        JButton btnDelete = createCozyButton("❌ Remove Game");
        JButton btnAvg = createCozyButton("📊 Average Rating");
        JButton btnExit = createCozyButton("🚪 Exit Program");

        sidePanel.add(btnRefresh); sidePanel.add(Box.createRigidArea(new Dimension(0, 12)));
        sidePanel.add(btnAdd);     sidePanel.add(Box.createRigidArea(new Dimension(0, 12)));
        sidePanel.add(btnUpdate);  sidePanel.add(Box.createRigidArea(new Dimension(0, 12)));
        sidePanel.add(btnDelete);  sidePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        sidePanel.add(btnAvg);     sidePanel.add(Box.createRigidArea(new Dimension(0, 12)));
        sidePanel.add(btnExit);

        add(sidePanel, BorderLayout.WEST);

        // --- 4. ACTION LISTENERS ---
        btnRefresh.addActionListener(e -> refreshFromDatabase());

        btnAdd.addActionListener(e -> showAddForm());

        btnUpdate.addActionListener(e -> handleUpdate());

        btnDelete.addActionListener(e -> {
            int row = gameTable.getSelectedRow();
            if (row != -1) {
                int id = (int) tableModel.getValueAt(row, 0);
                if (manager.removeGameByID(id)) refreshFromDatabase();
            } else {
                JOptionPane.showMessageDialog(this, "Select a game to remove!");
            }
        });

        btnAvg.addActionListener(e -> {
            double avg = manager.calculateAverageRating();
            JOptionPane.showMessageDialog(this, "The Average Cozy Rating is: " + String.format("%.2f", avg));
        });

        btnExit.addActionListener(e -> System.exit(0));

        // Initial Load
        refreshFromDatabase();
    }

    /**
     * Fetches fresh data from the GameManager and refreshes the JTable display.
     */
    private void refreshFromDatabase() {
        tableModel.setRowCount(0);
        List<Game> games = manager.getAllGames();
        for (Game g : games) {
            tableModel.addRow(new Object[]{
                    g.getGameID(), g.getTitle(), g.getGenre(), g.getDeveloper(),
                    g.getPlatform(), g.getReleaseYear(), g.getHoursPlayed(), g.getComfyRating()
            });
        }
    }

    /**
     * Displays a form to gather input and add a new game to the system.
     */
    private void showAddForm() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));
        JTextField[] f = new JTextField[8];
        String[] l = {"ID:", "Title:", "Genre:", "Dev:", "Platform:", "Year:", "Hours:", "Rating:"};

        for (int i = 0; i < 8; i++) {
            panel.add(new JLabel(l[i]));
            f[i] = new JTextField();
            panel.add(f[i]);
        }

        if (JOptionPane.showConfirmDialog(this, panel, "Add Cozy Game", JOptionPane.OK_CANCEL_OPTION) == 0) {
            try {
                Game newGame = new Game(
                        Integer.parseInt(f[0].getText()), f[1].getText(), f[2].getText(),
                        f[3].getText(), f[4].getText(), Integer.parseInt(f[5].getText()),
                        Double.parseDouble(f[6].getText()), Integer.parseInt(f[7].getText())
                );
                manager.addGame(newGame);
                refreshFromDatabase();
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Error: Invalid Input!"); }
        }
    }

    private void handleUpdate() {
        int row = gameTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a game first!");
            return;
        }
        int id = (int) tableModel.getValueAt(row, 0);
        String input = JOptionPane.showInputDialog(this, "Enter new Comfy Rating (1-10):");
        if (input != null) {
            try {
                int newRate = Integer.parseInt(input);
                if (manager.updateRatingByID(id, newRate)) refreshFromDatabase();
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Please enter a valid number."); }
        }
    }

    private JButton createCozyButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(190, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setBackground(Color.WHITE);
        btn.setForeground(TEXT_BROWN);
        btn.setFont(COZY_FONT);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(new Color(180, 190, 170), 1, true));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(STRAWBERRY_PINK); }
            public void mouseExited(java.awt.event.MouseEvent e) { btn.setBackground(Color.WHITE); }
        });
        return btn;
    }

    /**
     * The main entry point for the application.
     * This method launches the Cozy Games Management System by
     * initializing the GUI on the Event Dispatch Thread (EDT) to
     * ensure thread safety and a smooth user experience.
     * * @param args command-line arguments (not utilized in this application)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CozyGamesGUI().setVisible(true));
    }
}