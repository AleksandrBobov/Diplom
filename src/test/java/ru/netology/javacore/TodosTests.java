package ru.netology.javacore;
import org.junit.jupiter.api.*;

public class TodosTests {

  private static long suiteStartTime;
  private long testStartTime;

  @BeforeAll
  public static void initSuite() {
    System.out.println("Start Tests");
    suiteStartTime = System.nanoTime();
  }

  @AfterAll
  public static void completeSuite() {
    long output = System.nanoTime() - suiteStartTime;
    System.out.println("Test Finished:" + output / 1000000 + " milliseconds");
  }

  @BeforeEach
  public void initTest() {
    System.out.println("Starting new nest");
    testStartTime = System.nanoTime();
  }

  @AfterEach
  public void finalizeTest() {
    long output = System.nanoTime() - testStartTime;
    System.out.println("Test Passed " + output / 1000000 + " milliseconds");
  }

  @Test
  public void addTaskTest() {
    Todos todos = new Todos();
    todos.addTask("Test");
    boolean condition = todos.getAllTasks().contains("Test");
    Assertions.assertTrue(condition);
  }

  @Test
  public void addTaskFalseTest() {
    Todos todos = new Todos();
    todos.addTask("Test");
    boolean condition = todos.getAllTasks().contains("Тест");
    Assertions.assertFalse(condition);
  }

  @Test
  public void removeTaskTest() {
    Todos todos = new Todos();
    todos.addTask("Test");
    todos.removeTask("Test");
    boolean condition = todos.getAllTasks().contains("Test");
    Assertions.assertFalse(condition);
  }

  @Test
  public void getAllTasksTest() {
    Todos todos = new Todos();
    todos.addTask("Test");
    String result = todos.getAllTasks();

    Assertions.assertEquals(result, todos.getAllTasks());
  }

}