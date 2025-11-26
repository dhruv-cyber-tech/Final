// 1. Update package
package com.fitforge.workout;

import com.fitforge.main.App2;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;

public class WorkoutScreen extends JPanel {

    // 3. Change MainApp to App2
    private App2 app;
    private JLabel gifLabel, nameLabel, repsLabel, tipLabel;

    // --- CHANGED ---
    // Swapped JButton for a JLabel to hold the animation
    private JButton nextButton;
    // --- END CHANGED ---

    private JButton backButton;
    private ArrayList<Exercise> exercises;
    private int currentIndex = 0;

    // --- NEW ---
    // This is the classpath folder for your GIFs.
    // The trailing slash is important!
    private String base = "/gifs/";
    // --- END NEW ---

    // 4. Change MainApp to App2 in the constructor
    public WorkoutScreen(App2 app) { // <-- FIXED
        this.app = app;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE); // You can change this to your theme color!

        // Title label
        nameLabel = new JLabel("Exercise 1", SwingConstants.CENTER);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
        nameLabel.setBorder(BorderFactory.createEmptyBorder(25, 10, 10, 10));
        add(nameLabel, BorderLayout.NORTH);

        // GIF label
        gifLabel = new JLabel("", SwingConstants.CENTER);
        gifLabel.setPreferredSize(new Dimension(300, 300));
        add(gifLabel, BorderLayout.CENTER);

        // Bottom section
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        bottomPanel.setBackground(Color.WHITE);

        repsLabel = new JLabel("Reps: ", SwingConstants.CENTER);
        repsLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
        repsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        tipLabel = new JLabel("<html><center>Keep your back straight!</center></html>", SwingConstants.CENTER);
        tipLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        tipLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.setOpaque(false);
        backButton = new JButton("← Back");

        // --- CHANGED ---
        // Create the JLabel for the animated button
        nextButton = new JButton();

        // Load the icon from the classpath (assuming "resources" is a Source Folder)
        java.net.URL nextIconUrl = getClass().getResource(base + "next_animation.gif");

        if (nextIconUrl != null) {
            nextButton.setIcon(new ImageIcon(nextIconUrl));
        } else {
            // Fallback in case the animation is missing
            nextButton.setText("Next ➡️");
            System.out.println("Error: Could not load next_animation.gif from " + base);
        }
        nextButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // --- END CHANGED ---

        btnPanel.add(backButton);
        btnPanel.add(nextButton); // Add the new JLabel button

        bottomPanel.add(repsLabel);
        bottomPanel.add(Box.createVerticalStrut(5));
        bottomPanel.add(tipLabel);
        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(btnPanel);

        add(bottomPanel, BorderLayout.SOUTH);

        // Button actions
        backButton.addActionListener(e -> app.backToHome());

