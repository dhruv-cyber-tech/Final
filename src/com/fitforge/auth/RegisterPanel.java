package com.fitforge.auth;

import com.fitforge.model.UserManager;
import java.awt.*;
import javax.swing.*;

public class RegisterPanel extends JPanel { // Must be JPanel

    private final UserManager userManager;
    private final CardLayout card;
    private final JPanel mainPanel; // Must be JPanel

    public RegisterPanel(UserManager userManager, CardLayout card, JPanel mainPanel) { // Must be JPanel
        this.userManager = userManager;
        this.card = card;
        this.mainPanel = mainPanel;
        setLayout(null);
        setBackground(new Color(69, 51, 181));

        JLabel titleLabel = new JLabel("REGISTER", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(50, 60, 260, 50);
        add(titleLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 12));
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 130, 80, 20);
        add(userLabel);

        JTextField userField = new JTextField();
        userField.setFont(new Font("Arial", Font.PLAIN, 12));
        userField.setBounds(50, 155, 260, 30);
        userField.setBackground(new Color(240, 240, 240));
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 12));
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 195, 80, 20);
        add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.PLAIN, 12));
        passField.setBounds(50, 220, 260, 30);
        passField.setBackground(new Color(240, 240, 240));
        add(passField);

        JLabel confirmLabel = new JLabel("Confirm:");
        confirmLabel.setFont(new Font("Arial", Font.BOLD, 12));
        confirmLabel.setForeground(Color.WHITE);
        confirmLabel.setBounds(50, 260, 80, 20);
        add(confirmLabel);

        JPasswordField confirmField = new JPasswordField();
        confirmField.setFont(new Font("Arial", Font.PLAIN, 12));
        confirmField.setBounds(50, 285, 260, 30);
        confirmField.setBackground(new Color(240, 240, 240));
        add(confirmField);

        JLabel messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        messageLabel.setForeground(new Color(255, 100, 100));
        messageLabel.setBounds(50, 325, 260, 25);
        add(messageLabel);

        JButton registerBtn = new JButton("Register");
        registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
        registerBtn.setBounds(80, 365, 100, 35);
        registerBtn.setBackground(new Color(34, 139, 34));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.setFocusPainted(false);
        registerBtn.setBorderPainted(false);
        registerBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword()).trim();
            String confirm = new String(confirmField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                messageLabel.setText("Please fill all fields!");
            } else if (!password.equals(confirm)) {
                messageLabel.setText("Passwords do not match!");
            } else if (userManager.userExists(username)) {
                messageLabel.setText("Username already exists!");
            } else {
                userManager.addUser(username, password);
                messageLabel.setForeground(new Color(100, 255, 100));
                messageLabel.setText("Registration Successful!");
                userField.setText("");
                passField.setText("");
                confirmField.setText("");
                Timer timer = new Timer(1500, evt -> {
                    card.show(mainPanel, "login");
                    messageLabel.setText("");
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
        add(registerBtn);

        JButton loginBtn = new JButton("Back to Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setBounds(180, 365, 130, 35);
        loginBtn.setBackground(new Color(70, 130, 180));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.setBorderPainted(false);
        loginBtn.addActionListener(e -> card.show(mainPanel, "login"));
        add(loginBtn);
    }
}
