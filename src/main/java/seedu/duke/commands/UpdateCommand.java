package seedu.duke.commands;


import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.IllegalValueException;
import seedu.duke.exceptions.InvalidFlagException;
import seedu.duke.food.Food;
import seedu.duke.food.FoodList;
import seedu.duke.utils.DateFormatter;
import java.util.Arrays;

public class UpdateCommand extends Command{
    public static final String COMMAND_WORD = "update";
    private static final String FLAG_SEPARATOR = "--";
    String index;
    String[] flags;

    public UpdateCommand(String arguments) {
        String[] details = arguments.split(FLAG_SEPARATOR);
        this.index = details[0];
        this.flags = Arrays.copyOfRange(details, 1, details.length);
    }

    @Override
    public CommandResult execute(FoodList foodList) throws DukeException {
        if (index.isBlank()) {
            throw new IllegalValueException("Incorrect index entered");
        }
        DateFormatter dateFormatter = new DateFormatter();

        int index = Integer.parseInt(this.index.trim()) - 1;
        Food currentFood = foodList.getFood(index);

        for (String flag: flags) {
            String flagName = flag.split(" ")[0].trim().toLowerCase();
            String flagValue = flag.split(" ")[1].trim().toLowerCase();
            try {
                switch (flagName) {
                case "n":
                    currentFood.setName(flagValue);
                    break;
                case "e":
                    dateFormatter.checkValidDate(flagValue);
                    currentFood.setExpiryDate(flagValue);
                    break;
                case "q":
                    if (currentFood.getQuantity() == 0 && Arrays.asList(flags).contains("u")) {
                        throw new DukeException("Can't set quantity with no unit provided");
                    }
                    currentFood.setQuantity(Double.parseDouble(flagValue));
                    break;
                case "u":
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
