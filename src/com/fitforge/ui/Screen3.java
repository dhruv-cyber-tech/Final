package com.fitforge.ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Screen3 extends JPanel {

    private Image background;

    public Screen3() {
        setLayout(null);
        try {
            // ✅ Load image directly from source folder
            background = ImageIO.read(new File("src/resources/images/third.png"));
        } catch (IOException e) {
            System.out.println("Error loading Screen3 background: " + e.getMessage());
        }

        JLabel label = new JLabel("Track Your Progress");
        label.setFont(new Font("Segoe UI", Font.BOLD, 22));
        label.setForeground(Color.WHITE);
        label.setBounds(70, 60, 250, 40);
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
