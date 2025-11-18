package com.fitforge.workout;

import com.fitforge.main.App2;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File; // For rounded corners
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
// --- END NEW IMPORTS ---

public class HomeScreen extends JPanel {

    private App2 app;
    // Theme colors
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
        JPanel cardsPanel = new JPanel(new GridLayout(3, 2, 12, 12)); // 3 rows, 2 cols, 10px gaps
        cardsPanel.setOpaque(false); // Make transparent
        cardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- Pre-load images ---
        // Replace "new-chest-image.jpg" with the name of the file you saved
        BufferedImage chestImg = loadImage("unnamed.jpg");
        BufferedImage legsImg = loadImage("stretching.png");
        BufferedImage backImg = loadImage("back.jpg");
        BufferedImage armsImg = loadImage("strength.png");
        BufferedImage absImg = loadImage("abs.jpg");
        BufferedImage cardioImg = loadImage("yoga.png"); // Placeholder from your project

        // --- Fill the grid with our new custom ImageCardPanel ---
        cardsPanel.add(new ImageCardPanel(app, "Chest Training", chestImg));
        cardsPanel.add(new ImageCardPanel(app, "Stretching", legsImg));     // Was null
        cardsPanel.add(new ImageCardPanel(app, "Back Training", backImg));
        cardsPanel.add(new ImageCardPanel(app, "Strength", armsImg));       // Was null
        cardsPanel.add(new ImageCardPanel(app, "Abs Training", absImg));
        cardsPanel.add(new ImageCardPanel(app, "Yoga", cardioImg));         // Was null

        add(cardsPanel, BorderLayout.CENTER);

        // --- Bottom Navigation Bar (no change) ---
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
        profileButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Profile screen coming soon!");
        });

        bottomNavPanel.add(workoutsButton);
        bottomNavPanel.add(profileButton);

        add(bottomNavPanel, BorderLayout.SOUTH);
    }

    // --- Helper method to load images (no change) ---
    private BufferedImage loadImage(String imageName) {
        try {
            String imagePath = "src/resources/images/" + imageName;
            return ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            System.out.println("Failed to load image: " + imageName);
            return null;
        }
    }

    // --- 2. NEW INNER CLASS ---
    // This class handles all the custom drawing for the rounded cards
    private class ImageCardPanel extends JPanel {

        private BufferedImage backgroundImage;
        private String title;
        private App2 app;
        private final int cornerRadius = 20; // How round the corners are

        public ImageCardPanel(App2 app, String title, BufferedImage image) {
            this.app = app;
            this.title = title;
            this.backgroundImage = image;

            // This is CRITICAL for custom drawing
            setOpaque(false);

            setLayout(new BorderLayout()); // To position text at the bottom
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Create a label for the titles
            if (title != null && !title.equals("Stretching") && !title.equals("Strength") && !title.equals("Yoga")) {

                JLabel titleLabel = new JLabel(title);
                titleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
                titleLabel.setForeground(Color.WHITE);
                titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
                titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                JPanel textPanel = new JPanel(new BorderLayout());

                // Restored semi-transparent background
                textPanel.setBackground(new Color(0, 0, 0, 100));
                textPanel.add(titleLabel, BorderLayout.CENTER);

                add(textPanel, BorderLayout.SOUTH);
            }
            // --- END OF FIX 3 ---

            // Add click listener to the whole panel
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // This now correctly passes "Yoga", "Stretching", etc.
                    if (title != null) {
                        app.showLevelSelector(title);
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            // This method creates the rounded look
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();

            // 1. Enable anti-aliasing for smooth edges
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 2. Create the rounded rectangle shape
            RoundRectangle2D roundedRect = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

            // 3. Set this shape as the "clip", so nothing is drawn outside of it
            g2.setClip(roundedRect);

            // 4. Draw the background image, scaled to fill the panel
            if (backgroundImage != null) {
                g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            } else {
                // Fallback if image fails to load
                g2.setColor(new Color(88, 71, 190));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }

            // 5. (Optional) Draw a thin border
            g2.setColor(new Color(105, 88, 205));
            g2.setStroke(new BasicStroke(2));
            g2.draw(roundedRect);

            g2.dispose();
        }
    }
    // --- END OF NEW INNER CLASS ---
}
