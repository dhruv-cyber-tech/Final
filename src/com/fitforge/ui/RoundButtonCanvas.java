package com.fitforge.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoundButtonCanvas extends JComponent { // Must be JComponent

    private final String label;
    private Color backgroundColor;
    private Color pressedColor;

    public RoundButtonCanvas(String label) {
        this.label = label;
        this.backgroundColor = new Color(220, 220, 220);
        this.pressedColor = new Color(180, 180, 180);

        setPreferredSize(new Dimension(80, 80));

        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                backgroundColor = pressedColor;
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
    protected void paintComponent(Graphics g) { // Must be paintComponent
        super.paintComponent(g); // Must call super

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(backgroundColor);
        int diameter = Math.min(getWidth(), getHeight());
        g2.fillOval(0, 0, diameter - 1, diameter - 1);

        g2.setColor(Color.DARK_GRAY);
        g2.drawOval(0, 0, diameter - 1, diameter - 1);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 14));
        FontMetrics fm = g2.getFontMetrics();

        int textWidth = fm.stringWidth(label);
        int textAscent = fm.getAscent();

        int x = (diameter - textWidth) / 2;
        int y = (diameter - fm.getHeight()) / 2 + textAscent;

        g2.drawString(label, x, y);
        g2.dispose();
    }
}
