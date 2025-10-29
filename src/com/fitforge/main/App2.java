package com.fitforge.main;

// ADD ALL YOUR IMPORTS HERE
import com.fitforge.ui.PhonePanel;
import com.fitforge.ui.RoundButtonCanvas;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class App2 {

    JFrame f;
    JPanel mainPanel, screen1, screen2, screen3, screen4, screen5;
    CardLayout card;

    public App2() {
        // --- FRAME SETUP ---
        f = new JFrame("FitForge App");
        f.setUndecorated(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(420, 750);
        f.setLocationRelativeTo(null);
        f.setResizable(false);

        // --- MAIN PANEL ---
        card = new CardLayout();
        mainPanel = new JPanel(card);

        // --- LOAD IMAGES ---
        MediaTracker tracker = new MediaTracker(f);
        Image img1 = Toolkit.getDefaultToolkit().getImage("resources/images/first.jpg");
        Image img2 = Toolkit.getDefaultToolkit().getImage("resources/images/second.jpg");
        Image img3 = Toolkit.getDefaultToolkit().getImage("resources/images/third.jpg");
        Image img4 = Toolkit.getDefaultToolkit().getImage("resources/images/four.jpg");
        Image img5 = Toolkit.getDefaultToolkit().getImage("resources/images/fifth.png");

        tracker.addImage(img1, 0);
        tracker.addImage(img2, 1);
        tracker.addImage(img3, 2);
        tracker.addImage(img4, 3);
        tracker.addImage(img5, 4);

        try {
            tracker.waitForAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // --- CREATE SCREENS ---
        screen1 = createImageScreen(img1, "next1");
        screen2 = createImageScreen(img2, "next2");
        screen3 = createImageScreen(img3, "next3");
        screen4 = createImageScreen(img4, "next4");
        screen5 = createImageScreen(img5, "start");

        // Add all screens to card layout
        mainPanel.add("screen1", screen1);
        mainPanel.add("screen2", screen2);
        mainPanel.add("screen3", screen3);
        mainPanel.add("screen4", screen4);
        mainPanel.add("screen5", screen5);

        // Add first screen
        f.add(mainPanel);
        f.setVisible(true);
    }

    private JPanel createImageScreen(Image img, String action) {
        JPanel p = new JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
            }
        };
        p.setLayout(null);

        RoundButtonCanvas nextButton = new RoundButtonCanvas("Next");
        nextButton.setBounds(150, 650, 100, 60);
        p.add(nextButton);

        nextButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                switch (action) {
                    case "next1":
                        card.show(mainPanel, "screen2");
                        break;
                    case "next2":
                        card.show(mainPanel, "screen3");
                        break;
                    case "next3":
                        card.show(mainPanel, "screen4");
                        break;
                    case "next4":
                        card.show(mainPanel, "screen5");
                        break;
                    case "start":
                        openPhonePanel();
                        break;
                }
            }
        });

        return p;
    }

    // --- HELPER: open the phone panel ---
    private void openPhonePanel() {
        f.getContentPane().removeAll();
        PhonePanel phonePanel = new PhonePanel(mainPanel);
        f.add(phonePanel);
        f.revalidate();
        f.repaint();
    }

    // --- MAIN METHOD ---
    public static void main(String[] args) {
        new App2();
    }
}
