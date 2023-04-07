package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.food.Food;
import seedu.duke.food.FoodList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveCommandTest {

    @Test
    public void removeIndex_commandString_numberOfFoodLeft() throws DukeException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("Milk", "25/03/2025"));
        foodList.addFood(new Food("Bread", "18/03/2025"));
        foodList.addFood(new Food("Cheese", "26/06/2025"));
        foodList.removeFood(2);
        int expectedOutput = 2;
        assertEquals(expectedOutput, foodList.getNumberOfFood());
    }
}
