package controller;

import java.util.List;

import dto.Task;
import dto.TaskStatus;
import service.ITaskService;
import service.TaskService;

public class ToDoController {

  private ITaskService taskService = new TaskService();

  public void runToDoApp() {
    while (true) {
      showMenu();
      String userAction = getUserInput("Enter the Action Number");
      performAction(Integer.parseInt(userAction));
    }

  }

  private void showMenu() {
    System.out.println("Welcome to the ToDo App!");
    System.out.println("Action Menu: ");
    System.out.println("1. Add a new task");
    System.out.println("2. Update an existing task");
    System.out.println("3. Delete an existing task");
    System.out.println("4. List all Tasks");
    System.out.println("5. Exit");
  }

  private String getUserInput(String inputMessage) {
    System.out.print(inputMessage + " : ");
    return System.console().readLine();
  }

  private void performAction(int action) {
    switch (action) {
      case 1:
        addTask();
        break;
      case 2:
        updateTask();
        break;
      case 3:
        deleteTask();
        break;
      case 4:
        getTasks();
        break;
      case 5:
        System.exit(200);
      default:
        System.out.println("Invalid Action");
    }
  }

  private void showStatusOption() {
    System.out.println("Please Select Your Current Task Status From the Given Options");
    System.out.println("Current Status: ");
    int i = 1;

    for(TaskStatus taskStatus:TaskStatus.values()){
      System.out.println(i+": "+taskStatus);
      i++;
    }
    System.out.println("0. Skip");
  }

  private boolean addTask() {
    String taskName = getUserInput("Enter the Task Name");
    String taskDeadline = getUserInput(
        "Enter the Task Deadline in format as 01-Jan-2024 [Optional, Press Enter to skip] ");

    Task task = new Task(Task.getTaskAutoId(), taskName, TaskStatus.PENDING, taskDeadline);
    return taskService.addTask(task);

  }

  private boolean updateTask() {
    //Select Task to Update
    getTasks();
    String inputId = getUserInput("Enter the Task Id You Want to Update");

    //Select New Status
    showStatusOption();
    String newStatus = getUserInput("Enter the Current Status Number");

    //Enter New Deadline
    String newDeadline = getUserInput("Enter the Task Deadline in format as 01-Jan-2024 [Optional, Press Enter to skip] ");
    
    //Update Task
    try {
      boolean successStatus = taskService.updateTask(inputId, newStatus, newDeadline);
      if(successStatus){
        System.out.println("Task Updated Successfully!");
        return true;
      }
      System.out.println("Unable to Update the Task Due to Internal Error");
      return false;
      
    } 
    catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Unable to Update the Task Due to Invalid Input/Inputs!");
      return false;
    }
    
  }

  private boolean deleteTask() {
    //Select Task to Delete
    getTasks();
    String inputId = getUserInput("Please Enter the Task Id You Want to Delete");

    //Delete the Task
    try {
      boolean successStatus = taskService.deleteTask(inputId);
      if(successStatus){
        System.out.println("Task Deleted Successfully!");
        return true;
      }
      System.out.println("Unable to Delete the Task Due to Internal Error");
      return false;

    } 
    catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("Unable to Delete the Task Due to Invalid Input/Inputs!");
      return false;
    }
  }

  private void getTasks() {
    List<Task> tasks = taskService.getTasks();
    System.out.println("-------------------------------------------------------------------------------");
    System.out.println("ID" + " | " + "TASK NAME" + " | " + "TASK STATUS" + " | " + "DEADLINE");
    System.out.println("-------------------------------------------------------------------------------");
    tasks.forEach(task -> {
      System.out.println(task.getTaskId() + " | " + task.getTaskName() + " | " + task.getTaskStatus() + " | "
          + task.getTaskDeadline());
    });
    System.out.println("-------------------------------------------------------------------------------");
  }

}
