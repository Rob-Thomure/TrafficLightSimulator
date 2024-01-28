import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class AppRunner {
    private enum States {NOT_STARTED, MENU, SYSTEM}
    private States appState;
    private boolean exit;
    private int numRoads;
    private int numInterval;
    private RoadCircularQueue roadCircularQue;
    private Thread queueThread;
    private final Scanner keyboard;
    private static final String INCORRECT_INPUT = "Error! Incorrect Input. Try again: ";
    private static final String INCORRECT_OPTION = "Incorrect option";
    private static final String INPUT_NUMBER_OF_ROADS = "Input the number of roads: ";
    private static final String INPUT_INTERVAL = "Input the interval: ";

    public AppRunner() {
        this.keyboard = new Scanner(System.in);
        this.exit = false;
        this.appState = States.NOT_STARTED;
        this.numRoads = 0;
        this.numInterval = 0;
    }

    public void execute() {
        printGreeting();
        System.out.print(INPUT_NUMBER_OF_ROADS);
        numRoads = getPositiveIntFromKeyboard();
        roadCircularQue = new RoadCircularQueue(numRoads);
        System.out.print(INPUT_INTERVAL);
        numInterval = getPositiveIntFromKeyboard();
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
        System.out.print("Input road name: ");
        String roadName = keyboard.nextLine();
        Road road = new Road(roadName);

        if (roadCircularQue.enqueue(road)) {
            System.out.println(roadName + " added!");
        } else {
            System.out.println("Queue is full");
        }


        nextLineThenClearConsole();
    }

    private void deleteRoad() {
        try {
            Road road = roadCircularQue.dequeue();
            System.out.println(road.getName() + " deleted!");
        } catch (NoSuchElementException e) {
            System.out.println("Queue is empty");
        }

        nextLineThenClearConsole();
    }

    private void openSystem() {
        appState = States.SYSTEM;
        nextLineThenClearConsole();
        appState = States.MENU;
    }

    private void quit() {
        exit = true;
        System.out.println("Bye!");
    }

    private String getRequestFromKeyboard() {
        String request = keyboard.nextLine();
        while (!isValidRequest(request)) {
            System.out.println(INCORRECT_OPTION);
            nextLineThenClearConsole();
            printMainMenu();
            request = keyboard.nextLine();
        }
        return request;
    }

    private boolean isValidRequest(String request) {
        return request.matches("[0123]");
    }

    private void executeRequests() {
        appState = States.MENU;
        queueThread = new Thread(this::run, "QueueThread");
        queueThread.start();
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

    private void nextLineThenClearConsole() {
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

    private void run() {
        TimeCounter time = new TimeCounter();
        while (!exit) {
            if (appState == States.SYSTEM) {
                clearConsole();
                printSystemInfo(time);
            }
            try {
                Thread.sleep(1000L);
                time.addSecond();
            } catch (InterruptedException e) {

            }

        }
    }

    private void printSystemInfo(TimeCounter time) {
        String formattedString = """
                ! %ds. have passed since system startup !
                ! Number of roads: %d !
                ! Interval: %d !
                
                %s
                ! Press "Enter" to open menu 1"""
                .formatted(time.getDuration(), numRoads, numInterval, formatRoadList());
        System.out.println(formattedString);
    }

    private String formatRoadList() {
        StringBuilder stringBuilder = new StringBuilder();
        roadCircularQue.getRoadList()
                .forEach(road -> stringBuilder.append(road.getName()).append("\n"));
        return stringBuilder.toString();
    }

}