        // --- CHANGED ---
        // Add listener to the new JLabel button
        nextButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (nextButton.isEnabled()) {
                    showNextExercise();
                }
            }
        });
        // --- END CHANGED ---
    }

    // Called when user selects workout level
    public void loadWorkout(String bodyPart, String level) {
        this.exercises = getExercises(bodyPart, level);
        this.currentIndex = 0;
        showExercise(currentIndex);
    }

    /**
     * --- THIS IS THE NEW, CLEANER METHOD ---
     */
    private ArrayList<Exercise> getExercises(String bodyPart, String level) {
        ArrayList<Exercise> list = new ArrayList<>();

        // Use the bodyPart to decide which exercises to add
        switch (bodyPart.toLowerCase()) {

            case "chest training": {
                // 1. Define reps for each level
                String jjReps = "15 reps";
                String lrReps = "12 reps";
                String plankTime = "30 seconds";
                String mcReps = "20 reps";

                if (level.equalsIgnoreCase("Intermediate")) {
                    jjReps = "25 reps"; // Increased
                    lrReps = "18 reps"; // Increased
                    plankTime = "45 seconds"; // Increased
                    mcReps = "30 reps"; // Increased
                } else if (level.equalsIgnoreCase("Advanced")) {
                    jjReps = "35 reps"; // Increased
                    lrReps = "25 reps"; // Increased
                    plankTime = "60 seconds"; // Increased
                    mcReps = "40 reps";
                }

                list.add(new Exercise("Jumping Jacks", jjReps, "Keep your neck relaxed.", base + "chest1.gif"));
                list.add(new Exercise("Incline Pushups", lrReps, "Do not lift lower back off floor.", base + "chest2-resize.gif"));
                list.add(new Exercise("Push Ups", plankTime, "Keep body straight.", base + "chest4.gif"));
                list.add(new Exercise("Bench Dips", mcReps, "Engage core muscles.", base + "benchDips.gif"));
                break;
            }

            case "abs training": {
                // 1. Define reps for each level
                String crunchReps = "20 reps";
                String legRaiseReps = "15 reps";
                String plankTime = "30 seconds";
                String bicycleReps = "20 reps";
                // TODO: Add your other Abs exercise reps here

                if (level.equalsIgnoreCase("Intermediate")) {
                    crunchReps = "30 reps";
                    legRaiseReps = "20 reps";
                    plankTime = "45 seconds";
                    bicycleReps = "30 reps";
                    // TODO: Set intermediate reps for other exercises
                } else if (level.equalsIgnoreCase("Advanced")) {
                    crunchReps = "40 reps";
                    legRaiseReps = "25 reps";
                    plankTime = "60 seconds";
                    bicycleReps = "40 reps";
                    // TODO: Set advanced reps for other exercises
                }

                // 2. Add your REAL Abs exercises here
                // TODO: Replace these examples with your actual exercises
                list.add(new Exercise("Crunches", crunchReps, "Lift shoulders, not neck.", base + "abs2.gif"));
                list.add(new Exercise("Lying Leg Raises", legRaiseReps, "Keep lower back flat.", base + "legRaise.gif"));
                list.add(new Exercise("Plank", plankTime, "Keep body straight.", base + "abs3.gif"));
                list.add(new Exercise("Bicycle Crunches", bicycleReps, "Elbow to opposite knee.", base + "bicycleCrunches.gif"));
                // ... add your other 2 exercises
                break;
            }

            case "back training": {
                // 1. Define reps for each level
                String pullupReps = "8 reps";
                String rowReps = "12 reps";
                String deadliftReps = "10 reps";
                String supermanReps = "15 reps";
                // TODO: Add your other Back exercise reps here

                if (level.equalsIgnoreCase("Intermediate")) {
                    pullupReps = "12 reps";
                    rowReps = "15 reps";
                    deadliftReps = "15 reps";
                    supermanReps = "20 reps";
                } else if (level.equalsIgnoreCase("Advanced")) {
                    pullupReps = "15 reps";
                    rowReps = "20 reps";
                    deadliftReps = "20 reps";
                    supermanReps = "25 reps";
                }

                // 2. Add your REAL Back exercises here
                // TODO: Replace these examples with your actual exercises
                list.add(new Exercise("Pull Ups (Assisted)", pullupReps, "Use a band or machine.", base + "back1.gif"));
                list.add(new Exercise("Dumbbell Rows", rowReps, "Keep back straight.", base + "back2.gif"));
                list.add(new Exercise("Deadlifts", deadliftReps, "Engage your core.", base + "back4.gif"));
                list.add(new Exercise("Superman", supermanReps, "Lift arms and legs together.", base + "jumpingjack.gif"));
                break;
            }

            case "yoga": {
                // 1. Define reps for each level
                String pullupReps = "8 reps";
                String rowReps = "12 reps";
                String deadliftReps = "10 reps";
                String supermanReps = "15 reps";
                // TODO: Add your other Back exercise reps here

                if (level.equalsIgnoreCase("Intermediate")) {
                    pullupReps = "12 reps";
                    rowReps = "15 reps";
                    deadliftReps = "15 reps";
                    supermanReps = "20 reps";
                } else if (level.equalsIgnoreCase("Advanced")) {
                    pullupReps = "15 reps";
                    rowReps = "20 reps";
                    deadliftReps = "20 reps";
                    supermanReps = "25 reps";
                }

                // 2. Add your REAL Back exercises here
                // TODO: Replace these examples with your actual exercises
                list.add(new Exercise("Pull Ups (Assisted)", pullupReps, "Use a band or machine.", base + "abs1.gif"));
                list.add(new Exercise("Dumbbell Rows", rowReps, "Keep back straight.", base + "squat.gif"));
                list.add(new Exercise("Deadlifts", deadliftReps, "Engage your core.", base + "jumpingjack.gif"));
                list.add(new Exercise("Superman", supermanReps, "Lift arms and legs together.", base + "abs1.gif"));
                break;
            }

            case "strength": {
                // 1. Define reps for each level
                String crunchReps = "20 reps";
                String legRaiseReps = "15 reps";
                String plankTime = "30 seconds";
                String bicycleReps = "20 reps";
                // TODO: Add your other Abs exercise reps here

                if (level.equalsIgnoreCase("Intermediate")) {
                    crunchReps = "30 reps";
                    legRaiseReps = "20 reps";
                    plankTime = "45 seconds";
                    bicycleReps = "30 reps";
                    // TODO: Set intermediate reps for other exercises
                } else if (level.equalsIgnoreCase("Advanced")) {
                    crunchReps = "40 reps";
                    legRaiseReps = "25 reps";
                    plankTime = "60 seconds";
                    bicycleReps = "40 reps";
                    // TODO: Set advanced reps for other exercises
                }

                // 2. Add your REAL Abs exercises here
                // TODO: Replace these examples with your actual exercises
                list.add(new Exercise("Shoulder Taps", crunchReps, "Keep Your Core Tight.", base + "strength1.gif"));
                list.add(new Exercise("Squats", legRaiseReps, "Keep Your Hips Low.", base + "strength3.gif"));
                list.add(new Exercise("Russian Twists", plankTime, "Keep Knees at 90 Degrees", base + "strength4.gif"));
                list.add(new Exercise("Reverse Lunges", bicycleReps, "Elbow to opposite knee.", base + "strength2.gif"));
                // ... add your other 2 exercises
                break;
            }

            case "stretching": {
                // 1. Define reps for each level
                String crunchReps = "20 reps";
                String legRaiseReps = "15 reps";
                String plankTime = "30 seconds";
                String bicycleReps = "20 reps";
                // TODO: Add your other Abs exercise reps here

                if (level.equalsIgnoreCase("Intermediate")) {
                    crunchReps = "30 reps";
                    legRaiseReps = "20 reps";
                    plankTime = "45 seconds";
                    bicycleReps = "30 reps";
                    // TODO: Set intermediate reps for other exercises
                } else if (level.equalsIgnoreCase("Advanced")) {
                    crunchReps = "40 reps";
                    legRaiseReps = "25 reps";
                    plankTime = "60 seconds";
                    bicycleReps = "40 reps";
                    // TODO: Set advanced reps for other exercises
                }

                // 2. Add your REAL Abs exercises here
                // TODO: Replace these examples with your actual exercises
                list.add(new Exercise("Crunches", crunchReps, "Lift shoulders, not neck.", base + "str1.gif"));
                list.add(new Exercise("Lying Leg Raises", legRaiseReps, "Keep lower back flat.", base + "str2.gif"));
                list.add(new Exercise("Plank", plankTime, "Keep body straight.", base + "str3.gif"));
                list.add(new Exercise("Bicycle Crunches", bicycleReps, "Elbow to opposite knee.", base + "str4.gif"));
                // ... add your other 2 exercises
                break;
            }// ... Add a 'case' for "Stretching", "Yoga", "Strength" ...

            default:
                // This handles any combination you haven't added yet
                System.out.println("Workout not found for bodyPart: " + bodyPart);
        }

        System.out.println("Exercises loaded: " + list.size());
        return list;
    }

    private void showExercise(int index) {
        if (exercises == null || exercises.isEmpty()) {
            // --- NEW: Safety check for empty lists ---
            nameLabel.setText("No Exercises");
            repsLabel.setText("Reps: 0");
            tipLabel.setText("<html><center>No workout found for this category.</center></html>");
            gifLabel.setIcon(null);
            gifLabel.setText("Please go back");
            nextButton.setEnabled(false);
            nextButton.setVisible(false); // Hide the label
            return;
            // --- END NEW ---
        }

        Exercise ex = exercises.get(index);

        nameLabel.setText(ex.name);
        repsLabel.setText("Reps: " + ex.reps);
        tipLabel.setText("<html><center>" + ex.tip + "</center></html>");

        // This is your current loading method, it's unchanged
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

        // --- CHANGED ---
        // Control the visibility of the new JLabel button
        boolean hasNext = index < exercises.size() - 1;
        nextButton.setEnabled(hasNext);
        nextButton.setVisible(hasNext);
        // --- END CHANGED ---
    }

    private void showNextExercise() {
        if (currentIndex < exercises.size() - 1) {
            currentIndex++;
            showExercise(currentIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Workout Complete! 💪", "Done", JOptionPane.INFORMATION_MESSAGE);
            // --- NEW ---
            // After workout is complete, go back to home screen
            app.backToHome();
            // --- END NEW ---
        }
    }
}
