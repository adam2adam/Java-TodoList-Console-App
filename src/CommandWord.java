/**
 * Representations for all the valid command words for app
 * along with a string in a particular language.
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.
    ADD("add"), REMOVE("remove"), EDIT("edit"),DONE("done"),
    CREATE("create"), READ("read"), WRITE("write"), SAVE("save"), QUIT("quit"), HELP("help"), UNKNOWN("?");

    // The command string.
    private String commandString;

    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }

    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
