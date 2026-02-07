package edu.naukma;

public class Main {
    public static void main(String[] args) {
        University university = new University(
                "Національний університет 'Києво-Могилянська академія' ",
                "НаУКМА",
                "Київ",
                "вул. Сковороди, 2"
        );

        ConsoleMenu menu = new ConsoleMenu(university);
        menu.start();
    }
}

