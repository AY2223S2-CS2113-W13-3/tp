package seedu.duke.commands;

import seedu.duke.food.FoodList;

public class ClearCommand extends Command {
    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_CLEAR_ACKNOWLEDGEMENT = "Clearing the food list as requested...";

    @Override
    public CommandResult execute(FoodList foodList) {
        foodList.clearFoodList();
        return new CommandResult(MESSAGE_CLEAR_ACKNOWLEDGEMENT);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
