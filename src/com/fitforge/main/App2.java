package com.fitforge.main;

import com.fitforge.auth.LoginPanel;
import com.fitforge.auth.RegisterPanel;
import com.fitforge.model.UserManager;
import com.fitforge.ui.*; // This now imports all UI panels
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class App2 {

    JFrame f;
    JPanel mainPanel;
    CardLayout card;
    UserManager userManager;

    public App2() {
        f = new JFrame("FitForge App");
        f.setUndecorated(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(420, 750);
        f.setLocationRelativeTo(null);
        f.setResizable(false);
        f.setBackground(new Color(0, 0, 0, 0));

        JLayeredPane layeredPane = new JLayeredPane();
        f.setContentPane(layeredPane);

        userManager = new UserManager();

        card = new CardLayout();
        mainPanel = new JPanel(card);
        mainPanel.setBounds(30, 20, 368, 717);
        mainPanel.setOpaque(false);

        Image img1 = null, img2 = null, img3 = null, img4 = null, img5 = null;
        try {
            img1 = ImageIO.read(new File("src/resources/images/first.jpg"));
            img2 = ImageIO.read(new File("src/resources/images/second.jpg"));
            img3 = ImageIO.read(new File("src/resources/images/third.jpg"));
            img4 = ImageIO.read(new File("src/resources/images/four.jpg"));
            img5 = ImageIO.read(new File("src/resources/images/fifth.png"));
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }

        Screen1 screen1 = new Screen1(img1, card, mainPanel);
        Screen2 screen2 = new Screen2(img2, card, mainPanel);
        Screen3 screen3 = new Screen3(img3, card, mainPanel);
        Screen4 screen4 = new Screen4(img4, card, mainPanel);
        Screen5 screen5 = new Screen5(img5, card, mainPanel);

        LoginPanel loginPanel = new LoginPanel(userManager, card, mainPanel);
        RegisterPanel registerPanel = new RegisterPanel(userManager, card, mainPanel);
        UserDetailsPanel userDetailsPanel = new UserDetailsPanel(card, mainPanel);
        MainDashboardPanel mainDashboardPanel = new MainDashboardPanel(card, mainPanel); // <-- ADDED

        mainPanel.add(screen1, "screen1");
        mainPanel.add(screen2, "screen2");
        mainPanel.add(screen3, "screen3");
        mainPanel.add(screen4, "screen4");
        mainPanel.add(screen5, "screen5");
        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");
        mainPanel.add(userDetailsPanel, "dashboard");
        mainPanel.add(mainDashboardPanel, "home"); // <-- ADDED

        PhonePanel phonePanel = new PhonePanel();
        phonePanel.setBounds(0, 0, 420, 750);

        layeredPane.add(mainPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(phonePanel, JLayeredPane.PALETTE_LAYER);

        f.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App2());
    }
}
