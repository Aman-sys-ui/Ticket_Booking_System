package org.example;

import org.example.entities.Trains;
import org.example.entities.User;
import org.example.services.TrainService;
import org.example.services.UserService;
import org.example.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
    public static void main(String[] args) {
        UserService userService;
        TrainService trainService;
        Scanner input = new Scanner(System.in);
        User user;

        System.out.println("App started!");

        try {
            trainService = new TrainService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<List<String>> seats = new ArrayList<>(List.of(List.of("""
                [0,0,0,0,0,0],
                [0,0,0,0,0,0],
                [0,0,0,0,0,0],
                [0,0,0,0,0,0]""")));

        try {
            userService = new UserService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        int option = 0;
        while (option != 4) {
            System.out.println("""
                    1. To SignUp
                    2. To Login
                    3. Admin Dashboard
                    4. To Exit App""");
            System.out.print("Choose an option: ");
            option = input.nextInt();

            switch (option) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = input.next();
                    System.out.print("Enter your userName: ");
                    String userName = input.next();
                    System.out.print("Enter your password: ");
                    String password = input.next();
                    byte[] salt = UserServiceUtil.getSalt();
                    try {
                        user = new User(UUID.randomUUID().toString(), name, userName,
                                UserServiceUtil.hashPassword(password, salt));
                        userService.signUp(user);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 2:
                    System.out.print("Enter your userName: ");
                    String username = input.next();
                    System.out.print("Enter your password: ");
                    String rawPassword = input.next();
                    user = new User(username, rawPassword);

                    try {
                        User loggedInUser = userService.login(user);
                        if (loggedInUser == null) break;
                        user = loggedInUser;

                        int option_after_login = 0;
                        while (option_after_login != 3) {
                            System.out.println("""
                                    1. Search Trains
                                    2. Book Tickets
                                    3. To Logout""");
                            System.out.print("Choose an option: ");
                            option_after_login = input.nextInt();

                            switch (option_after_login) {
                                case 1:
                                    List<Trains> train = trainService.searchTrains(user, "Pune", "Patna");
                                    AtomicInteger index = new AtomicInteger(1);
                                    train.forEach(t -> System.out.printf(index.getAndIncrement() + ". " + """
                                            Train No: %s
                                            Train Stations & Times: %s
                                            """, t.getTrainNo(), t.getStationTime()));

                                    System.out.print("Choose option from search result: ");
                                    int list = input.nextInt();
                                    List<Trains> fetchedTrain = trainService.fetchTrain(train.get(list - 1).getTrainId());

                                    fetchedTrain.forEach(e -> System.out.printf(
                                            "You are booking train %s from %s to %s and total available seats %s ",
                                            e.getTrainNo(),
                                            e.getStations().get(0),
                                            e.getStations().get(e.getStations().size() - 1),
                                            e.getSeats().get(0)
                                    ));
                                    break;

                                case 2:
                                    // To be implemented
                                    break;

                                case 3:
                                    System.out.println("Logging out...");
                                    break;

                                default:
                                    System.out.println("Please choose a valid option.");
                                    break;
                            }
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 3:
                    int option_for_admin = 0;
                    while (option_for_admin != 3) {
                        System.out.println("You are in the admin dashboard.");
                        System.out.println("""
                                1. Add Trains
                                2. Remove Trains
                                3. Exit Dashboard""");
                        System.out.print("Choose an option: ");
                        option_for_admin = input.nextInt();

                        switch (option_for_admin) {
                            case 1:
                                List<String> list = new ArrayList<>();
                                System.out.println("Enter station names and times (comma separated) and separate each station with semicolon (e.g., Pune,08:00;Lucknow,09:30;Patna,12:00): ");
                                String stationDetails = input.next();
                                System.out.print("Enter Train No: ");
                                String trainNo = input.next();

                                try {
                                    trainService.addTrains(stationDetails, seats, trainNo);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                break;

                            case 2:
                                // To be implemented
                                break;

                            default:
                                System.out.println("Please choose a valid option.");
                                break;
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting app...");
                    break;

                default:
                    System.out.println("Invalid option. Please select from the menu.");
                    break;
            }
        }
    }
}
