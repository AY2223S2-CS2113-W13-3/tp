package seedu.duke.exceptions;

public class InvalidFlagException extends DukeException{
    public InvalidFlagException(String flag) {
        this.errorMessage = "the flag " + "\"--" + flag + "\"" + " is invalid";
    }
}
