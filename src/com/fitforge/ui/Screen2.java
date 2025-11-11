package com.fitforge.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Screen2 extends JPanel {

    private final Image background;

    // Helper method to create JLabels
    private JLabel createLabel(String text, int fontSize, boolean bold, Color color, int alignment, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(text, alignment);
        lbl.setFont(new Font("SansSerif", bold ? Font.BOLD : Font.PLAIN, fontSize));
        lbl.setForeground(color);
        lbl.setBounds(x, y, w, h);
        return lbl;
    }

    public Screen2(Image bgImage, CardLayout card, JPanel mainPanel) {
        this.background = bgImage;
        setLayout(null);
        setBackground(new Color(69, 51, 181));

        // --- UPDATED LAYOUT ---
        add(createLabel("Main Screen", 26, true, Color.WHITE, JLabel.CENTER, 20, 420, 320, 30));

        String bodyText = "<html><center>See your main information<br>about your training journey.</center></html>";
        add(createLabel(bodyText, 14, false, new Color(220, 220, 255), JLabel.CENTER, 20, 470, 320, 40));
        // --- END UPDATED LAYOUT ---

        // --- APPLIED THE NEW BUTTON ---
        ImageIcon nextIcon = new ImageIcon("src/resources/images/arrow.png");
        RoundButtonCanvas next2 = new RoundButtonCanvas(nextIcon);

        Color orangeColor = new Color(243, 180, 48); // The orange/yellow
        Color pressedOrange = new Color(220, 160, 40); // A darker shade
        next2.setButtonColors(orangeColor, pressedOrange);

        next2.setBounds(150, 570, 60, 60); // Adjusted Y position
        next2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                card.show(mainPanel, "screen3");
            }
        });
        add(next2);
        // --- END OF CHANGE ---
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (background != null) {
            // Shifted up to hide pagination dots
            g.drawImage(background, 0, -15, 362, 415, this);
        }
    }
}
