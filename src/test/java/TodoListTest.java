import org.example.TodoList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TodoListTest {
    private TodoList todoList;

    @BeforeEach
    void setUp() {
        todoList = new TodoList();
    }

    @Test
    void addAndList() {
        todoList.add(" task1 ");
        assertEquals(1, todoList.size());
        assertEquals("task1", todoList.getAll().get(0).getText());
    }

    @Test
    void remove() {
        todoList.add("a");
        todoList.add("b");
        assertTrue(todoList.remove(0));
        assertEquals(1, todoList.size());
        assertFalse(todoList.remove(10));
    }

    @Test
    void addEmptyIgnored() {
        todoList.add(" ");
        assertEquals(0, todoList.size());
    }

    @Test
    void clear() {
        todoList.add("task1");
        todoList.add("task2");
        todoList.add("task3");
        assertEquals(3, todoList.size());
        todoList.clear();
        assertEquals(0, todoList.size());
        assertTrue(todoList.getAll().isEmpty());
    }

    @Test
    void clearEmptyList() {
        todoList.clear();
        assertEquals(0, todoList.size());
    }

    @Test
    void markAsDone() {
        todoList.add("task1");
        todoList.add("task2");
        assertFalse(todoList.getAll().get(0).isDone());
        assertTrue(todoList.markAsDone(0));
        assertTrue(todoList.getAll().get(0).isDone());
        assertFalse(todoList.getAll().get(1).isDone());
    }

    @Test
    void markAsDoneInvalidIndex() {
        todoList.add("task1");
        assertFalse(todoList.markAsDone(-1));
        assertFalse(todoList.markAsDone(1));
        assertFalse(todoList.markAsDone(100));
    }

    @Test
    void search() {
        todoList.add("Buy milk");
        todoList.add("Buy bread");
        todoList.add("Call doctor");
        todoList.add("Buy eggs");

        List<TodoList.Task> results = todoList.search("buy");
        assertEquals(3, results.size());
        assertTrue(results.stream().allMatch(task -> 
            task.getText().toLowerCase().contains("buy")));
    }

    @Test
    void searchCaseSensitive() {
        todoList.add("Java programming");
        todoList.add("Python scripting");
        todoList.add("JavaScript coding");

        List<TodoList.Task> results = todoList.search("java");
        assertEquals(2, results.size());
    }

    @Test
    void searchNoResults() {
        todoList.add("task1");
        todoList.add("task2");

        List<TodoList.Task> results = todoList.search("xyz");
        assertTrue(results.isEmpty());
    }

    @Test
    void searchEmptyQuery() {
        todoList.add("task1");
        List<TodoList.Task> results = todoList.search("");
        assertTrue(results.isEmpty());
    }

    @Test
    void searchNullQuery() {
        todoList.add("task1");
        List<TodoList.Task> results = todoList.search(null);
        assertTrue(results.isEmpty());
    }

    @Test
    void taskToString() {
        todoList.add("test task");
        TodoList.Task task = todoList.getAll().get(0);
        assertTrue(task.toString().contains("test task"));
        assertTrue(task.toString().contains("[ ]"));
        assertFalse(task.isDone());
        
        todoList.markAsDone(0);
        assertTrue(task.isDone());
        assertTrue(task.toString().contains("[X]"));
    }

    @Test
    void addNullIgnored() {
        todoList.add(null);
        assertEquals(0, todoList.size());
    }
}
