package com.fitforge.ui;

import javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Screen3 extends JPanel {
    private Image background;

    public Screen3() {
        setLayout(null);
        try {
            background = ImageIO.read(getClass().getResource("resources/images/third.png"));
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
        if (background != null)
            g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}
