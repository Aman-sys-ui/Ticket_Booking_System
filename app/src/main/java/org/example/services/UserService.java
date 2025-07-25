package org.example.services;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.entities.User;
import org.example.util.UserServiceUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

public class UserService {
    private User user ;
    private List<User> userList;
    private ObjectMapper objectMapper ;
    private static final String USER_FILE_PATH = "app/src/main/java/org/example/localDb/users.json";

    public UserService() throws IOException{
            loadUserFile();
    }

//    public UserService(User user) throws IOException{
//        this.user = user;
//
//            loadUserFile();
//
//    }

    public void loadUserFile() throws IOException {
        objectMapper = new ObjectMapper();
        File file = new File(USER_FILE_PATH);
        userList = objectMapper.readValue(file, new TypeReference<List<User>>() {});
    }

    public void signUp(User user) throws IOException{
            saveUserToFile(user);
    }

    private void saveUserToFile(User user) throws IOException {
        objectMapper =new ObjectMapper();
        userList.add(user);
        File usersFile = new File(USER_FILE_PATH);
        objectMapper.writeValue(usersFile, userList);
        System.out.println(user.getName() + "added");
    }

    public void login(User LoginUser) throws Exception {
        Optional<String> foundUser = userList.stream().filter (user -> {
            try {
                return user.getUserName().equals(LoginUser.getUserName()) &&
                        UserServiceUtil.verifyPassword(LoginUser.getPassword(),
                                user.getPassword());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).map(User::getName).findFirst();

        foundUser.ifPresent(e->System.out.println("welcome " + e));

    }

}
