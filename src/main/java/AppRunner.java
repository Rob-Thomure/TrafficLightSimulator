import java.io.IOException;
import java.util.Scanner;

public class AppRunner {
    private boolean exit;
    private final Scanner keyboard;
    private static final String INCORRECT_INPUT = "Error! Incorrect Input. Try again: ";
    private static final String INCORRECT_OPTION = "Incorrect option";
    private static final String INPUT_NUMBER_OF_ROADS = "Input the number of roads: ";
    private static final String INPUT_INTERVAL = "Input the interval: ";

    public AppRunner() {
        this.keyboard = new Scanner(System.in);
        this.exit = false;
    }

    public void execute() {
        printGreeting();
        System.out.print(INPUT_NUMBER_OF_ROADS);
        int numRoads = getPositiveIntFromKeyboard();
        System.out.print(INPUT_INTERVAL);
        int numInterval = getPositiveIntFromKeyboard();
        clearConsole();
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

    private int getPositiveIntFromKeyboard() {
        int num = 0;
        while (num < 1) {
            String input = keyboard.nextLine();
            if (isDigit(input) && isPositiveNum(input))
                num = Integer.parseInt(input);
            else
                System.out.print(INCORRECT_INPUT);
        }
        return num;
    }

    private boolean isDigit(String input) {
        return input.matches("\\d+");
    }

    private boolean isPositiveNum(String input) {
        return Integer.parseInt(input) > 0;
    }

    private void addRoad() {
        System.out.println("Road added");
        waitForKeyStrokeThenClearConsole();
    }

    private void deleteRoad() {
        System.out.println("Road deleted");
        waitForKeyStrokeThenClearConsole();
    }

    private void openSystem() {
        System.out.println("System opened");
        waitForKeyStrokeThenClearConsole();
    }

    private void quit() {
        exit = true;
        System.out.println("Bye!");
    }

    private String getRequestFromKeyboard() {
        String request = keyboard.nextLine();
        while (!isValidRequest(request)) {
            System.out.println(INCORRECT_OPTION);
            waitForKeyStrokeThenClearConsole();
            printMainMenu();
            request = keyboard.nextLine();
        }
        return request;
    }

    private boolean isValidRequest(String request) {
        return request.matches("[0123]");
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

    private void waitForKeyStrokeThenClearConsole() {
        keyboard.nextLine();
        clearConsole();
    }

    private void clearConsole() {
        try {
            var clearCommand = System.getProperty("os.name").contains("Windows")
                    ? new ProcessBuilder("cmd", "/c", "cls")
                    : new ProcessBuilder("clear");
            clearCommand.inheritIO().start().waitFor();
        }
        catch (IOException | InterruptedException e) {}

    }
 }
