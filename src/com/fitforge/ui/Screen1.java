package com.fitforge.ui;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Screen1 extends JPanel {
    private Image background;

    public Screen1() {
        setLayout(null);
        try {
            background = ImageIO.read(getClass().getResource("resources/images/first.png"));
        } catch (IOException e) {
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
        if (background != null)
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
