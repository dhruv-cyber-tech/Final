package com.fitforge.model;

public class UserManager {

    // Arrays to store usernames and passwords
    private String[] usernames;
    private String[] passwords;
    private int userCount;

    public UserManager() {
        // Fixed size arrays (you can increase if needed)
        usernames = new String[20];
        passwords = new String[20];
        userCount = 0;

        // Default demo users
        usernames[userCount] = "dhruv";
        passwords[userCount++] = "dhruv123";

        usernames[userCount] = "devesh";
        passwords[userCount++] = "devesh123";

        usernames[userCount] = "manthan";
        passwords[userCount++] = "manthan123";

        usernames[userCount] = "viraj";
        passwords[userCount++] = "viraj123";
    }

    // Validate username/password combination
    public boolean validateUser(String username, String password) {
        for (int i = 0; i < userCount; i++) {
            if (usernames[i].equals(username) && passwords[i].equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Check if a username already exists
    public boolean userExists(String username) {
        for (int i = 0; i < userCount; i++) {
            if (usernames[i].equals(username)) {
                return true;
            }
        }
        return false;
    }

    // Add a new user
    public void addUser(String username, String password) {
        if (userCount < usernames.length) {
            usernames[userCount] = username;
            passwords[userCount] = password;
            userCount++;
        } else {
            System.out.println("User list is full!");
        }
    }

    // Optional: for debugging
    public void printAllUsers() {
        System.out.println("Registered Users:");
        for (int i = 0; i < userCount; i++) {
            System.out.println(" - " + usernames[i]);
        }
    }
}
