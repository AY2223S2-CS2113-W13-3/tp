package seedu.duke;

import seedu.duke.command.Command;
import seedu.duke.exception.CommandNotRecognisedException;

public class Parser {
    private final Ui ui = new Ui();
    public void processCommand(String input) throws CommandNotRecognisedException {
        try {
            String action = input.split(" ")[0];

            switch (action) {
            case Command.COMMAND_BYE:
                ui.printBye();
                break;
            case Command.COMMAND_HELP:
                try {
                    processHelpCommand(input);
                } catch (CommandNotRecognisedException e) {
                    ui.printCommandNotRecognised();
                }
                break;
            default:
                throw new CommandNotRecognisedException();
            }
        } catch (NullPointerException e) {
            ui.printNoInput();
        }
    }

    private void processHelpCommand(String input) throws CommandNotRecognisedException {
        String[] commands = input.split("--");

        if (commands.length == 1) {
            ui.printAllCommands();
            ui.printDefaultHelp();
            return;
        }

        for (String c : commands) {
            c = c.trim();
            if (c.equals(Command.COMMAND_HELP) || c.equals("")) {
                continue;
            }

            switch(c) {
            case Command.COMMAND_BYE:
                ui.printHelpBye();
                break;
            case Command.COMMAND_LIST:
                ui.printHelpList();
                break;
            case Command.COMMAND_ADD:
                ui.printHelpAdd();
                break;
            case Command.COMMAND_REMOVE:
                ui.printHelpRemove();
                break;
            case Command.COMMAND_FIND:
                ui.printHelpFind();
                break;
            default:
                throw new CommandNotRecognisedException();
            }
        }
        ui.printDefaultHelp();
    }
}
