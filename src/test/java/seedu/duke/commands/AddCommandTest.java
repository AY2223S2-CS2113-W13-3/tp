package seedu.duke.commands;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddCommandTest {

    @Test
    void testSplitDetails_oneString_returnsStringsArrayInTwoParts() {
        String details = " -n Egg -e 21/03/2023";
        AddCommand addFood = new AddCommand(details);
        String[] expected = {"Egg", "21/03/2023"};
        assertArrayEquals(expected, addFood.splitDetails(details));
    }

    @Test
    void testSplitDetails_oneString_returnsStringsArrayInThreeParts() {
        String details = " -n Egg -e 21/03/2023 -c others";
        AddCommand addFood = new AddCommand(details);
        String[] expected = {"Egg", "21/03/2023", "others"};
        assertArrayEquals(expected, addFood.splitDetails(details));
    }

    @Test
    void testSplitDetails_oneString_returnsStringsArrayInFourParts() {
        String details = " -n Egg -e 21/03/2023 -q 10 -u unit";
        AddCommand addFood = new AddCommand(details);
        String[] expected = {"Egg", "21/03/2023", "10", "unit"};
        assertArrayEquals(expected, addFood.splitDetails(details));
    }

    @Test
    void testSplitDetails_oneString_returnsStringsArrayInFiveParts() {
        String details = " -n Egg -e 21/03/2023 -c dairy -q 10 -u pieces";
        AddCommand addFood = new AddCommand(details);
        String[] expected = {"Egg", "21/03/2023", "10", "pieces","dairy"};
        assertArrayEquals(expected, addFood.splitDetails(details));
    }

    @Test
    void testIsValidDate_validDate_returnsTrue() {
        String details = " -n egg -e 21/04/2028 -q 10 -u unit";
        AddCommand addFood = new AddCommand(details);
        String date = addFood.splitDetails(details)[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expiryDate = LocalDate.parse(date, formatter);
        boolean expected = true;
        assertEquals(expected, addFood.isTheDateAfterCurrentDate(expiryDate));
    }

    @Test
    void testIsValidDate_invalidDate_returnsFalse() {
        String details = " -n egg -e 21/10/2010 -q 10 -u unit";
        AddCommand addFood = new AddCommand(details);
        String date = addFood.splitDetails(details)[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate expiryDate = LocalDate.parse(date, formatter);
        boolean expected = false;
        assertEquals(expected, addFood.isTheDateAfterCurrentDate(expiryDate));
    }

}
