public class AppRunner {

    public void execute() {
        printGreeting();
        printMainMenu();
    }

    private void printGreeting() {
        System.out.println("Welcome to the traffic management system!");
    }

    private void printMainMenu() {
        System.out.println("""
            Menu:
            1. Add
            2. Delete
            3. System
            0. Quit""");
    }
 }
