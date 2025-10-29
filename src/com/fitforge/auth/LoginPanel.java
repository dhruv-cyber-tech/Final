package com.fitforge.auth;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.fitforge.model.UserManager;

public class LoginPanel extends Panel {

    private final UserManager userManager;
    private final CardLayout card;
    private final Panel mainPanel;

    public LoginPanel(UserManager userManager, CardLayout card, Panel mainPanel) {
        this.userManager = userManager;
        this.card = card;
        this.mainPanel = mainPanel;
        setLayout(null);
        setBackground(new Color(69, 51, 181));

        // Title
        Label titleLabel = new Label("LOGIN", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(50, 100, 260, 50);
        add(titleLabel);

        // Username
        Label userLabel = new Label("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 14));
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 170, 80, 25);
        add(userLabel);

        TextField userField = new TextField();
        userField.setFont(new Font("Arial", Font.PLAIN, 14));
        userField.setBounds(50, 200, 260, 35);
        userField.setBackground(new Color(240, 240, 240));
        add(userField);

        // Password
        Label passLabel = new Label("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 14));
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 250, 80, 25);
        add(passLabel);

        TextField passField = new TextField();
        passField.setFont(new Font("Arial", Font.PLAIN, 14));
        passField.setEchoChar('*');
        passField.setBounds(50, 280, 260, 35);
        passField.setBackground(new Color(240, 240, 240));
        add(passField);

        // Message label
        Label messageLabel = new Label("", Label.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        messageLabel.setForeground(new Color(255, 100, 100));
        messageLabel.setBounds(50, 320, 260, 30);
        add(messageLabel);

        // Login Button
        Button loginButton = new Button("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBounds(80, 365, 100, 35);
        loginButton.setBackground(new Color(34, 139, 34));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = passField.getText().trim();

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

        // Register Button
        Button registerButton = new Button("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBounds(180, 365, 100, 35);
        registerButton.setBackground(new Color(70, 130, 180));
        registerButton.setForeground(Color.WHITE);
        registerButton.addActionListener(e -> {
            card.show(mainPanel, "register");
            messageLabel.setText("");
        });
        add(registerButton);

        // Demo Info
        Label infoLabel = new Label("Demo: dhruv/dhruv123", Label.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        infoLabel.setForeground(new Color(200, 200, 200));
        infoLabel.setBounds(50, 410, 260, 20);
        add(infoLabel);
    }
}
