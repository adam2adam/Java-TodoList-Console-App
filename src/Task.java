import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;


public class Task {
    private   int ID;
    private boolean hasDone;
    private String nameProject;
    private String titleTask;
    private Date dateCreated;
    private Date dateDueDate;

    //public ArrayList<Task> listOfTasks = new ArrayList<>();
    public TaskList objTaskList = new TaskList();

    public Task(){
        //ID =
        hasDone = false;
        nameProject = "new Project";
        titleTask = "new Title";
        //dateCreated = LocalDate.now();
        //dateDueDate = LocalDate.now();
        dateCreated = new Date();
        dateDueDate = new Date();
    }

    public Task(int givenID, String givenNameProject, String givenTitleTask, Date givenDueDate){
        this.ID = givenID;
        boolean hasDone = false;
        this.nameProject = givenNameProject;
        this.titleTask = givenTitleTask;
        //dateCreated = LocalDate.now();
        this.dateCreated = new Date();
        this.dateDueDate = givenDueDate;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.ID);
        sb.append("\t");
        sb.append(this.hasDone);
        sb.append("\t");
        sb.append(this.nameProject);
        sb.append("\t");
        sb.append(this.titleTask);
        sb.append("\t");
        sb.append(this.dateCreated.toString());
        sb.append("\t");
        sb.append(this.dateDueDate.toString());
        //String returnString = Integer.toString(this.ID) + "\t" + Boolean.toString(this.hasDone) + "\t" + this.nameProject + "\t" + this.titleTask + "\t" +
        //        this.dateCreated.toString() + "\t" + this.dateDueDate.toString();
        String returnString = sb.toString();
        return returnString;
    }
/*

    public void addTaskToList(){

        System.out.println("Task.Add() method to add TaskList");
        objTaskList.addTaskToList(this);
    }
*/

    public static TaskList fromStrToObjTaskList(String strFileContent){
        Task myTask = new Task();
        //System.out.println("Init myTask");
        TaskList myTaskList = new TaskList();
        //System.out.println("Init myTaskList");
        String[] strLines = strFileContent.substring(0,strFileContent.length()).split("\n");
        String[] tokens;
        //DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.US);
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SimpleDateFormat myOutputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

        for (String str : strLines){
            //System.out.println("--> LINE--> " + str);
            if(str.length() != 0){
                tokens = str.substring(0, str.length()).split("\t");
                /*
                System.out.println("* TOKENS[0] " + tokens[0]);
                System.out.println("* TOKENS[1] " + tokens[1]);
                System.out.println("* TOKENS[2] " + tokens[2]);
                System.out.println("* TOKENS[3] " + tokens[3]);
                System.out.println("* TOKENS[4] " + tokens[4]);
                System.out.println("* TOKENS[5]:!" + tokens[5] + "!");
                */
                //LocalDate testDate = LocalDate.parse(tokens[5], myFormat);
                try {
                    Date testDate = myOutputFormat.parse(tokens[5]);

                    //System.out.println("!!!!TEST DATE ---- " + testDate.toString()); //myFormat.format(testDate));

                    myTask.ID = Integer.parseInt(tokens[0]);
                    myTask.hasDone = Boolean.parseBoolean(tokens[1]);
                    myTask.nameProject = tokens[2];
                    myTask.titleTask = tokens[3];
                    myTask.dateCreated = myOutputFormat.parse(tokens[4]);
                    myTask.dateDueDate = myOutputFormat.parse(tokens[5]);
                    myTaskList.addTaskToList(myTask);
                    //System.out.println("TASKLIST: \n" + myTaskList.toString());
                    myTask = new Task();

                }catch (ParseException e) {
                    System.out.println("Parse Error!!!");
                }
            }
        }

        return myTaskList;

    }

/*
    */
/*Comparator for getting max id value*//*

    public static Comparator<Task> maxIDComparator = new Comparator<Task>() {

        public int compare(Task t1, Task t2) {
            String projectName1 = t1.getNameProject().toUpperCase();
            String projectName2 = t2.getNameProject().toUpperCase();

            //ascending order
            return projectName1.compareTo(projectName2);

            //descending order
            //return projectName2.compareTo(projectName1);
        }};

*/

    /*Comparator for sorting the list by Project Name*/
    public static Comparator<Task> projectNameComparator = new Comparator<Task>() {

        public int compare(Task t1, Task t2) {
            String projectName1 = t1.getNameProject().toUpperCase();
            String projectName2 = t2.getNameProject().toUpperCase();

            //ascending order
            return projectName1.compareTo(projectName2);

            //descending order
            //return projectName2.compareTo(projectName1);
        }};

    /*Comparator for sorting the list by Due Date*/
    public static Comparator<Task> dueDateComparator = new Comparator<Task>() {

        public int compare(Task t1, Task t2) {
            if (t1.dateDueDate == null || t2.dateDueDate == null)
                return 0;
            return t1.dateDueDate.compareTo(t2.dateDueDate);
            //ascending order
            //return projectName1.compareTo(projectName2);

            //descending order
            //return projectName2.compareTo(projectName1);
        }};



    public void Remove(){
        System.out.println("Task.Remove() method");
    }

/*    public void Edit(){
        System.out.println("Task.Edit() method");
    }
*/
/*    public void Done(){
        System.out.println("Task.Done() method");
    }
*/
    public String getNameProject(){
        return this.nameProject;
    }
    public String getTitleTask(){return this.titleTask;}
    public Date getDueDate(){return this.dateDueDate;}
    public boolean getHasDone(){return this.hasDone;}


    public void setNameProject(String newNameProject){
        System.out.println("Task.setNameProject() method");
        nameProject = newNameProject;
    }

    public void setTitleTask(String newTitleTask){
        System.out.println("Task.setTitleTask() method");
        titleTask = newTitleTask;
    }

    public void setDateDueDate(Date newDueDate){
        System.out.println("Task.setDateDueDate() method");
        dateDueDate = newDueDate;
    }

    public void setHasDone(){
        System.out.println("Task.hasDone() method");
        hasDone = !hasDone;
    }

    public int getID(){
        return this.ID;
    }

}
