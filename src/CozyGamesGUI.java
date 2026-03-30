import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Scanner;

/**
 * Class: CozyGamesGUI
 * Description: Phase 3 of the DMS Project. Implements a full GUI for managing
 * a "Cozy Games" library with CRUD operations and custom calculations.
 */
public class CozyGamesGUI extends JFrame {
    private JTable gameTable;
    private DefaultTableModel tableModel;

    // --- COZY PALETTE ---
    private final Color MATCHA_GREEN = new Color(216, 226, 210);
    private final Color STRAWBERRY_PINK = new Color(255, 225, 225);
    private final Color CREAM_WHITE = new Color(255, 253, 247);
    private final Color TEXT_BROWN = new Color(80, 70, 60);
    private final Font COZY_FONT = new Font("Segoe UI", Font.BOLD, 13);

    public CozyGamesGUI() {
        // --- 1. WINDOW SETUP ---
        setTitle("Cozy Games Management System | Phase 3 Final");
        setSize(1100, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 0));
        getContentPane().setBackground(CREAM_WHITE);

        // --- 2. THE DISPLAY (8 COLUMNS) ---
        //added hours played for better function
        String[] columns = {"ID", "Title", "Genre", "Developer", "Platform", "Release Year", "Hours Played", "Cozy Rating"};
        tableModel = new DefaultTableModel(columns, 0);
        gameTable = new JTable(tableModel);

        gameTable.setRowHeight(35);
        gameTable.setFont(new Font("SansSerif", Font.PLAIN, 14));
        gameTable.setSelectionBackground(STRAWBERRY_PINK);
        gameTable.setSelectionForeground(TEXT_BROWN);

        JScrollPane scrollPane = new JScrollPane(gameTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 20));
        scrollPane.getViewport().setBackground(CREAM_WHITE);
        add(scrollPane, BorderLayout.CENTER);

        // --- 3. SIDEBAR ---
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        sidePanel.setPreferredSize(new Dimension(220, 600));
        sidePanel.setBackground(MATCHA_GREEN);
        sidePanel.setBorder(BorderFactory.createEmptyBorder(30, 15, 30, 15));

        JButton btnLoad = createCozyButton("📂 Load Text File");
        JButton btnAdd = createCozyButton("➕ Add New Game");
        JButton btnUpdate = createCozyButton("✏️ Update Selected");
        JButton btnDelete = createCozyButton("❌ Remove Game");
        JButton btnCalc = createCozyButton("📊 Calc Cozy Score");
        JButton btnExit = createCozyButton("🚪 Exit Program");

        sidePanel.add(btnLoad);   sidePanel.add(Box.createRigidArea(new Dimension(0, 12)));
        sidePanel.add(btnAdd);    sidePanel.add(Box.createRigidArea(new Dimension(0, 12)));
        sidePanel.add(btnUpdate); sidePanel.add(Box.createRigidArea(new Dimension(0, 12)));
        sidePanel.add(btnDelete); sidePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        sidePanel.add(btnCalc);   sidePanel.add(Box.createRigidArea(new Dimension(0, 12)));
        sidePanel.add(btnExit);

        add(sidePanel, BorderLayout.WEST);

        // --- 4. ACTION LISTENERS ---
        btnLoad.addActionListener(e -> handleLoad());
        btnAdd.addActionListener(e -> showGameForm("Add Game", null));
        btnUpdate.addActionListener(e -> {
            int row = gameTable.getSelectedRow();
            if (row != -1) showGameForm("Update Game", row);
            else JOptionPane.showMessageDialog(this, "Select a game first!");
        });
        btnDelete.addActionListener(e -> {
            int row = gameTable.getSelectedRow();
            if (row != -1 && JOptionPane.showConfirmDialog(this, "Delete record?") == 0) tableModel.removeRow(row);
        });
        btnCalc.addActionListener(e -> handleCalculation());
        btnExit.addActionListener(e -> System.exit(0));
    }

    private void handleLoad() {
        JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            tableModel.setRowCount(0);
            try (Scanner s = new Scanner(fc.getSelectedFile())) {
                while (s.hasNextLine()) {
                    String[] data = s.nextLine().split(",");
                    if (data.length == 8) tableModel.addRow(data); // Expecting 8 pieces now
                }
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Error loading file."); }
        }
    }

    private void handleCalculation() {
        int row = gameTable.getSelectedRow();
        if (row != -1) {
            try {
                // Index 6 = Hours Played, Index 7 = Cozy Rating
                double hours = Double.parseDouble(tableModel.getValueAt(row, 6).toString());
                double rating = Double.parseDouble(tableModel.getValueAt(row, 7).toString());
                double score = (hours * rating) / 10.0;
                JOptionPane.showMessageDialog(this, "Cozy Impact Score: " + String.format("%.2f", score));
            } catch (Exception ex) { JOptionPane.showMessageDialog(this, "Numeric data error in Columns 7 or 8."); }
        }
    }

    private void showGameForm(String title, Integer rowToUpdate) {
        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10)); // 8 rows for 8 fields
        panel.setBackground(CREAM_WHITE);
        JTextField[] fields = new JTextField[8];
        String[] labels = {"ID:", "Title:", "Genre:", "Developer:", "Platform:", "Year:", "Hours:", "Rating:"};

        for (int i = 0; i < 8; i++) {
            panel.add(new JLabel(labels[i]));
            fields[i] = new JTextField();
            if (rowToUpdate != null) fields[i].setText(tableModel.getValueAt(rowToUpdate, i).toString());
            panel.add(fields[i]);
        }

        if (JOptionPane.showConfirmDialog(this, panel, title, JOptionPane.OK_CANCEL_OPTION) == 0) {
            String[] data = new String[8];
            for (int i = 0; i < 8; i++) data[i] = fields[i].getText();
            if (rowToUpdate == null) tableModel.addRow(data);
            else for (int i = 0; i < 8; i++) tableModel.setValueAt(data[i], rowToUpdate, i);
        }
    }

    private JButton createCozyButton(String text) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(190, 40));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setBackground(Color.WHITE);
        btn.setForeground(TEXT_BROWN);
        btn.setFont(COZY_FONT);
        btn.setBorder(BorderFactory.createLineBorder(new Color(180, 190, 170), 1, true));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(STRAWBERRY_PINK); }
            public void mouseExited(java.awt.event.MouseEvent e) { btn.setBackground(Color.WHITE); }
        });
        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CozyGamesGUI().setVisible(true));
    }
}