package njensen.exceptions;

/**
 * @author Nicholas Jensen on 05/27/2017.
 */
public class BadCustomCommandGroupException extends Exception {

    public BadCustomCommandGroupException(String message) {
        super("[Error] Bad command group. A command from the group failed.\n" + message);
    }

}
