import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    TaskList testTaskList;

    @BeforeEach
    void setUp() {
        testTaskList = new TaskList();
    }

    @AfterEach
    void tearDown() {
    }

    /**
     * Aren't 'new TaskList' objects Null?
     */
    @Test
    public void assertTaskNotNullTest(){
        assertNotNull(new TaskList(),"Newly created Task should not be null");
    }

    /**
     * Test if 'addTaskToList()' methods works properly
     */
    @Test
    void addTaskToListTest() {
        for(int i = 0; i < 10; i++) {
            testTaskList.addTaskToList(new Task());
        }

        assertEquals(testTaskList.listOfTasks.size(),testTaskList.getListSize());
    }

    /**
     * Test if 'getListSize()' method works properly
     */
    @Test
    void getListSizeTest() {
        int expectedSize = 3;

        for(int i=0; i<3; i++){
            testTaskList.addTaskToList(new Task());
        }

        assertEquals(expectedSize, testTaskList.getListSize());
    }
}