import java.util.Scanner;

public class AppRunner {
    private boolean exit = false;
    private final Scanner keyboard = new Scanner(System.in);


    public void execute() {
        printGreeting();
        int numRoads = getNumberOfRoadsFromKeyboard();
        int numInterval = getIntervalFromKeyboard();
        executeRequests();
    }

    private void printGreeting() {
        System.out.println("Welcome to the traffic management system!");
    }

    private void printMainMenu() {
        System.out.println("""
                Menu:
                1. Add road
                2. Delete road
                3. Open system
                0. Quit""");
    }

    private int getNumberOfRoadsFromKeyboard() {
        System.out.print("Input the number of roads: ");
        return keyboard.nextInt();
    }

    private int getIntervalFromKeyboard() {
        System.out.print("Input the interval: ");
        int interval = keyboard.nextInt();
        keyboard.nextLine();
        return interval;
    }

    private void addRoad() {
        System.out.println("Road added");
    }

    private void deleteRoad() {
        System.out.println("Road deleted");
    }

    private void openSystem() {
        System.out.println("System opened");
    }

    private void quit() {
        exit = true;
        System.out.println("Bye!");
    }

    private String getRequestFromKeyboard() {
        return keyboard.nextLine();
    }

    private void executeRequests() {
        while (!exit) {
            printMainMenu();
            String request = getRequestFromKeyboard();
            switch (request) {
                case "0" -> quit();
                case "1" -> addRoad();
                case "2" -> deleteRoad();
                case "3" -> openSystem();
            }
        }
    }
 }
