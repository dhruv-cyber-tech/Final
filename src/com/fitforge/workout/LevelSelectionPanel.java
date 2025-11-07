package com.fitforge.workout;

import com.fitforge.main.App2;
import java.awt.*;
import javax.swing.*;

public class LevelSelectionPanel extends JPanel {

    private App2 app;
    private String selectedBodyPart;
    private JLabel titleLabel;

    // Theme colors from your app
    private final Color mainBgColor = new Color(69, 51, 181);
    private final Color cardColor = new Color(88, 71, 190);

    public LevelSelectionPanel(App2 app) {
        this.app = app;
        setLayout(null); // Use null layout for absolute positioning
        setBackground(mainBgColor);

        // Title at the top
        titleLabel = new JLabel("Select Level", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(30, 100, 308, 40); // (368 - 30 - 30 = 308)
        add(titleLabel);

        // Add the three level buttons
        add(createLevelButton("Beginner", new Color(34, 139, 34), 200));
        add(createLevelButton("Intermediate", new Color(70, 130, 180), 260));
        add(createLevelButton("Advanced", new Color(255, 100, 100), 320));

        // Back button to return to home
        JButton backButton = new JButton("← Back to Workouts");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(cardColor);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setBounds(94, 450, 180, 40); // Centered
        backButton.addActionListener(e -> app.backToHome()); // Uses App2's method
        add(backButton);
    }

    // Helper to create the styled level buttons
    private JButton createLevelButton(String level, Color color, int yPos) {
        JButton button = new JButton(level);
        button.setFont(new Font("SansSerif", Font.BOLD, 18));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBounds(84, yPos, 200, 45); // Centered

        // This is where the navigation happens
        button.addActionListener(e -> {
            app.openWorkoutWindow(selectedBodyPart, level);
        });
        return button;
    }

    // This method is called by App2 to tell this panel which workout was chosen
    public void setBodyPart(String bodyPart) {
        this.selectedBodyPart = bodyPart;
        titleLabel.setText(bodyPart); // Update the title
    }
}
