package com.fitforge.ui;

import com.fitforge.main.App2;
import com.fitforge.model.User;
import java.awt.*;
import java.io.File;
import java.text.DecimalFormat;
import javax.imageio.ImageIO;
import javax.swing.*;

public class profileScreen extends JPanel {

    private App2 app;
    private User userData;
    private JLabel nameLabel, ageLabel, genderLabel, weightLabel, heightLabel, goalLabel;
    private JLabel bmiValueLabel, bmiCategoryLabel;
    private JPanel bmiPanel;

    public profileScreen(App2 app, User userData) {
        this.app = app;
        this.userData = userData;
        setLayout(null);
        setBackground(new Color(69, 51, 181));

        // --- Header ---
        JLabel title = new JLabel("My Profile", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        title.setBounds(0, 40, 420, 40);
        add(title);

        // --- Avatar (Using RoundButtonCanvas for circular image) ---
        try {
            // Using one of your existing images as the avatar
            ImageIcon avatarIcon = new ImageIcon(ImageIO.read(new File("src/resources/images/unnamed.jpg"))); 
            RoundButtonCanvas avatar = new RoundButtonCanvas(avatarIcon);
            avatar.setBounds(145, 90, 130, 130); // Centered
            avatar.setEnabled(false); // Purely visual
            add(avatar);
        } catch (Exception e) {
            System.out.println("Avatar not found");
        }

        // --- Details Panel ---
        JPanel detailsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        detailsPanel.setBounds(40, 240, 340, 150);
        detailsPanel.setOpaque(false);

        nameLabel = createInfoLabel("Name: User"); // Placeholder name
        ageLabel = createInfoLabel("Age: --");
        genderLabel = createInfoLabel("Gender: --");
        weightLabel = createInfoLabel("Weight: -- kg");
        heightLabel = createInfoLabel("Height: -- cm");
        
        detailsPanel.add(nameLabel);
        detailsPanel.add(ageLabel);
        detailsPanel.add(genderLabel);
        detailsPanel.add(heightLabel);
        detailsPanel.add(weightLabel);
        add(detailsPanel);

        // --- Goal Badge ---
        goalLabel = new JLabel("Goal: Stay Fit", SwingConstants.CENTER);
        goalLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        goalLabel.setForeground(new Color(255, 215, 0)); // Gold color
        goalLabel.setBounds(0, 400, 420, 30);
        add(goalLabel);

        // --- BMI Section ---
        bmiPanel = new JPanel(null);
        bmiPanel.setBounds(40, 440, 340, 100);
        bmiPanel.setBackground(new Color(255, 255, 255, 30)); // Transparent white
        bmiPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 1, true));
        
        JLabel bmiTitle = new JLabel("BMI Calculator");
        bmiTitle.setForeground(Color.WHITE);
        bmiTitle.setFont(new Font("SansSerif", Font.BOLD, 14));
        bmiTitle.setBounds(10, 10, 150, 20);
        bmiPanel.add(bmiTitle);

        bmiValueLabel = new JLabel("0.0", SwingConstants.RIGHT);
        bmiValueLabel.setForeground(Color.WHITE);
        bmiValueLabel.setFont(new Font("SansSerif", Font.BOLD, 36));
        bmiValueLabel.setBounds(180, 10, 140, 40);
        bmiPanel.add(bmiValueLabel);

        bmiCategoryLabel = new JLabel("Unknown", SwingConstants.RIGHT);
        bmiCategoryLabel.setForeground(new Color(220, 220, 220));
        bmiCategoryLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        bmiCategoryLabel.setBounds(180, 50, 140, 20);
        bmiPanel.add(bmiCategoryLabel);

        add(bmiPanel);

        // --- Back Button ---
        JButton backButton = new JButton("← Back to Home");
        backButton.setBounds(110, 650, 200, 40);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(88, 71, 190));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.addActionListener(e -> app.backToHome());
        add(backButton);
    }

    private JLabel createInfoLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl.setForeground(Color.WHITE);
        return lbl;
    }

    // This method is called by App2 right before showing this screen
    public void refreshProfile() {
        if (userData != null) {
            nameLabel.setText("Name: " + userData.getName());
            ageLabel.setText("Age: " + userData.getAge());
            genderLabel.setText("Gender: " + userData.getGender());
            weightLabel.setText("Weight: " + userData.getWeight() + " kg");
            heightLabel.setText("Height: " + userData.getHeight() + " cm");
            goalLabel.setText("Goal: " + userData.getGoal());
            
            
            calculateBMI();
        }
    }

    private void calculateBMI() {
        try {
            double w = Double.parseDouble(userData.getWeight());
            double hCm = Double.parseDouble(userData.getHeight());
            
            if (hCm > 0) {
                double hM = hCm / 100.0; // convert cm to meters
                double bmi = w / (hM * hM);
                
                DecimalFormat df = new DecimalFormat("#.#");
                bmiValueLabel.setText(df.format(bmi));
                
                String category;
                Color color;
                
                if (bmi < 18.5) {
                    category = "Underweight";
                    color = new Color(135, 206, 250); // Light Blue
                } else if (bmi < 24.9) {
                    category = "Normal Weight";
                    color = new Color(144, 238, 144); // Light Green
                } else if (bmi < 29.9) {
                    category = "Overweight";
                    color = new Color(255, 165, 0); // Orange
                } else {
                    category = "Obese";
                    color = new Color(255, 99, 71); // Red
                }
                
                bmiCategoryLabel.setText(category);
                bmiCategoryLabel.setForeground(color);
            }
        } catch (NumberFormatException e) {
            bmiValueLabel.setText("--");
            bmiCategoryLabel.setText("Invalid Data");
        }
    }
}