package seedu.duke.food;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Food {
    private String name;
    private String expiryDate;
    private int quantity;
    
    public Food(String name, String expiryDate) {
        this.name = name;
        this.expiryDate = expiryDate;
    }
    
    public foodQuantity(int quantity){
        this.quantity=quantity;
    }
    
    public LocalDate getDate() {
        return LocalDate.now();
    }

    public LocalDate formatDate(String expiryDate) {
        return LocalDate.parse(expiryDate);
    }

    public String getName() {
        return name;
    }

    public String getExpiryDate() {
        return expiryDate;
    }
    
    public int getFoodQuantity(){
        return quantity;
    }
    public String printFoodDetails() {
        return "Product Name: " + getName() + "\n" + "Expired by: " + getExpiryDate();
    }

    @Override
    public String toString() {
        return getName() + "\n       Expiry date: " + getExpiryDate();
    }
}
