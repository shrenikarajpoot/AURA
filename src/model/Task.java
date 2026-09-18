package model;

public class Task {

    private int taskId;
    private String taskName;
    private boolean completed;

    // Constructor
    public Task(int taskId, String taskName) {

        this.taskId = taskId;
        this.taskName = taskName;
        this.completed = false;
    }

    // Getter for task ID
    public int getTaskId() {
        return taskId;
    }

    // Getter for task name
    public String getTaskName() {
        return taskName;
    }

    // Getter for completion status
    public boolean isCompleted() {
        return completed;
    }

    // Mark task as completed
    public void markCompleted() {
        completed = true;
    }

    // Display task
    public void displayTask() {

        String status;

        if (completed) {
            status = "Completed";
        } else {
            status = "Pending";
        }

        System.out.println(
                taskId + ". " + taskName + " [" + status + "]"
        );
    }
}
    

