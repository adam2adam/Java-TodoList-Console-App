import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Scanner;

public class ToDoApp {
    private static Parser parser;
    private static Scanner reader;
    private static FileOps fileToDoList = new FileOps();
    private static Task createdNewTask;
    private static TaskList myTaskList;

    public ToDoApp() {
        parser = new Parser();
        reader = new Scanner(System.in);
        createdNewTask = new Task();
        myTaskList = new TaskList();
    }

    // Check if file already exists
    public static void checkAndCreateFile() {
        //check file
        System.out.println("Checking File");

        //create new file if it doesn't exist
        if (fileToDoList.CheckFileExists() == false)
            fileToDoList.CreateFile();
    }


    /**
     * Main interactionWithUser routine.  Loops until end of interaction.
     */
    public static void interactionWithUser() {
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for using this application.  Good bye.");
    }

    /**
     *
     */

    /**
     * Print out the opening message for the player.
     */
    private static void printWelcome() {
        int hasDoneFalse = 0, hasDoneTrue = 0;
        System.out.println("Welcome to the EveryDay Tasks List Application");
        String testFileReadContent = fileToDoList.ReadFile();
        myTaskList = createdNewTask.fromStrToObjTaskList(testFileReadContent);
        for(int i=0;i<myTaskList.getListSize();i++){
            if(myTaskList.listOfTasks.get(i).hasDone == false)
                hasDoneFalse++;
            else
                hasDoneTrue++;
        }

        //System.out.println("You have " + myTaskList.getListSize() + " tasks");
        System.out.println("You have " + hasDoneFalse + " tasks todo and " + hasDoneTrue + " tasks are done!" );
        parser.showCommands();
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        //System.out.println(currentRoom.getLongDescription());
    }

    private static boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case ADD:
                System.out.println("Create Task");
                createdNewTask = ToDoApp.createTask();
                System.out.println("TASK CREATED: " + createdNewTask.toString());

                System.out.println("Add task or tasklist");
                //createdNewTask.addTaskToList();
                myTaskList.addTaskToList(createdNewTask);
                //myTaskList.addTaskToList(createdNewTask);
                System.out.println(myTaskList.toString());

                //fileToDoList.WriteFile("TEEEEST!!!!\n");
                break;
            case REMOVE:
                System.out.println("Remove task or tasklist");
                break;
            case EDIT:
                System.out.println("Edit task pr tasklist");
                break;
            case DONE:
                System.out.println("Done");
                break;
//            case CREATE:
//                break;
            case READ:
                System.out.println("Read task or task list");
                //System.out.println(fileToDoList.ReadFile());

                String testFileReadContent = fileToDoList.ReadFile();
                System.out.println("<----- PRINT FILE CONTENT AS STRING ----->");
                System.out.println(testFileReadContent);
                //fileToDoList.fromStrToObjTaskList(testFileReadContent);
                myTaskList = createdNewTask.fromStrToObjTaskList(testFileReadContent);
                //Sort by Project Name
                Collections.sort(myTaskList.listOfTasks, Task.projectNameComparator);
                System.out.println("<----- PRINT FILE CONTENT AS TASK OBJECTS SORTED BY PROJECT NAME ----->");
                System.out.println(myTaskList.toString());
                //Sort by Due Date
                Collections.sort(myTaskList.listOfTasks, Task.dueDateComparator);
                System.out.println("<----- PRINT FILE CONTENT AS TASK OBJECTS SORTED BY DUE DATE ----->");
                System.out.println(myTaskList.toString());
                break;
/*
            case WRITE:
                System.out.println("Write task");
                break;
*/
            case SAVE:
                System.out.println("Save to file");
                fileToDoList.WriteFile(myTaskList.toString());
                break;
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    private static Task createTask(){
        int maxID;
        //boolean hasDone = false;
        String nameProject;
        String titleTask;
        //Date dateCreated = new Date();
        Date dateDueDate = null;
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.US);
/*
        Task tmpMaxID = Collections.max(myTaskList.listOfTasks, Comparator.comparingInt(Task::getID));
        tmpMaxID = Collections.max(myTaskList.listOfTasks);
        maxID = tmpMaxID.getID();
*/
        System.out.print("What is the project name?\n->createTask>>> ");
        nameProject = reader.nextLine();
        System.out.print("What is the task title?\n->createTask>>> ");
        titleTask = reader.nextLine();
        System.out.print("What is the due date? (yyyy-MM-ddTHH:mm) Example: " + myFormat.format(new Date()) + "\n->createTask>>> ");
        while (dateDueDate == null) {
            String line = reader.nextLine();
            try {
                dateDueDate = myFormat.parse(line);
                //dateDueDate = LocalDate.parse(line, myFormat);
            } catch (ParseException e) {
                System.out.println("Sorry, that's not valid. Please try again.");
            }
        }

        Task createdTask = new Task(1, nameProject,titleTask,dateDueDate);
        return createdTask;

    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private static void printHelp() {
        // System.out.println("You are lost. You are alone. You wander");
        //System.out.println("around at the university.");
        //System.out.println();
        //System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private static boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }

}
