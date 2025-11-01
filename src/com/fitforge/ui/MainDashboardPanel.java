package com.fitforge.ui; // <-- This line must be correct and match the folder path

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class MainDashboardPanel extends JPanel {

    private final Color cardColor = new Color(88, 71, 190);
    private final Color cardHoverColor = new Color(105, 88, 205);
    private final Color mainBgColor = new Color(69, 51, 181);

    public MainDashboardPanel(CardLayout card, JPanel mainPanel) {
        setLayout(null);
        setBackground(mainBgColor);

        // --- Title ---
        JLabel titleLabel = new JLabel("Hello, User!", JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(25, 30, 318, 40);
        add(titleLabel);

        JLabel subTitleLabel = new JLabel("Let's get to work.", JLabel.LEFT);
        subTitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subTitleLabel.setForeground(new Color(200, 200, 200));
        subTitleLabel.setBounds(25, 70, 318, 30);
        add(subTitleLabel);

        // --- Main Stats Panel ---
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(1, 2, 10, 0));
        statsPanel.setBackground(cardColor);
        statsPanel.setBounds(25, 120, 318, 80);
        statsPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(statsPanel);

        // TODO: Replace these with actual user data
        statsPanel.add(createStatCard("Weight", "75 kg"));
        statsPanel.add(createStatCard("Goal", "Build Muscle"));

        // --- Navigation Grid ---
        JPanel navGridPanel = new JPanel(new GridLayout(2, 2, 15, 15));
        navGridPanel.setBackground(null); // Transparent
        navGridPanel.setBounds(25, 220, 318, 300);
        add(navGridPanel);

        navGridPanel.add(createNavCard("Start Workout", "Go ->"));
        navGridPanel.add(createNavCard("My Plan", "View ->"));
        navGridPanel.add(createNavCard("Progress", "Track ->"));
        navGridPanel.add(createNavCard("Settings", "Edit ->"));

        // --- Logout Button ---
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBounds(119, 540, 130, 35);
        logoutButton.setBackground(new Color(255, 100, 100)); // Reddish color for logout
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        logoutButton.addActionListener(e -> {
            // Navigate back to login, clear fields if necessary
            card.show(mainPanel, "login");
        });
        add(logoutButton);
    }

    // Helper method to create a stat display
    private JPanel createStatCard(String title, String value) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false); // Transparent background

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));
        titleLabel.setForeground(new Color(200, 200, 200));
        panel.add(titleLabel, BorderLayout.NORTH);

        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 20));
        valueLabel.setForeground(Color.WHITE);
        panel.add(valueLabel, BorderLayout.CENTER);

        return panel;
    }

    // Helper method to create a clickable navigation card
    private JPanel createNavCard(String title, String subtitle) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(cardColor);
        card.setBorder(new LineBorder(cardColor, 2)); // For hover effect
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(new EmptyBorder(15, 15, 0, 15)); // Padding
        card.add(titleLabel, BorderLayout.CENTER);

        JLabel subtitleLabel = new JLabel(subtitle);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(200, 200, 200));
        subtitleLabel.setBorder(new EmptyBorder(0, 15, 15, 15)); // Padding
        card.add(subtitleLabel, BorderLayout.SOUTH);

        // --- Add hover effect ---
        card.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                card.setBackground(cardHoverColor);
                card.setBorder(new LineBorder(Color.WHITE, 2));
            }

            public void mouseExited(MouseEvent e) {
                card.setBackground(cardColor);
                card.setBorder(new LineBorder(cardColor, 2));
            }

            public void mouseClicked(MouseEvent e) {
                // TODO: Add navigation for each card
                System.out.println("Clicked: " + title);
            }
        });

        return card;
    }
}
