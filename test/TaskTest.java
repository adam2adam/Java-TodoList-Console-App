import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TaskTest {
    private Task testTask;
    TaskList testTaskList = new TaskList();
    SimpleDateFormat myOutputFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");


    @BeforeEach
    void setUp() {
        try {
            this.testTask = new Task(99, "Default Project Name", "Default Title",myOutputFormat.parse("Sat Nov 11 11:11:00 CET 1111"),myOutputFormat.parse("Sat Nov 11 11:11:00 CET 1111"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Aren't 'new Task()' objects Null?
     */
    @Test
    public void assertTaskNotNullTest(){
        assertNotNull(new Task(),"Newly created Task should not be null");
    }

    /**
     * Test method to test the toString() method of Task Class
     */
    @Test
    void toStringTaskTest() {
        String strExpected = "99\tfalse\tDefault Project Name\tDefault Title\tSat Nov 11 11:11:00 CET 1111\tSat Nov 11 11:11:00 CET 1111";
        assertEquals(strExpected,Task.getDefaultInstance().toString());
    }

    /**
     * Test method of Task.fromStrToObjTaskList() method
     */
    @Test
    void fromStrToObjTaskListTest() {
        String str = "99\tfalse\tDefault Project Name\tDefault Title\tSat Nov 11 11:11:00 CET 1111\tSat Nov 11 11:11:00 CET 1111";
        TaskList tmpTaskList = Task.fromStrToObjTaskList(str);
        testTaskList.addTaskToList(this.testTask);

        //assertEquals(this.testTask,Task.getDefaultInstance());
        assertEquals(testTaskList.listOfTasks,tmpTaskList.listOfTasks);

    }

}