package com.fitforge.ui;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PhonePanel extends JPanel {

    private Image phoneImage;

    // Constructor that accepts your main panel
    public PhonePanel(JPanel mainPanel) {
        setLayout(null);
        setBackground(Color.BLACK);

        // Load the phone overlay image from the resources folder
        try {
            phoneImage = ImageIO.read(getClass().getResource("resources/images/phone-mockup.png"));
        } catch (IOException e) {
            System.out.println("Error loading phone image: " + e.getMessage());
        }

        // Place your app's main panel inside the phone frame
        mainPanel.setBounds(30, 60, 362, 630);
        add(mainPanel);

    }

    // Draw the background (phone mockup image)
    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        if (phoneImage != null) {
            g.drawImage(phoneImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
