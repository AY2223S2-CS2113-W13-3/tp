package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.exceptions.DukeException;
import seedu.duke.food.Food;
import seedu.duke.food.FoodList;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.duke.food.FoodCategory.GRAIN;

public class FoodListEncoderTest {

    //TODO: Test for case where there is more than 1 food in foodList
    @Test
    void encodeFoodList_withExpiryDate_expectNameAndExpiry() throws DukeException {
        Food food = new Food("peanuts", "11/11/2023");
        FoodList fl = new FoodList();
        fl.addFood(food);
        ArrayList<String> encodedFoodLists = FoodListEncoder.encodeFoodList(fl);
        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("peanuts « 11/11/2023 « 0.0 « unit « OTHERS");
        assertEquals(expectedOutput, encodedFoodLists);
    }

    @Test
    void encodeFoodList_withExpiryDateAndQuantity_expectNameExpiryAndCategory() throws DukeException {
        Food food = new Food("peanuts", "11/11/2023", GRAIN);
        FoodList fl = new FoodList();
        fl.addFood(food);
        ArrayList<String> encodedFoodLists = FoodListEncoder.encodeFoodList(fl);
        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("peanuts « 11/11/2023 « 0.0 « unit « GRAIN");
        assertEquals(expectedOutput, encodedFoodLists);
    }

    @Test
    void encodeFoodList_withMultipleFood_expectMultipleFoodRecorded() throws DukeException {
        Food peanuts = new Food("peanuts", "11/11/2023");
        Food strawberry = new Food("strawberries", "31/05/2023");
        FoodList fl = new FoodList();
        fl.addFood(peanuts);
        fl.addFood(strawberry);
        ArrayList<String> encodedFoodLists = FoodListEncoder.encodeFoodList(fl);
        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("peanuts « 11/11/2023 « 0.0 « unit « OTHERS");
        expectedOutput.add("strawberries « 31/05/2023 « 0.0 « unit « OTHERS");
        assertEquals(expectedOutput, encodedFoodLists);
    }


    @Test
    void encodeFoodList_withExpiryDateAndQuantityWithUnits_expectNameExpiryAndQuantityWithUnits() throws DukeException {
        Food food = new Food("peanuts", "11/11/2023", 50.0, "g");
        FoodList fl = new FoodList();
        fl.addFood(food);
        FoodListEncoder fle = new FoodListEncoder();
        ArrayList<String> encodedFoodLists = fle.encodeFoodList(fl);
        ArrayList<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("peanuts « 11/11/2023 « 50.0 « g « OTHERS");
        assertEquals(expectedOutput, encodedFoodLists);
    }
}

