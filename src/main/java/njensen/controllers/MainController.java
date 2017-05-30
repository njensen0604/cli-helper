package njensen.controllers;


import njensen.configurations.CliCommands;
import njensen.configurations.Configurations;
import njensen.exceptions.BadCustomCommandGroupException;
import njensen.exceptions.BadPointerForCommandGroupException;
import njensen.tools.RunTerminalCommand;
import njensen.tools.UserInput;

import java.util.ArrayList;

/**
 * The main controller for the app.
 *
 * @author Nicholas Jensen on 05/27/2017.
 */
public class MainController {

    // to handle user inputs
    private UserInput userInput;

    // commands
    private CliCommands cliCommands;

    public MainController() {
        initProcess();
    }

    /**
     * The main process for the program / controller.
     */
    public void initProcess() {

        systemOut("== " + Configurations.getApplicationName() + " ==");

        promptTheUser();

    }

    /**
     * @return The object for the CLI Commands
     */
    public CliCommands getCliCommands() {
        if (cliCommands == null) cliCommands = new CliCommands();
        return cliCommands;
    }

    /**
     * @return The object for managing the user input
     */
    public UserInput getUserInput() {
        if (userInput == null) userInput = new UserInput();
        return userInput;
    }

    /**
     * Get user input from the command line.
     */
    private void promptTheUser() {

        String question = "Which command group to run [List, View <#>, Exit]? ";

        while (true) {

            getUserInput().getInputFromUser(question, false);

            // exit the program
            if (getUserInput().getEnteredTextLowercase(true).equals("e")
                    || getUserInput().getEnteredTextLowercase(true).equals("x")
                    || getUserInput().getEnteredTextLowercase(true).equals("exit")
                    || getUserInput().getEnteredTextLowercase(true).equals("c")
                    || getUserInput().getEnteredTextLowercase(true).equals("cancel")
                    || getUserInput().getEnteredTextLowercase(true).equals("")) {
                systemOut("Good bye.");
                break;
            }

            // show the list of available command groups
            if (getUserInput().getEnteredTextLowercase(true).equals("s")
                    || getUserInput().getEnteredTextLowercase(true).equals("sl")
                    || getUserInput().getEnteredTextLowercase(true).equals("see list")
                    || getUserInput().getEnteredTextLowercase(true).equals("seelist")
                    || getUserInput().getEnteredTextLowercase(true).equals("see")
                    || getUserInput().getEnteredTextLowercase(true).equals("l")
                    || getUserInput().getEnteredTextLowercase(true).equals("list")) {

                int index = 0;
                for (String s : getCliCommands().getGroupNames()) {
                    systemOut(++index + ". " + s);
                }

                continue;
            }

            // view commands in a group
            if (getUserInput().getEnteredTextLowercase(true).split(" ")[0].equals("v")
                    || getUserInput().getEnteredTextLowercase(true).split(" ")[0].equals("view")) {

                String[] commandSplit = getUserInput().getEnteredTextLowercase(true).split(" ");
                if (commandSplit.length > 1) {

                    String commandGroupNumber = commandSplit[1];

                    try {

                        int commandGroupIndex = (Integer.parseInt(commandGroupNumber)) - 1;

                        String groupName = getCliCommands().getGroupName(commandGroupIndex);
                        if (groupName != null)
                            systemOut(commandGroupNumber + ". " + groupName);

                        ArrayList<String> commands = null;
                        try {
                            commands = getCliCommands().getCommandsForGroup(commandGroupIndex);
                            if (commands != null) {


                                for (String c : commands) {

                                    systemOut("$ " + c);

                                }

                            }
                        } catch (BadPointerForCommandGroupException e) {
                            systemOut(e.getMessage());
                        }

                    } catch (NumberFormatException e) {
                        systemOut("Enter the number for the command group you want.");
                    }

                }

                continue;
            }

            // run command group
            if (!getUserInput().getEnteredTextLowercase(true).equals("")) {

                String commandGroupNumber = getUserInput().getEnteredTextLowercase(true);

                try {

                    int commandGroupIndex = (Integer.parseInt(commandGroupNumber)) - 1;

                    ArrayList<String> commands = null;
                    try {
                        commands = getCliCommands().getCommandsForGroup(commandGroupIndex);
                        if (commands != null) {

                            for (String c : commands) {

                                // run the command

                                String[] cs = c.split(" ");
                                try {
                                    RunTerminalCommand.send(cs);
                                } catch (BadCustomCommandGroupException e) {
                                    systemOut(e.getMessage());
                                    break;
                                }

                            }

                        }
                    } catch (BadPointerForCommandGroupException e) {
                        systemOut(e.getMessage());
                    }

                } catch (NumberFormatException e) {
                    systemOut("Enter the number for the command group you want.");
                }

                continue;
            }

        }

    }

    /**
     * Print text in the console.
     *
     * @param message The message to print.
     */
    private void systemOut(String message) {
        if (message != null)
            if (message.trim().length() > 0)
                System.out.println(message);
    }

}
