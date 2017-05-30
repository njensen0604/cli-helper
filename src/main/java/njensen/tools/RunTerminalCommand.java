package njensen.tools;


import njensen.exceptions.BadCustomCommandGroupException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This works with the command line.
 *
 * @author Nicholas Jensen on 05/27/2017.
 */
public class RunTerminalCommand {

    /**
     * This sends a command to the command line,
     * then waits for a response.
     *
     * @param command An array with command to call and its arguments.
     * @return Text returned by the system.
     */
    public static String send(String[] command) throws BadCustomCommandGroupException {

        String commandReturnText = null;

        if (command != null)
            if (command.length > 0) {

                String commandFull = "";
                for (String s : command) {
                    commandFull += " " + s;
                }
                systemOut("$ " + commandFull);

                Process process = null;
                try {

                    process = Runtime.getRuntime().exec(command);

                    // Read the output
                    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                    commandReturnText = "";
                    String line = "";
                    try {

                        while ((line = reader.readLine()) != null) {
                            systemOut(line);
                            if (commandReturnText.length() > 0)
                                commandReturnText += "\n" + line;
                            else
                                commandReturnText += line;

                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        process.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    String message = "[Error] Failed command: $ " + commandFull + "\n[Error] " + e.getMessage();
                    throw new BadCustomCommandGroupException(message);
                }

            }

        return commandReturnText;
    }

    /**
     * Print text in the console.
     *
     * @param message The message to print.
     */
    private static void systemOut(String message) {
        if (message != null)
            if (message.trim().length() > 0)
                System.out.println(message);
    }

}
