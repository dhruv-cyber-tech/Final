package com.fitforge.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Screen3 extends JPanel {

    private Image background;

    private JLabel createLabel(String text, int fontSize, boolean bold, Color color, int alignment, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(text, alignment);
        lbl.setFont(new Font("Arial", bold ? Font.BOLD : Font.PLAIN, fontSize));
        lbl.setForeground(color);
        lbl.setBounds(x, y, w, h);
        return lbl;
    }

    public Screen3(Image bgImage, CardLayout card, JPanel mainPanel) {
        this.background = bgImage;
        setLayout(null);
        setBackground(new Color(69, 51, 181));

        add(createLabel("Workout Registration", 22, true, Color.WHITE, JLabel.CENTER, 20, 410, 320, 30));
        add(createLabel("Explore exercises and register", 12, false, Color.WHITE, JLabel.CENTER, 20, 450, 320, 20));
        add(createLabel("your workouts. Track progress", 12, false, Color.WHITE, JLabel.CENTER, 20, 470, 320, 20));
        add(createLabel("and see improvement.", 12, false, Color.WHITE, JLabel.CENTER, 20, 490, 320, 30));

        RoundButtonCanvas next3 = new RoundButtonCanvas("Next ⏭");
        next3.setBounds(150, 540, 60, 60);
        next3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card.show(mainPanel, "screen4");
            }
        });
        add(next3);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, 362, 400, this);
        }
    }
}
