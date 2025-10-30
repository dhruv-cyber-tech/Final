package com.fitforge.ui;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

// Use JPanel (Swing)
public class Screen5 extends JPanel {

    private Image background;

    public Screen5() {
        setLayout(null);
        try {
            // Use getResource with an absolute path
            background = ImageIO.read(getClass().getResource("/resources/images/fifth.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading Screen5 background: " + e.getMessage());
        }

        // Use JLabel (Swing)
        JLabel label = new JLabel("Achievements");
        label.setFont(new Font("Segoe UI", Font.BOLD, 22));
        label.setForeground(Color.WHITE);
        label.setBounds(100, 60, 200, 40);
        add(label);
    }

    // Override paintComponent for Swing
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Call super
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
