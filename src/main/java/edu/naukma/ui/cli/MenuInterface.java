package edu.naukma.ui.cli;

import edu.naukma.domain.User;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class MenuInterface {
    private final List<MenuBranch> branches = new ArrayList<>();
    @Getter
    private final Set<User> users = new HashSet<>();
    private User currentUser;

    /**
     * Add a menu branch to the interface.
     *
     * @param branch The menu branch to add.
     * @throws IllegalArgumentException if the menu branch is null.
     */
    public void addMenuBranch(MenuBranch branch) {
        if (branch == null) throw new IllegalArgumentException("Menu branch cannot be null!");
        branches.add(branch);
    }

    /**
     * Add a user to the interface.
     *
     * @param user The user to add.
     * @throws IllegalArgumentException if the user is null.
     */
    public void addUser(User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null!");
        users.add(user);
    }

    public void run() {
        while (true) {
            login();
            while (currentUser != null) {
                printMenu(branches);
                int choice = InputUtils.readInt("Select option: ");
                if (choice == 0) {
                    currentUser = null;
                    System.out.println("Logged out.");
                    break;
                }

                if (choice < 1 || choice > branches.size()) {
                    System.out.println("[Invalid selection.]");
                    continue;
                }

                MenuBranch branch = branches.get(choice - 1);
                List<MenuItem> items = branch.getItems();

                List<MenuItem> allowed = new ArrayList<>();
                for (MenuItem it : items) {
                    if (it != null && it.allowsAccess(currentUser)) allowed.add(it);
                }

                if (allowed.isEmpty()) {
                    System.out.println("[No available items in this branch for your role.]");
                    continue;
                }

                System.out.println("\n--- " + branch.getDescription() + " ---");
                for (int i = 0; i < allowed.size(); i++) {
                    MenuItem it = allowed.get(i);
                    System.out.println((i + 1) + " - " + it.getDescription());
                }
                System.out.println("0 - Back");

                int itemChoice = InputUtils.readInt("Select option: ");
                if (itemChoice == 0) continue;
                if (itemChoice < 1 || itemChoice > allowed.size()) {
                    System.out.println("[Invalid selection.]");
                    continue;
                }

                MenuItem selected = allowed.get(itemChoice - 1);
                try {
                    selected.getAction().execute();
                    log.info("Executed action: " + selected.getDescription() + " by user: " + currentUser.login);
                } catch (Exception e) {
                    log.error("Error executing action: ", e);
                }
            }
        }
    }

    /**
     * Handles the user login process. It prompts the user for their login and password, checks the credentials against the list of registered users,
     * and sets the current user if the login is successful. If the login fails, it allows the user to try again.
     */
    private void login() {
        while (true) {
            System.out.println("\n--- Enter to system ---");


            String login = InputUtils.readString("Login: ");

            for (User user : users) {
                if (user.login.equals(login.trim())) {
                    String password = InputUtils.readString("Password: ");

                    if (user.checkPassword(password.trim())) {
                        currentUser = user;
                        System.out.println("Welcome!");
                        log.info("User logged in: " + user.login);
                        return;
                    }
                    System.out.println("[Password incorrect.]");
                    log.warn("Failed login attempt for user: " + user.login);
                }
            }

            System.out.println("[Login failed! Try again.]");
        }
    }

    /**
     * Prints the menu of available branches to the console. Each branch is displayed with a corresponding number for selection.
     * The method also includes an option for the user to log out by entering '0'.
     *
     * @param branches The list of menu branches to be displayed.
     * @param <T>      A type that extends the Described interface, allowing for flexibility in the types of branches that can be printed.
     */
    private <T extends Described> void printMenu(List<T> branches) {
        System.out.println("\n--- Menu ---");
        for (int i = 0; i < branches.size(); i++) {
            System.out.println((i + 1) + " - " + branches.get(i).getDescription());
        }
        System.out.println("0 - Exit");
    }
}
