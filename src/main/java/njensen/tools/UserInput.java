package njensen.tools;

import java.util.Scanner;

/**
 * This prompt the user with a message / question,
 * then returns the enteredText.
 *
 * @author Nicholas Jensen on 05/27/2017.
 */
public class UserInput {

    // text entered by the user
    private String enteredText = "";

    /**
     * This displays a message to the user,
     * then stores in for retrieval.
     *
     * @param message A message that will be prompted to the user.
     * @param newLine Specify whether to use a new line or same line.
     */
    public void getInputFromUser(String message, boolean newLine) {

        Scanner scanner = new Scanner(System.in);

        if (newLine == true) {
            systemOutNewLine(message);
        } else {
            systemOut(message);
        }
        this.enteredText = scanner.nextLine();

    }

    /**
     * This returns the enteredText from the user.
     *
     * @return The enteredText from the user.
     */
    public String getEnteredText() {
        return this.enteredText;
    }

    /**
     * This returns the enteredText from the user,
     * in uppercase.
     *
     * @param trimString Trim whitespace from the user enteredText?
     * @return The enteredText from the user, in uppercase.
     */
    public String getEnteredTextUppercase(boolean trimString) {
        if (trimString == true)
            return this.enteredText.toUpperCase().trim();
        return this.enteredText.toUpperCase();
    }


    /**
     * This returns the enteredText from the user,
     * in lowercase.
     *
     * @param trimString Trim whitespace from the user enteredText?
     * @return The enteredText from the user, in lowercase.
     */
    public String getEnteredTextLowercase(boolean trimString) {
        if (trimString == true)
            return this.enteredText.toLowerCase().trim();
        return this.enteredText.toLowerCase();
    }

    /**
     * Print text in the console.
     *
     * @param message The message to print.
     */
    private void systemOut(String message) {
        if (message != null)
            if (message.trim().length() > 0)
                System.out.print(message);
    }

    /**
     * Print text in the console, new line.
     *
     * @param message The message to print.
     */
    private void systemOutNewLine(String message) {
        if (message != null)
            if (message.trim().length() > 0)
                System.out.println(message);
    }

}
