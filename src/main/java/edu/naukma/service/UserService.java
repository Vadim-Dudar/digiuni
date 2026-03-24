package edu.naukma.service;

import edu.naukma.domain.User;
import edu.naukma.domain.UserRole;
import edu.naukma.ui.cli.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
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
        while (true) {
            login = InputUtils.readString("Enter username: ");
            if (users.contains(new User(login, "anyPassword", null))) {
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
        log.info("User created: {}", user);
    }

    public static User chooseUser(Set<User> users) {
        if (users == null || users.isEmpty()) throw new IllegalArgumentException("User list can not be empty or null!");

        List<User> userList = new ArrayList<>(users);

        int i = 1;
        for (User user : userList) {
            System.out.println(i++ + " - " + user.getLogin());
        }

        while (true) {
            int choice = InputUtils.readInt("Choose user: ");
            if (choice < 1 || choice > userList.size()) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            return userList.get(choice - 1);
        }
    }

    public static void deleteUser(Set<User> users) {
        if (users.isEmpty()) throw new IllegalArgumentException("User list can not be empty or null!");
        User user = chooseUser(users);
        users.remove(user);
        System.out.println("User successfully deleted: " + user);
        log.info("User deleted: {}", user);
    }

    public static void changePassword(Set<User> users) {
        if (users.isEmpty()) throw new IllegalArgumentException("User list can not be empty or null!");

        User user = chooseUser(users);
        String oldPassword, newPassword;
        while (true) {
            oldPassword = InputUtils.readString("Enter old password: ");
            newPassword = InputUtils.readString("Enter new password: ");

            if (!user.checkPassword(oldPassword)) {
                System.out.println("Incorrect old password. Please try again.");
                continue;
            } else if (newPassword.trim().isEmpty()) {
                System.out.println("New password cannot be empty. Please try again.");
                continue;
            } else if (oldPassword.equals(newPassword)) {
                System.out.println("New password cannot be the same as old password. Please try again.");
                continue;
            }
            break;
        }
        user.changePassword(oldPassword, newPassword);
        System.out.println("Password successfully changed for user: " + user);
        log.info("Password changed for user: {}", user);
    }
}
