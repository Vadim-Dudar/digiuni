package edu.naukma.services;

import edu.naukma.User;
import edu.naukma.UserRole;
import edu.naukma.console.InputUtils;

import java.util.Set;

public class UserService {
    public static void listUsers(Set<User> users) {
        if (users.isEmpty()) throw new IllegalArgumentException("User list can not be empty or null!");
        System.out.println("List of users:");
        int i = 1;
        for (User user : users) {
            System.out.println(i++ + " - " + user);
        }
    }

    public static void createUser(Set<User> users) {
        if (users == null) throw new IllegalArgumentException("User list can not be null!");

        String login;
        while (true){
            login = InputUtils.readString("Enter username: ");
            if (users.contains(new User(login, "", null))) {
                System.out.println("User with this username already exists.");
                continue;
            }
            break;
        }
        String password = InputUtils.readString("Enter password: ");
        UserRole role = InputUtils.chooseEnum(UserRole.class);

        User user = new User(login, password, role);
        users.add(user);
        System.out.println("User successfully added: " + user);
    }
}
