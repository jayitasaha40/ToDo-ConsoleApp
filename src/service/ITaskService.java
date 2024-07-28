package service;

import java.util.List;

import dto.Task;

public interface ITaskService {

    public boolean addTask(Task task);

    public boolean updateTask(String taskId, String newStatus, String newDeadline);

    public boolean deleteTask(String inputTaskId);

    public List<Task> getTasks();

}
