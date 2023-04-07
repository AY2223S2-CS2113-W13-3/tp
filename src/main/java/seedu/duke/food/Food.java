package seedu.duke.food;

import seedu.duke.exceptions.DukeException;
import seedu.duke.general.Ui;
import seedu.duke.utils.DateFormatter;
import seedu.duke.utils.Validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Food {
    private static final String MILLIGRAM_1 = "mg";
    private static final String MILLIGRAM_2 = "milligram";
    private static final String MILLIGRAM_3 = "milligrams";
    private static final String GRAM_1 = "gram";
    private static final String GRAM_2 = "g";
    private static final String GRAM_3 = "grams";
    private static final String KILOGRAM_1 = "kg";
    private static final String KILOGRAM_2 = "kilogram";
    private static final String KILOGRAM_3 = "kilograms";
    private static final String MILLIMETRE_1 = "ml";
    private static final String MILLIMETRE_2 = "millilitre";
    private static final String MILLIMETRE_3 = "millilitres";
    private static final String LITRE_1 = "l";
    private static final String LITRE_2 = "litre";
    private static final String LITRE_3 = "litres";
    private static final String SERVING_1 = "serving";
    private static final String SERVING_2 = "servings";
    private static final String UNIT_1 = "unit";
    private static final String UNIT_2 = "units";
    private static final String BOX_1 = "box";
    private static final String BOX_2 = "boxes";
    private static final String PACKET_1 = "packet";
    private static final String PACKET_2 = "packets";

    private String name;
    private String expiryDate;
    private Double quantity;
    private String unit;
    private FoodCategory category;

    /**
     * Constructor
     *
     * @param name       food name
     * @param expiryDate food expiry date
     * @param quantity   food quantity
     * @param unit       food unit
     * @param category   food category
     */
    public Food(String name,
                String expiryDate,
                Double quantity,
                String unit,
                FoodCategory category) throws DukeException {
        Validator.isQuantityValid(quantity);
        Validator.isExpiryDateValid(expiryDate);
        Validator.isUnitValid(unit);

        this.name = name;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.unit = unit;
        this.category = category;
    }

    public Food(String name, String expiryDate) throws DukeException {
        Validator.isExpiryDateValid(expiryDate);

        this.name = name;
        this.expiryDate = expiryDate;
        this.category = FoodCategory.OTHERS;
        this.quantity = 0.0;
        this.unit = Unit.UNITS.abbreviation;
    }

    /**
     * Constructor for Food object with no quantity and no unit
     *
     * @param name       food name
     * @param expiryDate food expiry date
     * @param category   food category
     */
    public Food(String name, String expiryDate, FoodCategory category) throws DukeException {
        Validator.isExpiryDateValid(expiryDate);

        this.name = name;
        this.expiryDate = expiryDate;
        this.quantity = 0.0;
        this.unit = Unit.UNITS.abbreviation;
        this.category = category;
    }

    public Food(String name, String expiryDate, Double quantity) throws DukeException {
        Validator.isQuantityValid(quantity);
        Validator.isExpiryDateValid(expiryDate);

        this.name = name;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        String dummyUnit = "dummy";
        this.unit = getUnitString(dummyUnit, quantity);
        this.category = FoodCategory.OTHERS;
    }

    public Food(String name, String expiryDate, Double quantity, FoodCategory category) throws DukeException {
        Validator.isQuantityValid(quantity);
        Validator.isExpiryDateValid(expiryDate);

        this.name = name;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        String dummyUnit = "dummy";
        this.unit = getUnitString(dummyUnit, quantity);
        this.category = category;
    }

    public Food(String name, String expiryDate, Double quantity, String unit) throws DukeException {
        Validator.isQuantityValid(quantity);
        Validator.isExpiryDateValid(expiryDate);
        Validator.isUnitValid(unit);

        this.name = name;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        this.unit = unit;
        this.category = FoodCategory.OTHERS;
    }


    public Food(String name, String expiryDate, Double quantity, String unit, String category) throws DukeException {
        Validator.isQuantityValid(quantity);
        Validator.isExpiryDateValid(expiryDate);
        Validator.isUnitValid(unit);

        this.name = name;
        this.expiryDate = expiryDate;
        this.category = FoodCategory.valueOf(category);
        this.quantity = quantity;
        this.unit = unit;
    }

    public Food(String name, String expiryDate, String category) {
        this.name = name;
        this.expiryDate = expiryDate;
        this.category = FoodCategory.valueOf(category);
    }


    public LocalDate getDate() {
        return LocalDate.now();
    }

    public LocalDate parseExpiryDate() throws DukeException {
        return DateFormatter.parse(expiryDate);
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public FoodCategory getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        for (FoodCategory c : FoodCategory.values()) {
            if (getCategoryString(c).equals(category.toLowerCase().trim())) {
                this.category = c;
                return;
            }
        }
        // If no category matched, set as unclassified
        this.category = FoodCategory.OTHERS;
    }

    public String getCategoryString(FoodCategory category) {
        switch (category) {
        case FRUIT:
            return "fruit";
        case MEAT:
            return "meat";
        case DAIRY:
            return "dairy";
        case GRAIN:
            return "grain";
        case BEVERAGE:
            return "beverage";
        case SEAFOOD:
            return "seafood";
        case VEGETABLE:
            return "vegetable";
        default:
            return "others";
        }
    }

    public void setUnit(String unit) {
        String unitTemp = getUnitString(unit, quantity);
        this.unit = unitTemp;
    }

    public String getUnitString(String unitTemporary, Double quantityInDouble) {
        switch (unitTemporary.toLowerCase()){
        case MILLIGRAM_1:
        case MILLIGRAM_2:
        case MILLIGRAM_3:
            return String.valueOf(Unit.MILLIGRAM.abbreviation);
        case GRAM_1:
        case GRAM_2:
        case GRAM_3:
            return String.valueOf(Unit.GRAM.abbreviation);
        case KILOGRAM_1:
        case KILOGRAM_2:
        case KILOGRAM_3:
            return String.valueOf(Unit.KILOGRAM.abbreviation);
        case MILLIMETRE_1:
        case MILLIMETRE_2:
        case MILLIMETRE_3:
            return String.valueOf(Unit.MILLILITER.abbreviation);
        case LITRE_1:
        case LITRE_2:
        case LITRE_3:
            return String.valueOf(Unit.LITER.abbreviation);
        case SERVING_1:
        case SERVING_2:
            if(quantityInDouble == 1) {
                return String.valueOf(Unit.SERVING.abbreviation);
            } else {
                return String.valueOf(Unit.SERVINGS.abbreviation);
            }
        case BOX_1:
        case BOX_2:
            if(quantityInDouble > 1){
                return String.valueOf(Unit.BOXES.abbreviation);
            } else {
                return String.valueOf(Unit.BOX.abbreviation);
            }
        case PACKET_1:
        case PACKET_2:
            if(quantityInDouble > 1){
                return String.valueOf(Unit.PACKETS.abbreviation);
            } else {
                return String.valueOf(Unit.PACKET.abbreviation);
            }
        default:
            if(quantityInDouble > 1) {
                return String.valueOf(Unit.UNITS.abbreviation);
            } else {
                return String.valueOf(Unit.UNIT.abbreviation);
            }
        }
    }

    /**
     * Returns a food product name
     *
     * @return name food name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a food expiry date string
     *
     * @return expiryDate food expiry date
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    public boolean isFresh() throws DukeException {
        LocalDate expiryDate = parseExpiryDate();
        return expiryDate.isAfter(getDate());
    }
    public String getExpiryStatus() throws DukeException {
        String expiryStatus;
        if(!isFresh()){
            expiryStatus = " (expired) ";
        } else {
            expiryStatus = " (fresh) ";
        }
        return expiryStatus;
    }

    public long getDaysExpire () throws DukeException {
        LocalDate expiryDate = parseExpiryDate();
        long days = ChronoUnit.DAYS.between(getDate(), expiryDate);
        return days;
    }

    public String getDaysString() throws DukeException {
        if(isFresh()) {
            String daysToExpireNotice = " (" + getDaysExpire() + " days left)";
            return daysToExpireNotice;
        }

        String daysExpiredNotice = null;
        long convertToPositiveDays = (-1) * getDaysExpire();
        daysExpiredNotice = " (expired " + convertToPositiveDays + " days)";
        return daysExpiredNotice;
    }

    //@@author david
    /**
     * Returns a foodDetail string
     *
     * @return foodDetails a String of complete food details to be printed
     */
    @Override
    public String toString()  {
        Double quantity = getQuantity();
        String foodDetail;
        String expiryStatus = "";
        String daysLeftString = "";

        try {
            expiryStatus = getExpiryStatus();
            daysLeftString = getDaysString();
        } catch (DukeException e) {
            Ui.showError("Expiry date parsing error");
        }

        if (quantity == 0.0) {
            foodDetail = getName() + expiryStatus
                    + "\n       Expiry date: " + getExpiryDate() + daysLeftString
                    + "\n       Category: " + getCategoryString(getCategory());
        } else {
            foodDetail = getName() + expiryStatus
                    + "\n       Expiry date: " + getExpiryDate() + daysLeftString
                    + "\n       Category: " + getCategoryString(getCategory())
                    + "\n       Remaining quantity: " + getQuantity() + " " + getUnit();
        }
        return foodDetail;
    }
}
