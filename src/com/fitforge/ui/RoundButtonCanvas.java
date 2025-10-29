package com.fitforge.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoundButtonCanvas extends Canvas {

    private String label;
    private Color backgroundColor;

    public RoundButtonCanvas(String label) {
        this.label = label;
        this.backgroundColor = new Color(220, 220, 220);
        setSize(80, 80);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                backgroundColor = new Color(180, 180, 180);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                backgroundColor = new Color(220, 220, 220);
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(backgroundColor);
        g.fillOval(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);

        g.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(label);
        int textHeight = fm.getAscent();

        g.drawString(label, (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 3);
    }
}
