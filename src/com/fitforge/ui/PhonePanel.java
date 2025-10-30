package com.fitforge.ui;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PhonePanel extends JPanel {

    private Image phoneImage;

    public PhonePanel() { // The constructor is now empty
        setLayout(null);
        setBackground(Color.DARK_GRAY);

        try {
            phoneImage = ImageIO.read(getClass().getResource("/resources/images/phone-mockup.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("Error loading phone image: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);

        if (phoneImage != null) {
            g.drawImage(phoneImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
