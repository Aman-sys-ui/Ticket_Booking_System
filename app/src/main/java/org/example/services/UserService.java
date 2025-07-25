package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.entities.User;
import org.example.util.UserServiceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UserService {
    private List<User> userList;
    private ObjectMapper objectMapper;
    private static final String USER_FILE_PATH = "app/src/main/java/org/example/localDb/users.json";

    public UserService() throws IOException {
        loadUserFile();
    }

    public void loadUserFile() throws IOException {
        objectMapper = new ObjectMapper();
        File file = new File(USER_FILE_PATH);
        if (file.length() == 0) {
            userList = new ArrayList<>();
        } else {
            userList = objectMapper.readValue(file, new TypeReference<List<User>>() {});
        }
    }

    public void signUp(User user) throws IOException {
        saveUserToFile(user);
    }

    private void saveUserToFile(User user) throws IOException {
        objectMapper = new ObjectMapper();
        userList.add(user);
        File usersFile = new File(USER_FILE_PATH);
        objectMapper.writeValue(usersFile, userList);
        System.out.println(user.getName() + " added");
    }

    public User login(User loginUser) throws Exception {
        Optional<User> foundUser = userList.stream().filter(user -> {
            try {
                return user.getUserName().equals(loginUser.getUserName()) &&
                        UserServiceUtil.verifyPassword(loginUser.getPassword(), user.getPassword());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).findFirst();

        if (foundUser.isPresent()) {
            System.out.println("Welcome " + foundUser.get().getName());
            return foundUser.get();
        } else {
            System.out.println("Invalid credentials.");
            return null;
        }
    }
}
