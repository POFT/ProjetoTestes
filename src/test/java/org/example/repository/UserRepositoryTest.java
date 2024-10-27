package org.example.repository;

import org.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        userRepository = new UserRepository();
    }

    @Test
    public void testgetUsersArray() {
        ArrayList<User> users = userRepository.getUsersArray();

        assertNotNull(users);
    }
}
