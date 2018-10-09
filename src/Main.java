public class Main {

    //**************** MAIN *****************
    public static void main(String[] args) {
        ToDoApp objTDoApp = new ToDoApp();
        /**
         * create new file if it doesn't exist
         */
        ToDoApp.checkAndCreateFile();
        /**
         * start to interact with user
         */
        ToDoApp.interactionWithUser();


    }

    //**************** MAIN *****************
}
