package seedu.duke.commands;

import org.junit.jupiter.api.Test;

import seedu.duke.exceptions.DukeException;
import seedu.duke.food.FoodList;
import seedu.duke.general.Parser;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

class HelpCommandTest {
    private static final String SHOW_ALL_COMMANDS = "List of commands: 'add', 'list', 'remove', 'find',"
            + " 'update', 'exit'."
            + "\nFor more detailed information on usage of specific command, type: help --COMMAND";
    private static final String DEFAULT_HELP_MESSAGE = "Refer to our user guide for more in-depth details on"
            + " how to use Food Supply Tracker:"
            + "\nhttps://ay2223s2-cs2113-w13-3.github.io/tp/UserGuide.html";
    private FoodList foodList;

    @Test
    void helpCommand_multipleWithRegex_expectSeparateByRegex() {
        String input = "--remove--find--add";
        HelpCommand hc = new HelpCommand(input);
        HashSet<String> expectedOutput = new LinkedHashSet<>(List.of(new String[]{"", "remove", "find", "add"}));
        assertEquals(expectedOutput, hc.getFilters());

    }

    @Test
    void helpCommand_withSpacesAndRegex_expectRemoveSpaceSeparateRegex() {
        String input = " --   remove      --find--          add";
        HelpCommand hc = new HelpCommand(input);
        HashSet<String> expectedOutput = new LinkedHashSet<>(List.of(new String[]{"", "remove", "find", "add"}));
        assertEquals(expectedOutput, hc.getFilters());
    }

    @Test
    void helpCommand_withRepeatCommand_expectRemoveRepeat() {
        String input = "--remove --find --remove --add --find";
        HelpCommand hc = new HelpCommand(input);
        HashSet<String> expectedOutput = new LinkedHashSet<>(List.of(new String[]{"", "remove", "find", "add"}));
        assertEquals(expectedOutput, hc.getFilters());
    }

    @Test
    void commandResult_withFilterButNoCommand_expectDefaultMessage() {
        String input = "help --";

        try {
            Command c = Parser.parse(input);
            CommandResult result = c.execute(foodList);
            String expectedOutput = SHOW_ALL_COMMANDS + '\n' + DEFAULT_HELP_MESSAGE;
            assertEquals(expectedOutput, result.feedbackToUser);
        } catch (DukeException e) {
            assert false;
            System.out.println("Unexpected Error");
        }
    }
}
