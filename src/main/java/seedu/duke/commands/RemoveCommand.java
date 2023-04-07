package seedu.duke.commands;

import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.IllegalValueException;
import seedu.duke.food.FoodList;

public class RemoveCommand extends Command {
    public static final String COMMAND_WORD = "remove";
    public static final String BLANK_INDEX_MESSAGE_1 = "Please enter an integer to remove a food item.\n" +
                                                        "You now have ";
    public static final String BLANK_INDEX_MESSAGE_2 = " food items in your list.";
    public static final String INCORRECT_INDEX_MESSAGE_1 = "Incorrect value entered.\n" +
                                                            "Your now have ";
    public static final String INVALID_INPUT_MESSAGE = "Please use a reasonable value :<";
    public String index;

    public RemoveCommand(String index) {
        this.index = index;
    }

    public boolean isInteger (){
        try {
            Integer.parseInt(index);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    public CommandResult execute (FoodList foodlist) throws DukeException {

        if (index.isBlank()) {
            String blankIntegerMessage = BLANK_INDEX_MESSAGE_1 + foodlist.getNumberOfFood() + BLANK_INDEX_MESSAGE_2;
            throw new IllegalValueException(blankIntegerMessage);
        }

        if(!isInteger()){
            String notIntegerMessage = BLANK_INDEX_MESSAGE_1 + foodlist.getNumberOfFood() + BLANK_INDEX_MESSAGE_2;
            return new CommandResult(notIntegerMessage);
        }

        if(!isNumberValid(index)){
            return new CommandResult(INVALID_INPUT_MESSAGE);
        }

        int deleteItem = Integer.parseInt(index.trim()) - 1;
        if (deleteItem >= foodlist.getNumberOfFood() || deleteItem < 0) {
            String incorrectIndexFormat = INCORRECT_INDEX_MESSAGE_1 + foodlist.getNumberOfFood()
                                            + BLANK_INDEX_MESSAGE_2;
            throw new IllegalValueException(incorrectIndexFormat);
        }

        String foodName = foodlist.getFood(deleteItem).getName();
        foodlist.removeFood(deleteItem);
        int itemsLeft = foodlist.getNumberOfFood();
        System.out.println("Removed '" + foodName + "' from the food supply list.");
        return new CommandResult("There is/are now " + itemsLeft + " item(s) in the list.");
    }
    boolean isNumberValid(String number) {
        if (number.length() >= 5) {
            return false;
        }
        return true;
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
