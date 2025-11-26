package com.fitforge.model;

public class User {
    private String weight;
    private String height;
    private String age;
    private String gender;
    private String goal;
    private String name;

    // Default constructor
    public User() {
        this.weight = "0";
        this.height = "0";
        this.age = "0";
        this.gender = "Not specified";
        this.goal = "Stay Fit";
        this.name="null";
    }

    public String getWeight() { return weight; }
    public void setWeight(String weight) { this.weight = weight; }

    public String getHeight() { return height; }
    public void setHeight(String height) { this.height = height; }

    public String getAge() { return age; }
    public void setAge(String age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getGoal() { return goal; }
    public void setGoal(String goal) { this.goal = goal; }

     public String getName() { return name; }
    public void setName(String name) { this.name = name; }


}
