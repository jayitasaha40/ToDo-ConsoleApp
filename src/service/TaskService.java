package service;

import java.util.List;

import dao.TaskRepository;
import dto.Task;
import dto.TaskStatus;

public class TaskService implements ITaskService {
    
    TaskRepository taskRepository;

    public TaskService() {
        this.taskRepository = new TaskRepository();
    }

    @Override
    public boolean addTask(Task task) {
        return taskRepository.addTask(task);
        // throw new UnsupportedOperationException("Unimplemented method 'addTask'");
    }

    @Override
    public boolean updateTask(String inputId, String newInputStatus, String newDeadline) throws IllegalArgumentException {

        //Validate inputId Format
        int taskId = -1;
        try {
            taskId = Integer.parseInt(inputId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Task Id: TaskId Should be a Number!");
        }

        //Check ID Present or Not
        List<Task> tasks = getTasks();
        if(tasks.size()<taskId || taskId <= 0){
            throw new IllegalArgumentException("Invalid Input: Incorrect Task ID!");
        }
        Task task = tasks.get(taskId-1);

        //Validate Status Number Format
        int newStatusId = -1;
        try {
            newStatusId = Integer.parseInt(newInputStatus);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Task Status: Status Index Should be a Number!");
        }

        //Check Correct Status Selected
        TaskStatus[] allTaskStatus = TaskStatus.values();
        TaskStatus newStatus;
        if(newStatusId == 0){
            //Previous Status
            newStatus = task.getTaskStatus();
        }
        else if(newStatusId <0 || allTaskStatus.length<newStatusId){
            throw new IllegalArgumentException("Invalid Input: Incorrect Status Number!");
        }
        else{
            newStatus = allTaskStatus[newStatusId-1];
        }
        
        //Get Deadline
        if(!newDeadline.matches("^\\d{2}-[a-zA-Z]{3}-\\d{4}$")){
            //Previous Deadline
            newDeadline = task.getTaskDeadline();
        }

         //Update Task in Repo
         return taskRepository.updateTask(task, newStatus, newDeadline);


        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'updateTask'");
    }

    @Override
    public boolean deleteTask(String inputTaskId) {
        //Validate inputId Format
        int taskId = -1;
        try {
            taskId = Integer.parseInt(inputTaskId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Task Id: TaskId Should be a Number!");
        }

        //Check ID Present or Not
        List<Task> tasks = getTasks();
        if(tasks.size()<taskId || taskId <= 0){
            throw new IllegalArgumentException("Invalid Input: Incorrect Task ID!");
        }
        Task task = tasks.get(taskId-1);

        //Delete the Task
        return taskRepository.deleteTask(task);
        
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'deleteTask'");
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.getTasks();
        // throw new UnsupportedOperationException("Unimplemented method 'showTasks'");
    }
    
}
