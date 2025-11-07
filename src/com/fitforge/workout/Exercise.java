package com.fitforge.workout; // <-- THE FIX

class Exercise {

    String name;
    String reps;
    String tip;
    String gifPath;

    Exercise(String name, String reps, String tip, String gifPath) {
        this.name = name;
        this.reps = reps;
        this.tip = tip;
        this.gifPath = gifPath;
    }
}
