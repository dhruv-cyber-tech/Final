package com.fitforge.workout;

import com.fitforge.main.App2;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class HomeScreen extends JPanel {

    private App2 app;
    private final Color mainBgColor = new Color(69, 51, 181);
    private final Color navBarColor = new Color(40, 30, 100);
    private final Color navButtonActive = new Color(120, 100, 220);

    public HomeScreen(App2 app) {
        this.app = app;
        setLayout(new BorderLayout());
        setBackground(mainBgColor);

        JLabel title = new JLabel("Workouts", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // --- 3x2 grid ---
        JPanel cardsPanel = new JPanel(new GridLayout(3, 2, 12, 12));
        cardsPanel.setOpaque(false);
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        BufferedImage chestImg = loadImage("unnamed.jpg");
        BufferedImage legsImg = loadImage("stretching.png");
        BufferedImage backImg = loadImage("back.jpg");
        BufferedImage armsImg = loadImage("strength.png");
        BufferedImage absImg = loadImage("abs.jpg");
        BufferedImage cardioImg = loadImage("yoga.png");

        cardsPanel.add(new ImageCardPanel(app, "Chest Training", chestImg));
        cardsPanel.add(new ImageCardPanel(app, "Stretching", legsImg));
        cardsPanel.add(new ImageCardPanel(app, "Back Training", backImg));
        cardsPanel.add(new ImageCardPanel(app, "Strength", armsImg));
        cardsPanel.add(new ImageCardPanel(app, "Abs Training", absImg));
        cardsPanel.add(new ImageCardPanel(app, "Yoga", cardioImg));

        add(cardsPanel, BorderLayout.CENTER);

        // --- Bottom Navigation Bar ---
        JPanel bottomNavPanel = new JPanel(new GridLayout(1, 2));
        bottomNavPanel.setBackground(navBarColor);
        bottomNavPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        JButton workoutsButton = new JButton("Workouts");
        workoutsButton.setBackground(navButtonActive);
        workoutsButton.setForeground(Color.WHITE);
        workoutsButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        workoutsButton.setFocusPainted(false);
        workoutsButton.setBorder(BorderFactory.createLineBorder(navBarColor, 2));

        JButton profileButton = new JButton("Profile");
        profileButton.setBackground(navBarColor);
        profileButton.setForeground(Color.GRAY);
        profileButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        profileButton.setFocusPainted(false);
        profileButton.setBorder(BorderFactory.createLineBorder(navBarColor, 2));
        
        // --- CHANGED: Now opens the Profile Screen ---
        profileButton.addActionListener(e -> {
            app.showProfile();
        });

        bottomNavPanel.add(workoutsButton);
        bottomNavPanel.add(profileButton);

        add(bottomNavPanel, BorderLayout.SOUTH);
    }

    private BufferedImage loadImage(String imageName) {
        try {
            String imagePath = "src/resources/images/" + imageName;
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println("Failed to load image: " + imageName);
            return null;
        }
    }

    private class ImageCardPanel extends JPanel {

        private BufferedImage backgroundImage;
        private String title;
        private App2 app;
        private final int cornerRadius = 20;

        public ImageCardPanel(App2 app, String title, BufferedImage image) {
            this.app = app;
            this.title = title;
            this.backgroundImage = image;

            setOpaque(false);
            setLayout(new BorderLayout());
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            if (title != null && !title.equals("Stretching") && !title.equals("Strength") && !title.equals("Yoga")) {
                JLabel titleLabel = new JLabel(title);
                titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
                titleLabel.setForeground(Color.WHITE);
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                JPanel textPanel = new JPanel(new BorderLayout());
                textPanel.setBackground(new Color(0, 0, 0, 100));
                textPanel.add(titleLabel, BorderLayout.CENTER);

                add(textPanel, BorderLayout.SOUTH);
            }

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (title != null) {
                        app.showLevelSelector(title);
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
            g2.setClip(roundedRect);

            if (backgroundImage != null) {
                g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            } else {
                g2.setColor(new Color(88, 71, 190));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }

            g2.setColor(new Color(105, 88, 205));
            g2.setStroke(new BasicStroke(2));
            g2.draw(roundedRect);
            g2.dispose();
        }
    }
}