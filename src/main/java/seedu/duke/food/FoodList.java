package seedu.duke.food;

import java.util.ArrayList;

public class FoodList {
    private ArrayList<Food> foodList = new ArrayList<>();

    public FoodList() {
    }

    public void addFood(Food food) {
        foodList.add(food);
    }

    public void removeFood(int index) {
        assert index > 0: "Index of food entered must be larger or equal to 1";
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

    public FoodList findFood(String term) {
        FoodList result = new FoodList();

        for (Food foodItem: foodList) {
            String name = foodItem.getName();
            if (name.toLowerCase().contains(term.toLowerCase().trim())) {
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
