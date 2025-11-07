package com.fitforge.ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PhonePanel extends JPanel {

    private Image phoneImage;

    public PhonePanel() {
        
        setOpaque(false);
        setBackground(Color.BLACK);

        try {
            phoneImage = ImageIO.read(new File("src/resources/images/phone-mockup.png"));
        } catch (IOException e) {
            System.out.println("Error loading phone image: " + e.getMessage());
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (phoneImage != null) {
           
            g.drawImage(phoneImage, 4, 0, getWidth() - 5, getHeight(), this);
        }
    }
}
