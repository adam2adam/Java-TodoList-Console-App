import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ToDoApp {
    private static Parser parser;
    private static Scanner reader;
    private static FileOps fileToDoList = new FileOps();
    private static Task createdNewTask;
    private static Task toUpdateTask;
    private static TaskList myTaskList;
    private static int maxID;


    public ToDoApp() {
        parser = new Parser();
        reader = new Scanner(System.in);
        createdNewTask = new Task();
        myTaskList = new TaskList();
    }
    /**
    * Check if file already exists
    */
     public static void checkAndCreateFile() {
        //check file
        //System.out.println("Checking File");

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
     * Print out the opening message
     */
    private static void printWelcome() {
        int hasDoneFalse = 0, hasDoneTrue = 0;
        System.out.println("Welcome to the EveryDay Tasks List Application");
        String testFileReadContent = fileToDoList.ReadFile();
        //myTaskList = createdNewTask.fromStrToObjTaskList(testFileReadContent);
        myTaskList = Task.fromStrToObjTaskList(testFileReadContent);
        for(int i=0;i<myTaskList.getListSize();i++){
            if(myTaskList.listOfTasks.get(i).getHasDone() == false)
                hasDoneFalse++;
            else
                hasDoneTrue++;
        }
        System.out.println("--¢> You have " + hasDoneFalse + " tasks todo and " + hasDoneTrue + " tasks are done!\n" );
        parser.showCommands();
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
    }

    /**
     * Doing the main menu operations
     * @param command
     * @return
     */
    private static boolean processCommand(Command command) {
        boolean wantToQuit = false;
        CommandWord commandWord = command.getCommandWord();
        String secondWord = command.getSecondWord();
        //System.out.println("CommandWord: '" + commandWord.toString() + "'\n -->SecondWord: '" + secondWord + "'");
        String strFileReadContent = fileToDoList.ReadFile();

        switch (commandWord) {
            case ADD:
                wantedToAdd(myTaskList);
                break;
            case EDIT:
                //System.out.println("Edit task");
                wantedToEdit(myTaskList, secondWord, strFileReadContent);
                break;
            case READ:
                wantedToRead(myTaskList,secondWord, strFileReadContent);
                break;
            case SAVE:
                //System.out.println("Save to file");
                fileToDoList.WriteFile(myTaskList.toString());
                break;
            case UNKNOWN:
                System.out.println("Unknown Command! Type 'help' for commands list");
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

    /**
     * If add command is selected from the main menu
     * @param myTaskList
     */
    private static void wantedToAdd(TaskList myTaskList){
        //*** Getting Max ID
        //Task maxIDTask = myTaskList.listOfTasks.stream().max(Comparator.comparingInt(m -> m.getID())).get();

        //if(strFileReadContent.equals("") || strFileReadContent.equals(null) || strFileReadContent.isEmpty()){
        if(myTaskList.getListSize() == 0){
            maxID = -1;
        } else {
            maxID = myTaskList.listOfTasks.stream().
                    max(Comparator.comparingInt(m -> m.getID())).get().getID();
            System.out.println("Max valued ID of Tasks: " + maxID);
        }
        //***

        //System.out.println("Create Task");
        createdNewTask = ToDoApp.createTask(maxID);
        System.out.println("TASK CREATED--> " + createdNewTask.toString() + "\n");
        //System.out.println("ID  hasDone ProjectName TaskTitle   DateCreated             DateDue");
        // Add new Task to TaskList
        //System.out.println("Add task to tasklist");
        myTaskList = myTaskList.addTaskToList(createdNewTask);
        System.out.println(myTaskList.toString());
    }

    /**
     * If edit is selected from the main menu
     * @param myTaskList
     * @param secondWord
     * @param strFileReadContent
     */
    private static void wantedToEdit(TaskList myTaskList, String secondWord, String strFileReadContent){
        try {
            // Edit - Update Task (regarding to Task ID)
            if (secondWord.equals("-u")) {
                System.out.println("second word (-u): " + secondWord);
                System.out.println(myTaskList.toString());
                System.out.println("Select the ID of Task that you want to update");
                int updateID = Integer.valueOf(reader.nextLine());
                try {
                    // Find the Task whose ID was entered
                     Optional<Task> t = myTaskList.listOfTasks.stream()
                            .filter(i -> i.getID() == updateID)
                            .findFirst();
                     // Check the entered Task ID - If there is a task whose ID has entered, update
                    if (!t.equals(Optional.empty())) {
                        System.out.println("Please update the task (ID: " + updateID + ")");
                        toUpdateTask = ToDoApp.createTask(updateID);
                        int indexOfTask = myTaskList.listOfTasks.indexOf(t.get());
                        myTaskList.listOfTasks.get(indexOfTask).setNameProject(toUpdateTask.getNameProject());
                        myTaskList.listOfTasks.get(indexOfTask).setTitleTask(toUpdateTask.getTitleTask());
                        myTaskList.listOfTasks.get(indexOfTask).setDateDueDate(toUpdateTask.getDueDate());
                        toUpdateTask = null;
                    // If the entered ID is wrong
                    } else
                        System.out.println("Wrong index! Please try again!");
                } catch (IndexOutOfBoundsException iobedit){
                System.out.println("Wrong index! Please try again!");
            }

            // Edit - Mark as Done
            } else if (secondWord.equals("-m")) {
                System.out.println("second word (-m): " + secondWord);
                System.out.println(myTaskList.toString());
                System.out.println("Select the ID of Task that you want to mar as done or not");
                int markDoneID = Integer.valueOf(reader.nextLine());
                try {
                    // Find the Task whose ID was entered
                    Optional<Task> t = myTaskList.listOfTasks.stream()
                            .filter(i -> i.getID() == markDoneID)
                            .findFirst();
                    // Check the entered Task ID - If there is a task whose ID has entered, mark as Done
                    if ( !t.equals(Optional.empty())) {
                        int indexOfTask = myTaskList.listOfTasks.indexOf(t.get());
                        myTaskList.listOfTasks.get(indexOfTask).setHasDone();
                        System.out.println(myTaskList.toString());
                    }
                    // If the entered ID is wrong
                    else
                        System.out.println("Wrong index! Please try again!");
                } catch (IndexOutOfBoundsException iobedit){
                    System.out.println("Wrong index! Please try again!");
                }
            // Edit - Remove
            } else if (secondWord.equals("-r")) {
                System.out.println("second word (-r): " + secondWord);
                System.out.println(myTaskList.toString());
                System.out.println("Select the ID of Task that you want to remove");
                int removeTaskID = Integer.valueOf(reader.nextLine());
                try {
                    // Find the Task whose ID was entered
                    Optional<Task> t = myTaskList.listOfTasks.stream()
                            .filter(i -> i.getID() == removeTaskID)
                            .findFirst();
                    // Check the entered Task ID - If there is a task whose ID has entered, remove
                    if (!t.equals(Optional.empty())) {
                        int indexOfTask = myTaskList.listOfTasks.indexOf(t.get());
                        myTaskList.listOfTasks.remove(indexOfTask);
                        System.out.println(myTaskList.toString());
                        // If the entered ID is wrong
                    }else
                        System.out.println("Wrong index! Please try again!");
                } catch (IndexOutOfBoundsException iobedit){
                    System.out.println("Wrong index! Please try again!");
                }
                //System.out.println(myTaskList.toString());

            } else {
                System.out.println("second word : " + secondWord + "\t Wrong parameter! Try again. 'help' for the commands list");
            }
        }catch (NullPointerException ex) {
            System.out.println("second word : " + secondWord + "\t Wrong parameter! Try again. 'help' for the commands list");
            //System.out.println(ex);
            //System.out.println("<----- PRINT FILE CONTENT AS STRING ----->");
            //System.out.println(strFileReadContent);
        }
    }

    /**
     * If read command is selected from the main menu
     * @param myTaskList
     * @param secondWord
     * @param strFileReadContent
     */
    private static void wantedToRead(TaskList myTaskList, String secondWord, String strFileReadContent){
        try {
            // Sort tasks by Project Name
            if (secondWord.equals("-p")) {
                //System.out.println("second word (-p): " + secondWord);
                //Sort by Project Name
                Collections.sort(myTaskList.listOfTasks, Task.projectNameComparator);
                System.out.println("<----- PRINT TASK OBJECTS WHICH ARE ON THE AIR SORTED BY PROJECT NAME ----->");
                System.out.println(myTaskList.toString());
            // Sort tasks by Due Date
            } else if (secondWord.equals("-d")) {
                //System.out.println("second word (-d): " + secondWord);
                //Sort by Due Date
                Collections.sort(myTaskList.listOfTasks, Task.dueDateComparator);
                System.out.println("<----- PRINT TASK OBJECTS WHICH ARE ON THE AIR SORTED BY DUE DATE ----->");
                System.out.println(myTaskList.toString());
            } // List the tasks which are in the memory (Not written to file yet!!!)
            else if (secondWord.equals("-l")){
                System.out.println("<----- PRINT TASK OBJECTS WHICH ARE ON THE AIR! ----->");
                System.out.println(myTaskList.toString());
            } //Wrong parameter
            else {
                System.out.println("second word : " + secondWord + "\t Wrong paremeter! Try again");
            }

        }catch (NullPointerException ex) {
            System.out.println("<----- PRINT FILE CONTENT AS STRING ----->");
            System.out.println("|ID|hasDone|ProjectName|TaskTitle|DateCreated|DateDue");
            System.out.println(strFileReadContent);
        }
    }

    /**
     * Create new Task object
     * @param maxID
     * @return
     */
    public static Task createTask(int maxID){
        String nameProject = "";
        String titleTask = "";
        Date dateDueDate = null;
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        // Read the properties of new Task
        while(nameProject.trim().equals("") || nameProject.isEmpty() || nameProject.trim().equals(" ")) {
            System.out.print("What is the Project Name?\n->createTask>>> ");
            nameProject = reader.nextLine();
        }
        while(titleTask.trim().equals("") || titleTask.isEmpty() || titleTask.trim().equals(" ") ) {
            System.out.print("What is the Task Title?\n->createTask>>> ");
            titleTask = reader.nextLine();
        }
        System.out.print("What is the Due Date? (yyyy-MM-ddTHH:mm) Example: " + myFormat.format(new Date()) + "\n->createTask>>> ");
        while (dateDueDate == null) {
            String line = reader.nextLine();
            try {
                dateDueDate = myFormat.parse(line);
            } catch (ParseException e) {
                System.out.println("Sorry, that's not valid. Please try again.");
            }
        }

        //System.out.println("maxID: " + maxID);
        // Create new Task by new values and right Task ID
        Task createdTask = new Task(++maxID, nameProject,titleTask,dateDueDate);
        return createdTask;

    }

    /**
     * Print out help information
     */
    private static void printHelp() {
        parser.showCommands();
    }

    /**
     * "Quit"
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
