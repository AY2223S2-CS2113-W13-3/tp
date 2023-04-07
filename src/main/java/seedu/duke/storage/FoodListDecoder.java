package seedu.duke.storage;

import seedu.duke.exceptions.EmptyStorageFileException;
import seedu.duke.exceptions.ReadStorageFileErrorException;
import seedu.duke.exceptions.StorageOperationException;
import seedu.duke.food.Food;
import seedu.duke.food.FoodCategory;
import seedu.duke.food.FoodList;
import seedu.duke.general.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Decodes the storage data file into an {@code FoodList} object.
 * The code is adapted from
 * https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/storage/AddressBookDecoder.java
 */
public class FoodListDecoder {
    private static final String SEPARATOR = "«";
    private static final char NAME_IDENTIFIER = 'n';
    private static final char EXPIRY_DATE_IDENTIFIER = 'e';
    private static final char QUANTITY_IDENTIFIER = 'q';
    private static final char UNIT_IDENTIFIER = 'u';
    private static final char CATEGORY_IDENTIFIER = 'c';
    private static final String MISSING_IDENTIFIER_ERROR = "Error in reading storage file: Missing identifier." ;
    private static final String INVALID_QUANTITY_ERROR = "Quantity in save file is not a valid number.";
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Decodes {@code encodedFoodList} into an {@code FoodList} containing the decoded food.
     */
    public static FoodList decodeFoodList(List<String> encodedFoodList) {
        final ArrayList<Food> decodedFoodList = new ArrayList<>();
        for (String encodedFood : encodedFoodList) {
            try {
                decodedFoodList.add(decodeFoodFromString(encodedFood));
            } catch (StorageOperationException | ReadStorageFileErrorException | EmptyStorageFileException e) {
                Ui.showError(e.getMessage());
            }
        }
        return new FoodList(decodedFoodList);
    }

    /**
     * Decodes {@code encodedFood} into a {@code Food}.
     */

    private static Food decodeFoodFromString(String encodedFood) throws StorageOperationException,
            ReadStorageFileErrorException, EmptyStorageFileException {

        if (encodedFood.equals("")) {
            throw new EmptyStorageFileException();
        }
        String[] details = encodedFood.split(SEPARATOR);
        String foodName = "";
        String expiryDate = "";
        String quantity = "0.0";
        String unit = "";
        String category = "";


        for (String foodDetails : details) {
            try {
                if (foodDetails.equals("")) {
                    continue;
                }

                char identifier = foodDetails.charAt(0);
                String detail = foodDetails.substring(1).trim();

                switch(identifier) {
                case NAME_IDENTIFIER:
                    if (hasValidFoodName(detail)) {
                        assert !detail.equals("");
                        foodName = detail;
                    }
                    break;
                case EXPIRY_DATE_IDENTIFIER:
                    if (hasValidExpiryDate(detail)) {
                        expiryDate = detail;
                    }
                    break;
                case QUANTITY_IDENTIFIER:
                    if(hasValidQuantity(detail)) {
                        quantity = detail;
                    }
                    break;
                case UNIT_IDENTIFIER:
                    unit = detail;
                    break;
                case CATEGORY_IDENTIFIER:
                   if(hasValidCategory(detail)) {
                       category = detail;
                   } else {
                       category = String.valueOf(FoodCategory.UNCLASSIFIED_FOOD);
                   }
                    break;
                default:
                    break;
                }
            } catch (StringIndexOutOfBoundsException e) {
                Ui.showError(MISSING_IDENTIFIER_ERROR);
            }
        }
        try {
            return new Food(foodName, expiryDate, Double.valueOf(quantity), unit, category);
        } catch (NumberFormatException e) {
            Ui.showError(INVALID_QUANTITY_ERROR);
        }
        return new Food(foodName, expiryDate, category);
    }

    private static boolean hasValidFoodName(String detail) throws ReadStorageFileErrorException {
        if (detail.equals("")) {
            throw new ReadStorageFileErrorException("food name: " + detail);
        }
        return true;
    }

    private static boolean hasValidExpiryDate(String detail) throws ReadStorageFileErrorException {
       try {
           LocalDate.parse(detail, formatter);
       } catch (DateTimeParseException e) {
           throw new ReadStorageFileErrorException("expiry date: " + detail);
       }
       if (isInvalidDate(detail)) {
           throw new ReadStorageFileErrorException("expiry date: " + detail);
       }
       return true;
    }

    private static boolean hasValidQuantity(String detail) throws ReadStorageFileErrorException {
        try {
            double value = Double.parseDouble(detail);
            if (value < 0 || value > 9999) {
                throw new ReadStorageFileErrorException("food quantity:" + detail);
            }
        } catch (NumberFormatException e) {
            Ui.showError(INVALID_QUANTITY_ERROR);
        }
        return true;
    }

    private static boolean hasValidCategory(String detail) {
        for (FoodCategory category : FoodCategory.values()) {
            if (category.name().equals(detail)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isInvalidDate(String date) {
        String[] splitString = date.split("/", 3);
        String d = splitString[0];
        String m = splitString[1];
        String y = splitString[2];

        if (Integer.parseInt(y) % 4 != 0 && m.equals("02")) {
            return d.equals("29");
        }
        return false;
    }

}
