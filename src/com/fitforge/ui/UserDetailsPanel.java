package com.fitforge.ui;

import com.fitforge.model.User;
import java.awt.*;
import javax.swing.*;

public class UserDetailsPanel extends JPanel {

    public UserDetailsPanel(CardLayout card, JPanel mainPanel, User userData) {
        setLayout(null);
        setBackground(new Color(69, 51, 181));

        JLabel titleLabel = new JLabel("Tell Us About You", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(50, 20, 260, 50);
        add(titleLabel);

        int labelX = 50;
        int fieldX = 160;
        int startY = 90;
        int gap = 50;

        // --- Name ---
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setBounds(labelX, startY, 100, 25);
        add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameField.setBounds(fieldX, startY, 150, 30);
        nameField.setBackground(new Color(240, 240, 240));
        add(nameField);

        // --- Weight ---
        JLabel weightLabel = new JLabel("Weight (kg):");
        weightLabel.setFont(new Font("Arial", Font.BOLD, 14));
        weightLabel.setForeground(Color.WHITE);
        weightLabel.setBounds(labelX, startY + gap, 100, 25);
        add(weightLabel);

        JTextField weightField = new JTextField();
        weightField.setFont(new Font("Arial", Font.PLAIN, 14));
        weightField.setBounds(fieldX, startY + gap, 150, 30);
        weightField.setBackground(new Color(240, 240, 240));
        add(weightField);

        // --- Height ---
        JLabel heightLabel = new JLabel("Height (cm):");
        heightLabel.setFont(new Font("Arial", Font.BOLD, 14));
        heightLabel.setForeground(Color.WHITE);
        heightLabel.setBounds(labelX, startY + gap * 2, 100, 25);
        add(heightLabel);

        JTextField heightField = new JTextField();
        heightField.setFont(new Font("Arial", Font.PLAIN, 14));
        heightField.setBounds(fieldX, startY + gap * 2, 150, 30);
        heightField.setBackground(new Color(240, 240, 240));
        add(heightField);

        // --- Age ---
        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        ageLabel.setForeground(Color.WHITE);
        ageLabel.setBounds(labelX, startY + gap * 3, 100, 25);
        add(ageLabel);

        JTextField ageField = new JTextField();
        ageField.setFont(new Font("Arial", Font.PLAIN, 14));
        ageField.setBounds(fieldX, startY + gap * 3, 150, 30);
        ageField.setBackground(new Color(240, 240, 240));
        add(ageField);

        // --- Gender ---
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Arial", Font.BOLD, 14));
        genderLabel.setForeground(Color.WHITE);
        genderLabel.setBounds(labelX, startY + gap * 4, 100, 25);
        add(genderLabel);

        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setFont(new Font("Arial", Font.PLAIN, 14));
        maleButton.setForeground(Color.WHITE);
        maleButton.setOpaque(false);
        maleButton.setBounds(fieldX, startY + gap * 4, 70, 25);

        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setFont(new Font("Arial", Font.PLAIN, 14));
        femaleButton.setForeground(Color.WHITE);
        femaleButton.setOpaque(false);
        femaleButton.setBounds(fieldX + 80, startY + gap * 4, 80, 25);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        add(maleButton);
        add(femaleButton);

        // --- Goals ---
        JLabel goalsLabel = new JLabel("Your Goal:");
        goalsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        goalsLabel.setForeground(Color.WHITE);
        goalsLabel.setBounds(labelX, startY + gap * 5, 100, 25);
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
        goalsComboBox.setBounds(labelX, startY + gap * 5 + 35, 260, 35);
        add(goalsComboBox);

        JLabel messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        messageLabel.setForeground(new Color(255, 100, 100));
        messageLabel.setBounds(50, 430, 260, 30);
        add(messageLabel);

        // Submit Button
        JButton submitButton = new JButton("Save & Continue");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBounds(100, 470, 160, 40);
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
                userData.setWeight(weightField.getText().trim());
                userData.setHeight(heightField.getText().trim());
                userData.setAge(ageField.getText().trim());
                userData.setGender(genderGroup.getSelection().getActionCommand());
                userData.setGoal((String) goalsComboBox.getSelectedItem());
                userData.setName(nameField.getText());

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
