import java.util.HashMap;

/**
 * This class holds an enumeration of all command words known to the app.
 * It is used to recognise commands as they are typed in.
 */

public class CommandWords
{
    // A mapping between a command word and the CommandWord
    // associated with it.
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }

    /**
     * Check whether a given String is a valid command word.
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll()
    {
        for(String command : validCommands.keySet()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }

    public void showCommandsMenu(){
        //this.showAll();
        System.out.println("read:   Read Task List from File. The following option is available:\n\t-d\tby Date\n\t-p\tby Project Name\n\t-l\tlist unsaved Task List(From memory!!!)");
        System.out.println("add:    Add New Task");
        System.out.println("edit:   Edit Task. The following option is available:\n\t-u\tfor update\n\t-m\tmark as done\n\t-r\tremove");
        System.out.println("save:   Save Task List to File");
        System.out.println("help:   Help");
        System.out.println("quit:   Quit");
    }
}
