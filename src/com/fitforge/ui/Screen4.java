package com.fitforge.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Screen4 extends JPanel {

    private Image background;

    private JLabel createLabel(String text, int fontSize, boolean bold, Color color, int alignment, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(text, alignment);
        lbl.setFont(new Font("Arial", bold ? Font.BOLD : Font.PLAIN, fontSize));
        lbl.setForeground(color);
        lbl.setBounds(x, y, w, h);
        return lbl;
    }

    public Screen4(Image bgImage, CardLayout card, JPanel mainPanel) {
        this.background = bgImage;
        setLayout(null);
        setBackground(new Color(69, 51, 181));

        add(createLabel("Training History", 22, true, Color.WHITE, JLabel.CENTER, 20, 410, 320, 30));
        add(createLabel("See your previous workouts", 12, false, Color.WHITE, JLabel.CENTER, 20, 450, 320, 20));
        add(createLabel("and be proud of your progress.", 12, false, Color.WHITE, JLabel.CENTER, 20, 470, 320, 20));

        RoundButtonCanvas next4 = new RoundButtonCanvas("Next ⏭");
        next4.setBounds(150, 540, 60, 60);
        next4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card.show(mainPanel, "screen5");
            }
        });
        add(next4);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            g.drawImage(background, 0, 0, 362, 400, this);
        }
    }
}
