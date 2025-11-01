package com.fitforge.ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PhonePanel extends JPanel {

    private Image phoneImage;

    public PhonePanel() {
        // Make the panel transparent
        setOpaque(false);
        // setBackground(Color.BLACK); // Fully transparent background
        // We don't set layout or background anymore

        try {
            phoneImage = ImageIO.read(new File("src/resources/images/phone-mockup.png"));
        } catch (IOException e) {
            System.out.println("Error loading phone image: " + e.getMessage());
        }
    }

    // ✅ Changed from paintChildren to paintComponent
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Draw the transparent background

        if (phoneImage != null) {
            // Draw the phone image on top
            g.drawImage(phoneImage, 4, 0, getWidth() - 5, getHeight(), this);
        }
    }
}
