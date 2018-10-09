/**
 * Representations for all the valid command words for app
 */
public enum CommandWord
{
    // A value for each command word along with its corresponding user interface string.
    //REMOVE("remove"), CREATE("create"), WRITE("write"), DONE("done"),
    READ("read"), ADD("add"), EDIT("edit"), SAVE("save"), HELP("help"), QUIT("quit"), UNKNOWN("?");

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
