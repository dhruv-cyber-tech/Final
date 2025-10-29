package ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class PhonePanel extends JPanel {

    private Image phoneImage;

    public PhonePanel(Panel mainPanel) {
        setLayout(null);
        setBackground(Color.BLACK);

        try {
            phoneImage = ImageIO.read(new File("images/phone-mockup.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainPanel.setBounds(30, 60, 362, 630);
        add(mainPanel);
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);
        if (phoneImage != null) {
            g.drawImage(phoneImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
