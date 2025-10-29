package com.fitforge.auth;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.fitforge.model.UserManager;

public class RegisterPanel extends Panel {

    private final UserManager userManager;
    private final CardLayout card;
    private final Panel mainPanel;

    public RegisterPanel(UserManager userManager, CardLayout card, Panel mainPanel) {
        this.userManager = userManager;
        this.card = card;
        this.mainPanel = mainPanel;
        setLayout(null);
        setBackground(new Color(69, 51, 181));

        // Title
        Label titleLabel = new Label("REGISTER", Label.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(50, 60, 260, 50);
        add(titleLabel);

        // Username field
        Label userLabel = new Label("Username:");
        userLabel.setFont(new Font("Arial", Font.BOLD, 12));
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(50, 130, 80, 20);
        add(userLabel);

        TextField userField = new TextField();
        userField.setFont(new Font("Arial", Font.PLAIN, 12));
        userField.setBounds(50, 155, 260, 30);
        userField.setBackground(new Color(240, 240, 240));
        add(userField);

        // Password
        Label passLabel = new Label("Password:");
        passLabel.setFont(new Font("Arial", Font.BOLD, 12));
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(50, 195, 80, 20);
        add(passLabel);

        TextField passField = new TextField();
        passField.setFont(new Font("Arial", Font.PLAIN, 12));
        passField.setEchoChar('*');
        passField.setBounds(50, 220, 260, 30);
        passField.setBackground(new Color(240, 240, 240));
        add(passField);

        // Confirm password
        Label confirmLabel = new Label("Confirm:");
        confirmLabel.setFont(new Font("Arial", Font.BOLD, 12));
        confirmLabel.setForeground(Color.WHITE);
        confirmLabel.setBounds(50, 260, 80, 20);
        add(confirmLabel);

        TextField confirmField = new TextField();
        confirmField.setFont(new Font("Arial", Font.PLAIN, 12));
        confirmField.setEchoChar('*');
        confirmField.setBounds(50, 285, 260, 30);
        confirmField.setBackground(new Color(240, 240, 240));
        add(confirmField);

        // Message
        Label messageLabel = new Label("", Label.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        messageLabel.setForeground(new Color(255, 100, 100));
        messageLabel.setBounds(50, 325, 260, 25);
        add(messageLabel);

        // Register button
        Button registerBtn = new Button("Register");
        registerBtn.setFont(new Font("Arial", Font.BOLD, 14));
        registerBtn.setBounds(80, 365, 100, 35);
        registerBtn.setBackground(new Color(34, 139, 34));
        registerBtn.setForeground(Color.WHITE);
        registerBtn.addActionListener(e -> {
            String username = userField.getText().trim();
            String password = passField.getText().trim();
            String confirm = confirmField.getText().trim();

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

        // Back to Login
        Button loginBtn = new Button("Back to Login");
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setBounds(180, 365, 130, 35);
        loginBtn.setBackground(new Color(70, 130, 180));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(e -> card.show(mainPanel, "login"));
        add(loginBtn);
    }
}
