package com.fitforge.ui;

import com.fitforge.model.User;
import java.awt.*;
import javax.swing.*;

public class UserDetailsPanel extends JPanel {

    // Accepts UserData to save info
    public UserDetailsPanel(CardLayout card, JPanel mainPanel, User userData) {
        setLayout(null);
        setBackground(new Color(69, 51, 181)); 

        JLabel titleLabel = new JLabel("Tell Us About You", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(50, 40, 260, 50);
        add(titleLabel);

        // --- Weight ---
        JLabel weightLabel = new JLabel("Weight (kg):");
        weightLabel.setFont(new Font("Arial", Font.BOLD, 14));
        weightLabel.setForeground(Color.WHITE);
        weightLabel.setBounds(50, 110, 100, 25);
        add(weightLabel);

        JTextField weightField = new JTextField();
        weightField.setFont(new Font("Arial", Font.PLAIN, 14));
        weightField.setBounds(160, 110, 150, 30);
        weightField.setBackground(new Color(240, 240, 240));
        add(weightField);

        // --- Height ---
        JLabel heightLabel = new JLabel("Height (cm):");
        heightLabel.setFont(new Font("Arial", Font.BOLD, 14));
        heightLabel.setForeground(Color.WHITE);
        heightLabel.setBounds(50, 160, 100, 25);
        add(heightLabel);

        JTextField heightField = new JTextField();
        heightField.setFont(new Font("Arial", Font.PLAIN, 14));
        heightField.setBounds(160, 160, 150, 30);
        heightField.setBackground(new Color(240, 240, 240));
        add(heightField);

        // --- Age ---
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ageLabel.setForeground(Color.WHITE);
        ageLabel.setBounds(50, 210, 100, 25);
        add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setFont(new Font("Arial", Font.PLAIN, 14));
        ageField.setBounds(160, 210, 150, 30);
        ageField.setBackground(new Color(240, 240, 240));
        add(ageField);

        // --- Gender ---
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        genderLabel.setForeground(Color.WHITE);
        genderLabel.setBounds(50, 260, 100, 25);
        add(genderLabel);

        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setFont(new Font("Arial", Font.PLAIN, 14));
        maleButton.setForeground(Color.WHITE);
        maleButton.setBackground(null);
        maleButton.setBounds(155, 260, 70, 25);
        maleButton.setOpaque(false);
        maleButton.setActionCommand("Male");

        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setFont(new Font("Arial", Font.PLAIN, 14));
        femaleButton.setForeground(Color.WHITE);
        femaleButton.setBackground(null);
        femaleButton.setBounds(235, 260, 80, 25);
        femaleButton.setOpaque(false);
        femaleButton.setActionCommand("Female");

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        add(maleButton);
        add(femaleButton);

        // --- Goals ---
        JLabel goalsLabel = new JLabel("Your Goal:");
        goalsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        goalsLabel.setForeground(Color.WHITE);
        goalsLabel.setBounds(50, 310, 100, 25);
        add(goalsLabel);

        String[] goals = {
            "Select a Goal...",
            "Lose Weight",
            "Build Muscle",
            "Improve Endurance",
            "Increase Flexibility",
            "Stay Fit & Healthy"
        };
        JComboBox<String> goalsComboBox = new JComboBox<>(goals);
        goalsComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        goalsComboBox.setBounds(50, 345, 260, 35);
        goalsComboBox.setBackground(new Color(240, 240, 240));
        add(goalsComboBox);

        JLabel messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        messageLabel.setForeground(new Color(255, 100, 100));
        messageLabel.setBounds(50, 400, 260, 30);
        add(messageLabel);

        JButton submitButton = new JButton("Save & Continue");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBounds(100, 450, 160, 40);
        submitButton.setBackground(new Color(34, 139, 34));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFocusPainted(false);
        submitButton.setBorderPainted(false);

        submitButton.addActionListener(e -> {
            if (weightField.getText().trim().isEmpty()
                    || heightField.getText().trim().isEmpty()
                    || ageField.getText().trim().isEmpty()
                    || (genderGroup.getSelection() == null)
                    || goalsComboBox.getSelectedIndex() == 0) {

                messageLabel.setForeground(new Color(255, 100, 100));
                messageLabel.setText("Please fill all fields!");
            } else {
                // --- SAVE DATA TO MODEL ---
                userData.setWeight(weightField.getText().trim());
                userData.setHeight(heightField.getText().trim());
                userData.setAge(ageField.getText().trim());
                userData.setGender(genderGroup.getSelection().getActionCommand());
                userData.setGoal((String) goalsComboBox.getSelectedItem());
                // --------------------------

                messageLabel.setForeground(new Color(100, 255, 100));
                messageLabel.setText("Details Saved!");

                Timer timer = new Timer(1000, evt -> card.show(mainPanel, "home"));
                timer.setRepeats(false);
                timer.start();
            }
        });
        add(submitButton);
    }
}