package dto;

public class Task {
    private int taskId;
    private String taskName;
    private TaskStatus taskStatus;
    private String deadline;

    private static int taskAutoId = 0;

    public Task(int taskId, String taskName, TaskStatus taskStatus, String deadline) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
        this.deadline = deadline;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public String getTaskDeadline() {
        return deadline;
    }

    public static int getTaskAutoId() {
        taskAutoId++;
        return taskAutoId;
    }

    public void setTaskStatus(TaskStatus taskStatus){
        this.taskStatus = taskStatus;
    }

    public void  setTaskDeadline(String deadline){
        this.deadline = deadline;
    }

}
