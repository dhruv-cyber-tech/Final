package com.fitforge.ui;

import java.awt.*;
import javax.swing.*;

public class DashboardPanel extends JPanel {

    public DashboardPanel() {
        setLayout(null);
        setBackground(new Color(50, 50, 50));

        JLabel welcomeLabel = new JLabel("Welcome to FitForge!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setBounds(20, 280, 322, 40);
        add(welcomeLabel);
    }
}
