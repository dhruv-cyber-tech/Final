package com.fitforge.ui;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Screen1 extends JPanel {

    private Image background;

    public Screen1() {
        setLayout(null);
        try {
            // Note: The path is different here because it's loaded from App2
            // This class isn't actually used by the final App2, which builds
            // the intro screens manually. But if you were to use it:
            background = ImageIO.read(getClass().getResource("/resources/images/first.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading Screen1 background: " + e.getMessage());
        }

        JLabel title = new JLabel("Welcome to FitForge!");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        title.setBounds(50, 50, 300, 40);
        add(title);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
