package seedu.duke.utils;

import seedu.duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DateFormatter {
    public static final String INVALID_DATE_PROVIDED = "Invalid date provided";

    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DD_MM_YYYY);
    public static LocalDate parse(String date) throws DukeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DD_MM_YYYY);
            return LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException(INVALID_DATE_PROVIDED);
        }
    }
}
