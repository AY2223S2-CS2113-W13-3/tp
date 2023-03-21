package seedu.duke.food;

import seedu.duke.exceptions.DukeException;

import java.time.LocalDate;
import java.util.ArrayList;

public class FoodList {
    private ArrayList<Food> foodList = new ArrayList<>();

    public FoodList() {
    }

    public void addFood(Food food) {
        foodList.add(food);
    }

    public void removeFood(int index) {
        foodList.remove(index);
    }

    public int getNumberOfFood() {
        return foodList.size();
    }

    public ArrayList<Food> getFoodList(){
        return foodList;
    }

    public Food getFood(int i){
        return foodList.get(i);
    }

    public FoodList findFood(String term, String ...flags) throws DukeException{
        FoodList result = new FoodList();

        for (Food foodItem: foodList) {
            String name = foodItem.getName();
            LocalDate expiryDate = foodItem.parseExpiryDate();
            boolean hasTerm = name.toLowerCase().contains(term.toLowerCase().trim());
            if (hasTerm && flags.length == 0) {
                result.addFood(foodItem);
                continue;
            }

            // Filter by flags
            for (String flag: flags) {
                if (flag.trim().equals("fresh")) {
                    boolean isFresh = expiryDate.isAfter(LocalDate.now());
                    if(!isFresh) continue;
                }

                else if (flag.trim().equals("expired")) {
                    boolean isExpired = expiryDate.isBefore(LocalDate.now());
                    if(!isExpired) continue;
                }

                else throw new DukeException("the flag " + "\"-" + flag + "\"" + " is invalid");

                // adds the item if all filters passed
                result.addFood(foodItem);
            }

        }

        return result;
    }

    @Override
    public String toString() {
        int index = 1;
        StringBuilder output = new StringBuilder();
        for (Food foodItem : foodList) {
            output.append(index).append(". ").append(foodItem);
            output.append(System.lineSeparator());
            index++;
        }
        return output.toString();
    }
}
