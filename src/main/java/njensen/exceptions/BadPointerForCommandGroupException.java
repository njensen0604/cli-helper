package njensen.exceptions;

/**
 * @author Nicholas Jensen on 05/27/2017.
 */
public class BadPointerForCommandGroupException extends Exception {

    public BadPointerForCommandGroupException(String message) {
        super("[Error] command missing. Wrong pointer.\n" + message);
    }
}
