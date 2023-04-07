package seedu.duke;

import java.util.logging.Logger;
import java.util.logging.Level;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.InvalidStorageFilePathException;
import seedu.duke.exceptions.StorageOperationException;
import seedu.duke.food.FoodList;
import seedu.duke.general.Parser;
import seedu.duke.general.Ui;
import seedu.duke.storage.StorageFile;


/**
 * Entry point of the Food Supply Tracker application
 * Initializes the application and starts the interaction with the user.
 */
public class Duke {
    private static final Logger logger = Logger.getLogger("Run Duke Log");
    private final Ui ui;

    private FoodList foodList;

    private StorageFile storageFile;

    public Duke() {
        ui = new Ui();
        try {
            storageFile = new StorageFile();
        } catch (InvalidStorageFilePathException e) {
            Ui.showError(e.getMessage());
        }

        try {
            foodList = storageFile.load();
        } catch (StorageOperationException e) {
            Ui.showError(e.getMessage());
        }
    }

    public boolean processCommand(String fullCommand) throws DukeException {
        Command c = Parser.parse(fullCommand);
        CommandResult result = c.execute(foodList);
        result.printResult();
        storageFile.save(foodList);
        return c.isExit();
    }

    // This part of the code is adapted from the module website
    // https://nus-cs2113-ay2223s2.github.io/website/schedule/week7/project.html
    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                isExit = processCommand(fullCommand);
            } catch (DukeException e) {
                logger.log(Level.WARNING, "ERROR");
                Ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        logger.log(Level.INFO, "Start running Duke");
        new Duke().run();
        logger.log(Level.INFO, "End of running Duke");
    }
}
