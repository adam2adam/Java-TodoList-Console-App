public class Main {

    //**************** MAIN *****************
    public static void main(String[] args) {
        ToDoApp objTDoApp = new ToDoApp();

        //System.out.println("Welcome to ToDoly");


        //create new file if it doesn't exist
        ToDoApp.checkAndCreateFile();
        //start to interact with user
        ToDoApp.interactionWithUser();


    }

    //**************** MAIN *****************
}
