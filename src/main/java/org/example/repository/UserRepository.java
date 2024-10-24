package org.example.repository;

import org.example.model.User;
import org.example.util.CSVUsersReader;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserRepository {

    private ArrayList<User> userArray;

    public UserRepository() throws FileNotFoundException {
        this.userArray = CSVUsersReader.readCsvFileToUsersArray("src/main/resources/Cesaeland_logins.csv");
    }

    public ArrayList<User> getUsersArray() {
        return userArray;
    }

    /**
     * Method to add a new user to the repository
     *
     * @param loginType The type of login (ADMIN/ENGINEER)
     * @param username  The username of the new user
     * @param password  The password of the new user
     * @throws IOException If there is an error during saving
     */
    public void addUser(String loginType, String username, String password) throws IOException {

        User newUser = new User(loginType, username, password);

        userArray.add(newUser);

        saveUserToCsv(newUser);
    }

    /**
     * Method to save the new user to the CSV file
     *
     * @param user The user to save
     * @throws IOException If there is an error during saving
     */
    private void saveUserToCsv(User user) throws IOException {
        try (FileWriter writer = new FileWriter("src/main/resources/Cesaeland_logins.csv", true)) {
            writer.append(String.format("%s,%s,%s\n", user.getLoginType(), user.getUsername(), user.getPassword()));
        }
    }
}
