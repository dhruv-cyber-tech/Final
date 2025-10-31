package com.fitforge.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Screen5 extends JPanel {

    private Image background;

    private JLabel createLabel(String text, int fontSize, boolean bold, Color color, int alignment, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(text, alignment);
        lbl.setFont(new Font("Arial", bold ? Font.BOLD : Font.PLAIN, fontSize));
        lbl.setForeground(color);
        lbl.setBounds(x, y, w, h);
        return lbl;
    }

    public Screen5(Image bgImage, CardLayout card, JPanel mainPanel) {
        this.background = bgImage;
        setLayout(null);
        setBackground(new Color(69, 51, 181));

        add(createLabel("Ready to Begin?", 22, true, Color.WHITE, JLabel.CENTER, 20, 410, 320, 30));
        add(createLabel("Login or Register to Start", 12, false, Color.WHITE, JLabel.CENTER, 20, 450, 320, 30));

        RoundButtonCanvas next5 = new RoundButtonCanvas("Login");
        next5.setBounds(150, 540, 60, 60);
        next5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card.show(mainPanel, "login");
            }
        });
        add(next5);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, 362, 400, this);
        }
    }
}
