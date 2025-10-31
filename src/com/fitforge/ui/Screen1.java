package com.fitforge.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Screen1 extends JPanel {

    private Image background;

    // Helper method to create JLabels
    private JLabel createLabel(String text, int fontSize, boolean bold, Color color, int alignment, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(text, alignment);
        lbl.setFont(new Font("Arial", bold ? Font.BOLD : Font.PLAIN, fontSize));
        lbl.setForeground(color);
        lbl.setBounds(x, y, w, h);
        return lbl;
    }

    // Constructor now takes the image and navigation tools
    public Screen1(Image bgImage, CardLayout card, JPanel mainPanel) {
        this.background = bgImage;
        setLayout(null);
        setBackground(new Color(69, 51, 181));

        // All the UI components from App2.java are now here
        add(createLabel("Welcome to FitForge", 22, true, Color.WHITE, JLabel.CENTER, 20, 410, 320, 30));
        add(createLabel("My name is ChikiBot and I will", 12, false, Color.WHITE, JLabel.CENTER, 20, 450, 320, 30));
        add(createLabel("be your guide on this journey.", 12, false, Color.WHITE, JLabel.CENTER, 20, 470, 320, 30));
        add(createLabel("Shall we Start?", 12, false, Color.WHITE, JLabel.CENTER, 20, 490, 320, 30));

        RoundButtonCanvas next1 = new RoundButtonCanvas("Next ⏭");
        next1.setBounds(150, 540, 60, 60);
        next1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card.show(mainPanel, "screen2"); // Use the passed-in card/panel
            }
        });
        add(next1);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            // Draw image to fit the top part of the panel
            g.drawImage(background, 0, 0, 362, 400, this);
        }
    }
}
