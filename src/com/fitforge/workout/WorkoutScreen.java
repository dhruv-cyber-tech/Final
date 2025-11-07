// 1. Update package
package com.fitforge.workout;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
// 2. Add import for App2
import com.fitforge.main.App2;

public class WorkoutScreen extends JPanel {

    // 3. Change MainApp to App2
    private App2 app;
    private JLabel gifLabel, nameLabel, repsLabel, tipLabel;
    private JButton nextButton, backButton;
    private ArrayList<Exercise> exercises;
    private int currentIndex = 0;

    // 4. Change MainApp to App2 in the constructor
    public WorkoutScreen(App2 app) { // <-- FIXED
        this.app = app;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // You can change this to your theme color!

        // Title label
        nameLabel = new JLabel("Exercise 1", SwingConstants.CENTER);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(nameLabel, BorderLayout.NORTH);

        // GIF label
        gifLabel = new JLabel("", SwingConstants.CENTER);
        gifLabel.setPreferredSize(new Dimension(300, 300));
        add(gifLabel, BorderLayout.CENTER);

        // Bottom section
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        bottomPanel.setBackground(Color.WHITE); // You can change this to your theme color!

        repsLabel = new JLabel("Reps: ", SwingConstants.CENTER);
        repsLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        repsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        tipLabel = new JLabel("<html><center>Keep your back straight!</center></html>", SwingConstants.CENTER);
        tipLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        tipLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setOpaque(false);
        backButton = new JButton("← Back");
        nextButton = new JButton("Next ➡️");

        btnPanel.add(backButton);
        btnPanel.add(nextButton);

        bottomPanel.add(repsLabel);
        bottomPanel.add(Box.createVerticalStrut(5));
        bottomPanel.add(tipLabel);
        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(btnPanel);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        backButton.addActionListener(e -> app.backToHome());
        nextButton.addActionListener(e -> showNextExercise());
    }

    // Called when user selects workout level
    public void loadWorkout(String bodyPart, String level) {
        this.exercises = getExercises(bodyPart, level);
        this.currentIndex = 0;
        showExercise(currentIndex);
    }

    private ArrayList<Exercise> getExercises(String bodyPart, String level) {
        ArrayList<Exercise> list = new ArrayList<>();

        // --- MODIFIED LINE ---
        // 1. This path is now a CLASSPATH path.
        // The leading "/" means "start at the root of the src folder".
        String base = "/resources/gifs";
        // --- END MODIFICATION ---
        if (level.equalsIgnoreCase("Beginner") && bodyPart.equalsIgnoreCase("Chest Training")) {
            // These paths are now correct classpath paths
            list.add(new Exercise("Jumping Jacks", "15 reps", "Keep your neck relaxed.", base + "/jumpingjack.gif"));
            list.add(new Exercise("Leg Raise", "12 reps", "Do not lift lower back off floor.", base + "/chest2.gif"));
            list.add(new Exercise("Plank", "30 seconds", "Keep body straight.", base + "/chest3.gif"));
            list.add(new Exercise("Mountain Climbers", "20 reps", "Engage core muscles.", base + "/chest4.gif"));
        } else if (level.equalsIgnoreCase("Intermediate") && bodyPart.equalsIgnoreCase("Chest Training")) {
            // list.add(new Exercise("Incline Push-Ups", "12 reps", "Use a chair or bench.", base + "/inclinepushup.gif"));
            // list.add(new Exercise("Chest Dips", "10 reps", "Keep elbows in.", base + "/chestdip.gif"));

            // list.add(new Exercise("Wide Arm Push-Ups", "10 reps", "Hands wider than shoulders.", base + "/widepushup.gif"));
            // list.add(new Exercise("Plank to Push-Up", "12 reps", "Alternate positions.", base + "/plankpushup.gif"));
        } else if (level.equalsIgnoreCase("Beginner") && bodyPart.equalsIgnoreCase("Legs Training")) {
            // list.add(new Exercise("Squats", "15 reps", "Keep knees behind toes.", base + "/squat.gif"));

            // list.add(new Exercise("Lunges", "10 reps per leg", "Keep upper body straight.", base + "/lunge.gif"));
            // list.add(new Exercise("Calf Raises", "20 reps", "Go slow up and down.", base + "/calfraise.gif"));
            // list.add(new Exercise("Wall Sit", "30 sec", "Keep back flat against wall.", base + "/wallsit.gif"));
        }
        System.out.println("Exercises loaded: " + list.size());
        return list;
    }

    private void showExercise(int index) {
        if (exercises == null || exercises.isEmpty()) {
            return;
        }
        Exercise ex = exercises.get(index);

        nameLabel.setText(ex.name);
        repsLabel.setText("Reps: " + ex.reps);
        tipLabel.setText("<html><center>" + ex.tip + "</center></html>");

        // --- MODIFIED SECTION ---
        // 2. We now use getClass().getResource() to load the image
        // This is the most reliable way to load resources from your classpath.
        java.net.URL gifURL = getClass().getResource(ex.gifPath);

        if (gifURL != null) {
            System.out.println("Loading: " + gifURL.getPath());
            gifLabel.setIcon(new ImageIcon(gifURL));
            gifLabel.setText("");
        } else {
            gifLabel.setIcon(null);
            gifLabel.setText("GIF not found!");
            System.out.println("Failed to load (as resource): " + ex.gifPath);
        }
        // --- END MODIFICATION ---

        nextButton.setEnabled(index < exercises.size() - 1);
    }

    private void showNextExercise() {
        if (currentIndex < exercises.size() - 1) {
            currentIndex++;
            showExercise(currentIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Workout Complete! 💪", "Done", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
