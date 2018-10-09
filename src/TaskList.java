import java.util.ArrayList;
import java.util.Date;

public class TaskList {
    public ArrayList<Task> listOfTasks;// = new ArrayList<>();

    public TaskList(){
        listOfTasks = new ArrayList<>();
    }

    @Override
    public String toString(){
        String returnString = "";
        System.out.println("|ID|hasDone|ProjectName|TaskTitle|DateCreated|DateDue|");
        for(Task tmpTask:listOfTasks)
            returnString = returnString + tmpTask.toString() + "\n";
        return returnString;
    }

    public TaskList addTaskToList(Task newTask){
        //System.out.println("TaskList.Add() method");
        listOfTasks.add(newTask);
        return this;
    }

    public void Remove(){
        System.out.println("TaskList.Remove() method");
    }

    public void Edit(){
        System.out.println("TaskList.Edit() method");
    }

    public void Done(){
        System.out.println("TaskList.Done() method");
    }

    public int getListSize(){
        return listOfTasks.size();
    }
}
