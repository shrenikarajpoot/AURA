package thread;

import java.util.ArrayList;
import model.Task;

public class ReminderThread extends Thread {

    private ArrayList<Task> tasks;
    private boolean running = true;

    public ReminderThread(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {

        while (running) {

            try {

                Thread.sleep(10000);

                System.out.println(
                        "\n[AURA Background Service] Checking tasks..."
                );

                boolean pendingTask = false;

                for (Task task : tasks) {

                    if (!task.isCompleted()) {

                        pendingTask = true;

                        System.out.println(
                                "[AURA Reminder] Pending task: "
                                + task.getTaskName()
                        );
                    }
                }

                if (!pendingTask) {

                    System.out.println(
                            "[AURA Reminder] No pending tasks."
                    );
                }

                System.out.print("You: ");

            } catch (InterruptedException e) {

                System.out.println(
                        "AURA: Reminder thread interrupted."
                );

                break;
            }
        }
    }

    public void stopReminder() {

        running = false;
        this.interrupt();
    }
}
