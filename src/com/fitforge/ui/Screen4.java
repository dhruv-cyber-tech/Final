package com.fitforge.ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Screen4 extends JPanel {

    private Image background;

    public Screen4() {
        setLayout(null);
        try {
            // ✅ Load image directly from your source folder
            background = ImageIO.read(new File("src/resources/images/four.png"));
        } catch (IOException e) {
            System.out.println("Error loading Screen4 background: " + e.getMessage());
        }

        JLabel label = new JLabel("Nutrition Tips");
        label.setFont(new Font("Segoe UI", Font.BOLD, 22));
        label.setForeground(Color.WHITE);
        label.setBounds(100, 60, 200, 40);
        add(label);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
