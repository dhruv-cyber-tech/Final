package com.fitforge.main;

// These imports are now correct, as the classes are JPanel-based
import com.fitforge.auth.LoginPanel;
import com.fitforge.auth.RegisterPanel;
import com.fitforge.model.UserManager;
import com.fitforge.ui.DashboardPanel;
import com.fitforge.ui.PhonePanel;
import com.fitforge.ui.RoundButtonCanvas; // This is now our JComponent version
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class App2 {

    JFrame f;
    JPanel mainPanel; // This is a JPanel
    CardLayout card;
    UserManager userManager;

    // Helper method to create JLabels (since you do it often)
    private JLabel createLabel(String text, int fontSize, boolean bold, Color color, int alignment, int x, int y, int w, int h) {
        JLabel lbl = new JLabel(text, alignment);
        lbl.setFont(new Font("Arial", bold ? Font.BOLD : Font.PLAIN, fontSize));
        lbl.setForeground(color);
        lbl.setBounds(x, y, w, h);
        return lbl;
    }

    public App2() {
        // --- FRAME SETUP ---
        f = new JFrame("FitForge App");
        f.setUndecorated(true); // This stays
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(420, 750); // Set to phone mockup size
        f.setLocationRelativeTo(null);
        f.setResizable(false);

        // --- USER MANAGER ---
        userManager = new UserManager();

        // --- MAIN APP PANEL (with CardLayout) ---
        card = new CardLayout();
        mainPanel = new JPanel(card); // This is a JPanel
        mainPanel.setBounds(30, 60, 362, 630);
        mainPanel.setOpaque(false); // Make it transparent so we can see PhonePanel's bg

        // --- LOAD IMAGES ---
        Image img1 = null, img2 = null, img3 = null, img4 = null, img5 = null;
        try {
            img1 = ImageIO.read(getClass().getResource("C:\\Users\\Dell\\Desktop\\Final\\resources\\images\\first.jpg"));
            img2 = ImageIO.read(getClass().getResource("C:\\Users\\Dell\\Desktop\\Final\\resources\\images\\second.jpg"));
            img3 = ImageIO.read(getClass().getResource("C:\\Users\\Dell\\Desktop\\Final\\resources\\images\\third.jpg"));
            img4 = ImageIO.read(getClass().getResource("C:\\Users\\Dell\\Desktop\\Final\\resources\\images\\four.jpg"));
            img5 = ImageIO.read(getClass().getResource("C:\\Users\\Dell\\Desktop\\Final\\resources\\images\\fifth.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Error loading images: " + e.getMessage());
            // You might want to show a JOptionPane error here
        }

        // --- CREATE SCREENS (Manually, like your pasted code) ---
        // Screen 1
        final Image bg1 = img1;
        JPanel screen1 = new JPanel(null) {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg1 != null) {
                    g.drawImage(bg1, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        screen1.setBackground(new Color(69, 51, 181));
        screen1.add(createLabel("Welcome to FitForge", 22, true, Color.WHITE, JLabel.CENTER, 20, 410, 320, 30));
        screen1.add(createLabel("My name is ChikiBot and I will", 12, false, Color.WHITE, JLabel.CENTER, 20, 450, 320, 30));
        screen1.add(createLabel("be your guide on this journey.", 12, false, Color.WHITE, JLabel.CENTER, 20, 470, 320, 30));
        screen1.add(createLabel("Shall we Start?", 12, false, Color.WHITE, JLabel.CENTER, 20, 490, 320, 30));
        RoundButtonCanvas next1 = new RoundButtonCanvas("Next ⏭");
        next1.setBounds(150, 540, 60, 60);
        next1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                card.show(mainPanel, "screen2");
            }
        });
        screen1.add(next1);

        // Screen 2
        final Image bg2 = img2;
        JPanel screen2 = new JPanel(null) {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg2 != null) {
                    g.drawImage(bg2, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        screen2.setBackground(new Color(69, 51, 181));
        screen2.add(createLabel("Main Screen", 22, true, Color.WHITE, JLabel.CENTER, 20, 410, 320, 30));
        screen2.add(createLabel("See your main information", 12, false, Color.WHITE, JLabel.CENTER, 20, 450, 320, 20));
        screen2.add(createLabel("about your training journey.", 12, false, Color.WHITE, JLabel.CENTER, 20, 470, 320, 20));
        RoundButtonCanvas next2 = new RoundButtonCanvas("Next ⏭");
        next2.setBounds(150, 540, 60, 60);
        next2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                card.show(mainPanel, "screen3");
            }
        });
        screen2.add(next2);

        // Screen 3
        final Image bg3 = img3;
        JPanel screen3 = new JPanel(null) {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg3 != null) {
                    g.drawImage(bg3, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        screen3.setBackground(new Color(69, 51, 181));
        screen3.add(createLabel("Workout Registration", 22, true, Color.WHITE, JLabel.CENTER, 20, 410, 320, 30));
        screen3.add(createLabel("Explore exercises and register", 12, false, Color.WHITE, JLabel.CENTER, 20, 450, 320, 20));
        screen3.add(createLabel("your workouts. Track progress", 12, false, Color.WHITE, JLabel.CENTER, 20, 470, 320, 20));
        screen3.add(createLabel("and see improvement.", 12, false, Color.WHITE, JLabel.CENTER, 20, 490, 320, 30));
        RoundButtonCanvas next3 = new RoundButtonCanvas("Next ⏭");
        next3.setBounds(150, 540, 60, 60);
        next3.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                card.show(mainPanel, "screen4");
            }
        });
        screen3.add(next3);

        // Screen 4
        final Image bg4 = img4;
        JPanel screen4 = new JPanel(null) {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg4 != null) {
                    g.drawImage(bg4, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        screen4.setBackground(new Color(69, 51, 181));
        screen4.add(createLabel("Training History", 22, true, Color.WHITE, JLabel.CENTER, 20, 410, 320, 30));
        screen4.add(createLabel("See your previous workouts", 12, false, Color.WHITE, JLabel.CENTER, 20, 450, 320, 20));
        screen4.add(createLabel("and be proud of your progress.", 12, false, Color.WHITE, JLabel.CENTER, 20, 470, 320, 20));
        RoundButtonCanvas next4 = new RoundButtonCanvas("Next ⏭");
        next4.setBounds(150, 540, 60, 60);
        next4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                card.show(mainPanel, "screen5");
            }
        });
        screen4.add(next4);

        // Screen 5
        final Image bg5 = img5;
        JPanel screen5 = new JPanel(null) {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (bg5 != null) {
                    g.drawImage(bg5, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        screen5.setBackground(new Color(69, 51, 181));
        screen5.add(createLabel("Ready to Begin?", 22, true, Color.WHITE, JLabel.CENTER, 20, 410, 320, 30));
        screen5.add(createLabel("Login or Register to Start", 12, false, Color.WHITE, JLabel.CENTER, 20, 450, 320, 30));
        RoundButtonCanvas next5 = new RoundButtonCanvas("Login");
        next5.setBounds(150, 540, 60, 60);
        next5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                card.show(mainPanel, "login");
            }
        });
        screen5.add(next5);

        // --- CREATE AUTH/DASHBOARD PANELS ---
        // This is now correct! We pass a JPanel (mainPanel) to the constructors
        // of our Swing-based LoginPanel and RegisterPanel.
        LoginPanel loginPanel = new LoginPanel(userManager, card, mainPanel);
        RegisterPanel registerPanel = new RegisterPanel(userManager, card, mainPanel);
        DashboardPanel dashboardPanel = new DashboardPanel();

        // --- ADD ALL PANELS TO CARDLAYOUT ---
        mainPanel.add("screen1", screen1);
        mainPanel.add("screen2", screen2);
        mainPanel.add("screen3", screen3);
        mainPanel.add("screen4", screen4);
        mainPanel.add("screen5", screen5);
        mainPanel.add("login", loginPanel);
        mainPanel.add("register", registerPanel);
        mainPanel.add("dashboard", dashboardPanel);

        // --- PHONE PANEL (The Frame's Content) ---
        PhonePanel phonePanel = new PhonePanel();
        phonePanel.add(mainPanel); // Add the app *inside* the phone

        // Add the phone panel to the main frame
        f.add(phonePanel);
        f.setVisible(true);
    }

    // --- MAIN METHOD ---
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App2());
    }
}
