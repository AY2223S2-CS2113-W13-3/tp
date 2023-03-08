package seedu.duke;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {

    public void printDivider() {
        System.out.println("____________________________________________________________");
    }

    public void printStartMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("How can I help you?");
        printDivider();
    }

    public String getUserInput() {
        String input = null;
        Scanner in = new Scanner(System.in);
        try {
            input = in.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("☹ OOPS!!! You did not enter any command");
        }

        return input;
    }

    public void printHelpBye() {
        System.out.println("Command 'bye': This command is used to exit the program.\n");
    }

    public void printAllCommands() {
        System.out.println("List of commands: 'bye', 'help', 'list', 'add', 'remove', 'find'"
                + "\nFor more detailed information on usage of specific command, type: help --COMMAND");
    }
    public void printHelpList() {
        System.out.println("Command 'list': This command lists all food products in the tracker."
                + "\nAppend the filter '--fresh' for listing unexpired food products and" +
                "'--expired' for listing expired food products.\n");
    }
    public void printHelpAdd() {
        System.out.println("Command 'add': This command adds a food product to the food supply tracker."
                + "\nFormat: add -n PRODUCT_NAME -e EXPIRY_DATE\n");
    }
    public void printHelpRemove() {
        System.out.println("Command 'remove': This command removes the food product from the food supply tracker"
                + " based on its index." + "\nFormat: remove INDEX\n");
    }
    public void printHelpFind() {
        System.out.println("Command 'find': This command finds the food product by its name."
                + "\nFormat: find PRODUCT_NAME\n");
    }
    public void printDefaultHelp() {
        System.out.println("Refer to our user guide for more in-depth details on how to use our system:"
                + "\nhttps://docs.google.com/document/d/1WKscnkYy9UqI_tsWmUHIMjgILJc6GQeFn0B1ce6qkQo/edit?usp=sharing");
        printDivider();
    }

    public void printCommandNotRecognised() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that command means :-(");
        printAllCommands();
        printDivider();
    }

    public void printBye() {
        System.out.println("Bye! Hope to see you again.");
        printDivider();
    }
}
