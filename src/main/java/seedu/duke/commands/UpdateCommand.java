package seedu.duke.commands;


import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.IllegalValueException;
import seedu.duke.exceptions.InvalidFlagException;
import seedu.duke.food.Food;
import seedu.duke.food.FoodList;
import seedu.duke.utils.DateFormatter;
import seedu.duke.utils.Validator;

import java.util.Arrays;

public class UpdateCommand extends Command{
    public static final String COMMAND_WORD = "update";
    private static final String FLAG_SEPARATOR = "--";
    String index;
    String[] flags;

    public UpdateCommand(String arguments) throws DukeException {
        if(!arguments.contains(FLAG_SEPARATOR)) {
            throw new DukeException("No field to update is specified");
        }
        String[] details = arguments.split(FLAG_SEPARATOR);
        this.index = details[0];
        this.flags = Arrays.copyOfRange(details, 1, details.length);
    }

    @Override
    public CommandResult execute(FoodList foodList) throws DukeException {
        DateFormatter dateFormatter = new DateFormatter();

        int index = 1;
        try {
            index = Integer.parseInt(this.index.trim()) - 1;
        } catch(NumberFormatException e) {
            int numberOfFood = foodList.getNumberOfFood();
            String wrongIndex = "Invalid index format! Please give an index within range of " + numberOfFood;
            throw new DukeException(wrongIndex);
        }
        Food currentFood = foodList.getFood(index);

        for (String flag: flags) {
            String[] flagParts = flag.split(" ");

            if (flagParts.length == 1) {
                String invalidFlagName = flagParts[0];
                throw new InvalidFlagException(invalidFlagName);
            }
            String flagName = flagParts[0].trim().toLowerCase();
            String flagValue = flagParts[1].trim().toLowerCase();
            try {
                switch (flagName) {
                case "n":
                    currentFood.setName(flagValue);
                    break;
                case "e":
                    Validator.isExpiryDateValid(flagValue);
                    currentFood.setExpiryDate(flagValue);
                    break;
                case "q":
                    if (currentFood.getQuantity() == 0.0 && Arrays.asList(flags).contains("u")) {
                        throw new DukeException("Can't set quantity with no unit provided");
                    }
                    double newQuantity = Double.parseDouble(flagValue);
                    if(newQuantity < 0.0){
                        throw new DukeException("Can't set quantity of a negative value.");
                    }
                    currentFood.setQuantity(newQuantity);
                    String newFoodUnit = currentFood.getUnit();
                    currentFood.setUnit(newFoodUnit);
                    break;
                case "u":
                    if (currentFood.getQuantity() == 0.0) {
                        throw new DukeException("Can't set unit when quantity is not provided");
                    }
                    currentFood.setUnit(flagValue);
                    break;
                case "c":
                    currentFood.setCategory(flagValue);
                    break;
                default:
                    throw new InvalidFlagException(flagName);
                }
            } catch (DukeException e) {
                throw e;
            } catch (Exception e) {
                throw new IllegalValueException("Illegal value for the flag " + flagName);
            }
        }


        return new CommandResult("Updated food item successfully! \n" + foodList.getFood(index));
    }
}
