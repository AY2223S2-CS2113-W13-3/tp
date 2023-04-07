package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.food.FoodList;

import java.util.Arrays;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    private static final String FLAG_SEPARATOR = "--";

    String term;
    String[] flags;

    public FindCommand(String arguments) {
        String[] details = arguments.split(FLAG_SEPARATOR);
        this.term = details[0];
        this.flags = Arrays.copyOfRange(details, 1, details.length);
    }

    @Override
    public CommandResult execute(FoodList foodList) throws DukeException {
        FoodList result = foodList.findFood(term, flags);
        if (result.getNumberOfFood() > 0) {
            System.out.println(result);
            return new CommandResult("Found " + result.getNumberOfFood() + " of food items");
        } else {
            return new CommandResult("No food found for such term");
        }
    }
}
