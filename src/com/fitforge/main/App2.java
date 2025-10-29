package com.fitforge.main;

// ADD ALL YOUR IMPORTS HERE
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
// Import your other classes
import com.fitforge.ui.PhonePanel;
import com.fitforge.ui.RoundButtonCanvas;
import com.fitforge.model.UserManager; // or whatever you called it
// ... import all your other screen and panel classes

public class App2 {

    JFrame f;
    Panel mainPanel, screen1, screen2, screen3, screen4, screen5;
    CardLayout card;
    private int pX, pY;

    // ... (Add all your other variables, like usernames/passwords or your UserManager)
    // THIS IS THE CONSTRUCTOR. ALL YOUR UI CODE GOES HERE!
    public App2() {
        f = new JFrame("FitForge App");
        f.setUndecorated(true);
        card = new CardLayout();
        mainPanel = new Panel(card);

        // --- Load all images (FIX THE PATHS HERE!) ---
        MediaTracker tracker = new MediaTracker(f);
        // Use the correct relative path from the "Final" folder
        Image img1 = Toolkit.getDefaultToolkit().getImage("resources/images/first.jpg");
        Image img2 = Toolkit.getDefaultToolkit().getImage("resources/images/second.jpg");
        Image img3 = Toolkit.getDefaultToolkit().getImage("resources/images/third.jpg");
        Image img4 = Toolkit.getDefaultToolkit().getImage("resources/images/four.jpg");
        Image img5 = Toolkit.getDefaultToolkit().getImage("resources/images/fifth.png");
        tracker.addImage(img1, 0);
        // ... (add the rest of the images)
        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        PhonePanel phonePanel = new PhonePanel(mainPanel);

        // --- Add Mouse Listeners for drag ---
        phonePanel.addMouseListener(new MouseAdapter() {

        
        ... });
        phonePanel.addMouseMotionListener(new MouseAdapter() {

        
        ... });

        // --- Add phone panel to frame ---
        f.add(phonePanel);
        f.setSize(420, 750);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // THIS IS THE MOST IMPORTANT LINE YOU ARE MISSING
        f.setVisible(true);
    }

    // ... (Add all your helper methods here, like createLoginPanel(), userExists(), etc.)
    // Your main method stays the same
    public static void main(String[] args) {
        new App2();
    }
}
