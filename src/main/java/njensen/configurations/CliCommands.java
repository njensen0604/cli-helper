package njensen.configurations;

import njensen.exceptions.BadPointerForCommandGroupException;

import java.util.ArrayList;

/**
 * All the available groups of commands.
 * * Set command in the constructor *
 *
 * @author Nicholas Jensen on 05/27/2017.
 */
public class CliCommands {

    // the order of commands to run
    private ArrayList<String> groupPointers;
    private ArrayList<String> groupNames;

    // list of commands available
    private ArrayList<String> commands;

    public CliCommands() {

        // variables for use building commands
        String userName = System.getProperty("user.name");
        String userHome = System.getProperty("user.home");
        String publicWorkingDirectory = System.getProperty("user.dir");

        // set all command groups and names
        addGroup("test group", "0:1:2:3");

        // set all available commands
        getCommands().add("pwd");//0
        getCommands().add("whoami");
        getCommands().add("ls -l");
        getCommands().add("echo 'thanks for testing this'");

    }

    /**
     * Create a group of commands.
     *
     * @param name     Name of command group.
     * @param pointers Pointers to commands.
     */
    private void addGroup(String name, String pointers) {
        if (name != null && pointers != null) {
            getGroupNames().add(name);
            getGroupPointers().add(pointers);
        }
    }

    /**
     * @return List of pointers for the groups.
     */
    private ArrayList<String> getGroupPointers() {
        if (groupPointers == null
                || groupNames == null) {
            groupPointers = new ArrayList<>();
            groupNames = new ArrayList<>();
        }
        return groupPointers;
    }

    /**
     * @return List of pointers for the groups.
     */
    private String[] getPointersForGroup(int index) {

        if (index > -1) {

            if (index < getGroupPointers().size()) {

                String[] pointers = getGroupPointers().get(index).split(":");

                return pointers;
            }

        }

        return null;
    }

    /**
     * @return List of names for command groups.
     */
    public ArrayList<String> getGroupNames() {
        if (groupPointers == null
                || groupNames == null) {
            groupPointers = new ArrayList<>();
            groupNames = new ArrayList<>();
        }
        return groupNames;
    }

    /**
     * @param index The index of the group.
     * @return Name for a specific command group.
     */
    public String getGroupName(int index) {

        if (index > -1) {

            if (index < getGroupNames().size()) {
                return getGroupNames().get(index);
            }

        }

        return null;

    }

    /**
     * @return List of all commands available to choose from.
     */
    private ArrayList<String> getCommands() {
        if (commands == null) {
            commands = new ArrayList<>();
        }
        return commands;
    }

    /**
     * @param index Index for a command.
     * @return A single command as a string.
     */
    public String getCommand(int index) {
        if (index > -1) {
            if (index < getCommands().size()) {
                return getCommands().get(index);
            }
        }
        return null;
    }

    /**
     * Get all the commands in the group.
     *
     * @param index Index of the group in groupPointers.
     * @return List of all the commands in the group.
     * @throws BadPointerForCommandGroupException The pointer does not point to a index on commands list.
     */
    public ArrayList<String> getCommandsForGroup(int index) throws BadPointerForCommandGroupException {
        if (index > -1) {
            if (index < getGroupNames().size()) {

                String[] pointers = getPointersForGroup(index);

                ArrayList<String> commands = new ArrayList<>();

                for (String p : pointers) {

                    try {

                        int pAsInt = Integer.parseInt(p);

                        String command = getCommand(pAsInt);

                        if (command == null) {
                            throw new BadPointerForCommandGroupException("");
                        } else {
                            commands.add(command);
                        }

                    } catch (NumberFormatException e) {
                        throw new BadPointerForCommandGroupException("[Error] '" + p + "' is malformed.");
                    }

                }

                return commands;
            }
        }
        return null;
    }

}
