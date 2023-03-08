package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exception.CommandNotRecognisedException;


public class Duke {

    private final Ui ui;
    private final Parser parser;

    public Duke() {
        ui = new Ui();
        parser = new Parser();
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        ui.printStartMessage();

        String userInput;
        do {
            userInput = ui.getUserInput();
            try {
                parser.processCommand(userInput);
            } catch (CommandNotRecognisedException e) {
                ui.printCommandNotRecognised();
                ui.printDivider();
            }
        } while (!userInput.equals(Command.COMMAND_BYE));

    }

}
