package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.food.Food;
import seedu.duke.food.FoodList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {

    @Test
    public void termSearch_withoutFlags_numberOfFoundFood() throws DukeException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("Chicken egg", "25/04/2025"));
        foodList.addFood(new Food("Ostrich Egg  ", "26/04/2025"));
        foodList.addFood(new Food("Cow's Milk", "26/04/2025"));
        int expected = 2;
        assertEquals(expected, foodList.findFood("EGG ").getNumberOfFood());
    }

    @Test void termSearch_withFreshFlag_numberOfFoundFood() throws DukeException {
        FoodList foodList = new FoodList();
        foodList.addFood(new Food("Expired egg", "25/04/2024"));
        foodList.addFood(new Food("Good Egg  ", "26/04/2025"));
        foodList.addFood(new Food("Expired Milk", "27/04/2024"));
        foodList.addFood(new Food("Good Milk", "27/04/2025"));
        int expected = 2;
        assertEquals(expected, foodList.findFood("EGG", "fresh").getNumberOfFood());
    }

}
