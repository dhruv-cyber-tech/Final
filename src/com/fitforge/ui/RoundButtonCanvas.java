package com.fitforge.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RoundButtonCanvas extends JComponent {

    private String label;
    private ImageIcon icon; 

    private Color backgroundColor;
    private Color pressedColor;
    private Color textColor;
    
    // --- ADDED ---
    // Store the original color to restore it on mouse release
    private Color originalBackgroundColor; 
    // --- END ADDED ---

    /**
     * Constructor for text-based buttons
     */
    public RoundButtonCanvas(String label) {
        this.label = label;
        this.icon = null; 
        commonInit(); 
    }

    /**
     * Constructor for icon-based buttons
     */
    public RoundButtonCanvas(ImageIcon icon) {
        this.icon = icon;
        this.label = null; 
        commonInit(); 
    }

    /**
     * --- NEW METHOD ---
     * Allows you to set the colors after creating the button
     */
    public void setButtonColors(Color background, Color pressed) {
        this.backgroundColor = background;
        this.originalBackgroundColor = background; // Store this as the new default
        this.pressedColor = pressed;
        repaint(); // Redraw with the new color
    }
    
    /**
     * Helper method to set up common properties
     */
    private void commonInit() {
        // Default colors
        this.backgroundColor = Color.WHITE;
        this.originalBackgroundColor = Color.WHITE; // Store the default
        this.pressedColor = new Color(230, 230, 230);
        this.textColor = new Color(69, 51, 181);

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
                // --- MODIFIED ---
                // Restore the original background color, whatever it is
                backgroundColor = originalBackgroundColor; 
                // --- END MODIFIED ---
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 1. Draw the circular background
        g2.setColor(backgroundColor);
        int diameter = Math.min(getWidth(), getHeight());
        g2.fillOval(0, 0, diameter - 1, diameter - 1);

        // 2. Draw the border
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawOval(0, 0, diameter - 1, diameter - 1);

        // 3. Check if we should draw text or an icon
        if (label != null) {
            // Draw text
            g2.setColor(this.textColor);
            g2.setFont(new Font("SansSerif", Font.BOLD, 14));
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(label);
            int textAscent = fm.getAscent();
            int x = (diameter - textWidth) / 2;
            int y = (diameter - fm.getHeight()) / 2 + textAscent;
            g2.drawString(label, x, y);

        } else if (icon != null) {
            // Draw the image, scaled to fit
            Image img = icon.getImage();
            int iconSize = (int) (diameter * 0.60); // 60% of the button size
            int x = (diameter - iconSize) / 2;
            int y = (diameter - iconSize) / 2;
            g2.drawImage(img, x, y, iconSize, iconSize, this);
        }

        g2.dispose();
    }
}