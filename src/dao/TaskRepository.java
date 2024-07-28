package dao;

import java.util.ArrayList;
import java.util.List;

import dto.Task;
import dto.TaskStatus;

public class TaskRepository {

    public static List<Task> taskDB = new ArrayList<>();

    public boolean addTask(Task task) {
        return taskDB.add(task);
    }

    public List<Task> getTasks() {
        return taskDB;
    }

    public boolean updateTask(Task task, TaskStatus taskStatus, String deadline) {

        task.setTaskStatus(taskStatus);
        task.setTaskDeadline(deadline);
        return true;
    }

    public boolean deleteTask(Task task){
        return taskDB.remove(task);
    }

}
