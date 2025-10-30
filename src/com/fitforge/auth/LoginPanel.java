package com.fitforge.auth;

import com.fitforge.model.UserManager;
import java.awt.*;
import javax.swing.*;

public class LoginPanel extends JPanel { // Must be JPanel

    private final UserManager userManager;
    private final CardLayout card;
    private final JPanel mainPanel; // Must be JPanel

    public LoginPanel(UserManager userManager, CardLayout card, JPanel mainPanel) { // Must be JPanel
        this.userManager = userManager;
        this.card = card;
        this.mainPanel = mainPanel;
        setLayout(null);
        setBackground(new Color(69, 51, 181));

        JLabel titleLabel = new JLabel("LOGIN", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(50, 100, 260, 50);
        add(titleLabel);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 170, 80, 25);
        add(userLabel);

        JTextField userField = new JTextField();
        userField.setFont(new Font("Arial", Font.PLAIN, 14));
        userField.setBounds(50, 200, 260, 35);
        userField.setBackground(new Color(240, 240, 240));
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 250, 80, 25);
        add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setFont(new Font("Arial", Font.PLAIN, 14));
        passField.setBounds(50, 280, 260, 35);
        passField.setBackground(new Color(240, 240, 240));
        add(passField);

        JLabel messageLabel = new JLabel("", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        messageLabel.setForeground(new Color(255, 100, 100));
        messageLabel.setBounds(50, 320, 260, 30);
        add(messageLabel);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBounds(80, 365, 100, 35);
        loginButton.setBackground(new Color(34, 139, 34));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            if (userManager.validateUser(username, password)) {
                messageLabel.setForeground(new Color(100, 255, 100));
                messageLabel.setText("Login Successful!");
                Timer timer = new Timer(1000, evt -> card.show(mainPanel, "dashboard"));
                timer.setRepeats(false);
                timer.start();
            } else {
                messageLabel.setForeground(new Color(255, 100, 100));
                messageLabel.setText("Login failed! Redirecting to Register...");
                userField.setText("");
                passField.setText("");
                Timer timer = new Timer(1500, evt -> card.show(mainPanel, "register"));
                timer.setRepeats(false);
                timer.start();
            }
        });
        add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBounds(180, 365, 100, 35);
        registerButton.setBackground(new Color(70, 130, 180));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.addActionListener(e -> {
            card.show(mainPanel, "register");
            messageLabel.setText("");
        });
        add(registerButton);

        JLabel infoLabel = new JLabel("Demo: dhruv/dhruv123", JLabel.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        infoLabel.setForeground(new Color(200, 200, 200));
        infoLabel.setBounds(50, 410, 260, 20);
        add(infoLabel);
    }
}
