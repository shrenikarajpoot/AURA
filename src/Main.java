import model.User;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Task;
import model.Note;
import exception.TaskNotFoundException;
import exception.NoteNotFoundException;
import util.FileManager;
import thread.ReminderThread;
import java.util.HashMap;
import java.util.Stack;

public class Main {
   

    public static void main(String[] args) {
         User user = new User(1, "AURA User" , "user@example.com");
         user.displayUser();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        int nextTaskId = 1;
        ArrayList<Note> notes = new ArrayList<>();
        int nextNoteId = 1;
        HashMap<String, String> 
        commandShortcuts = new HashMap<>();
        commandShortcuts.put("h", "help");
        commandShortcuts.put("t", "time");
        commandShortcuts.put("d", "date");
        commandShortcuts.put("at", "add task");
        commandShortcuts.put("vt", "view tasks");
        commandShortcuts.put("an", "add note");
        commandShortcuts.put("vn", "view notes"); 
        Stack<String> commandHistory = new Stack<>();
        ReminderThread reminderThread =
        new ReminderThread(tasks);

reminderThread.start();

        System.out.println("-------------------------------------------");
        System.out.println("              AURA");
        System.out.println(" Advanced Universal Response &");
        System.out.println(" Assistance System");
        System.out.println("-------------------------------------------");

        System.out.println("AURA: Hello! I am AURA.");
        System.out.println("AURA: How can I help you?");
        FileManager.log("AURA started");
        while (true) {

            System.out.print("\nYou: ");
            String command = scanner.nextLine().trim().toLowerCase();
            commandHistory.push(command);
            if (commandShortcuts.containsKey(command)) {

    command = commandShortcuts.get(command);
}
            if (command.equals("hello") || command.equals("hi")) {

                System.out.println("AURA: Hello! Nice to meet you.");
            }
                else if (command.equals("add task"))
                {
                    System.out.println("AURA: What task should I add? ");

                     String taskName = scanner.nextLine().trim(); 
            

                             if (taskName.isEmpty()) {

                             System.out.println("AURA: Task name cannot be empty.");

                              } else {

                                    Task task = new Task(nextTaskId, taskName);

                                     tasks.add(task);

                                    System.out.println("AURA: Task added successfully.");

                                     FileManager.log("Task added:"+ taskName);
                                      nextTaskId++;
                              }
                            }
                            else if (command.equals("add note")) {

    System.out.print(
            "AURA: What should I write? "
    );

    String content = scanner.nextLine().trim();

    if (content.isEmpty()) {

        System.out.println(
                "AURA: Note cannot be empty."
        );

    } else {

        Note note = new Note(
                nextNoteId,
                content
        );

        notes.add(note);

        System.out.println(
                "AURA: Note saved successfully."
        );
        FileManager.log("Note added:"+ content);
        nextNoteId++;
    }
}
else if (command.equals("view notes")) {

    if (notes.isEmpty()) {

        System.out.println(
                "AURA: You don't have any notes yet."
        );

    } else {

        System.out.println(
                "\nAURA: Your Notes"
        );

        for (Note note : notes) {

            note.displayNote();
        }
    }
}
else if (command.equals("history")) {

    if (commandHistory.isEmpty()) {

        System.out.println(
                "AURA: No command history available."
        );

    } else {

        System.out.println(
                "\nAURA: Recent Commands"
        );

        for (int i = commandHistory.size() - 1; i >= 0; i--) {

            System.out.println(
                    (commandHistory.size() - i)
                    + ". "
                    + commandHistory.get(i)
            );
        }
    }
}
else if (command.equals("search notes")) {

    if (notes.isEmpty()) {

        System.out.println(
                "AURA: You don't have any notes."
        );

    } else {

        System.out.print(
                "AURA: Enter keyword to search: "
        );

        String keyword =
                scanner.nextLine().trim().toLowerCase();

        boolean found = false;

        for (Note note : notes) {

            if (note.getContent()
                    .toLowerCase()
                    .contains(keyword)) {

                note.displayNote();

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "AURA: No matching notes found."
            );
        }
    }
}
                        else if (command.equals("view tasks")) {
    if (tasks.isEmpty()) {

        System.out.println("AURA: You don't have any tasks yet.");

    } else {

        System.out.println("\nAURA: Your Tasks");

        for (Task task : tasks) {
            task.displayTask();
        }
    }
}
else if (command.equals("complete task")) {

    if (tasks.isEmpty()) {

        System.out.println("AURA: You don't have any tasks.");

    } else {

        System.out.print("AURA: Enter task ID: ");

        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;

        for (Task task : tasks) {

            if (task.getTaskId() == id) {

                task.markCompleted();

                System.out.println(
                        "AURA: Task marked as completed."
                );
                FileManager.log("Task completed: "+ task.getTaskName());
                found = true;
                break;
            }
        }

        if (!found) {

            System.out.println(
                    "AURA: Task with this ID was not found."
            );
        }
    }
}
else if (command.equals("delete task")) {

    try {

        System.out.print("AURA: Enter task ID to delete: ");

        int id = scanner.nextInt();
        scanner.nextLine();

        Task taskToDelete = null;

        for (Task task : tasks) {

            if (task.getTaskId() == id) {

                taskToDelete = task;
                break;
            }
        }

        if (taskToDelete == null) {

            throw new TaskNotFoundException(
                    "Task with ID " + id + " was not found."
            );
        }

        tasks.remove(taskToDelete);

        System.out.println(
                "AURA: Task deleted successfully."
        );
        FileManager.log("Task deleted: "+ taskToDelete.getTaskName());

    }

    catch (TaskNotFoundException e) {

        System.out.println(
                "AURA: " + e.getMessage()
        );
    }
}
else if (command.equals("delete note")) {

    try {

        System.out.print("AURA: Enter note ID to delete: ");

        int id = scanner.nextInt();
        scanner.nextLine();

        Note noteToDelete = null;

        for (Note note : notes) {

            if (note.getNoteId() == id) {

                noteToDelete = note;
                break;
            }
        }

        if (noteToDelete == null) {

            throw new NoteNotFoundException(
                    "Note with ID " + id + " was not found."
            );
        }

        notes.remove(noteToDelete);

        System.out.println(
                "AURA: Note deleted successfully."
        );

    }

    catch (NoteNotFoundException e) {

        System.out.println(
                "AURA: " + e.getMessage()
        );
    }
}
else if (command.equals("complete task")) {

    if (tasks.isEmpty()) {

        System.out.println("AURA: You don't have any tasks.");

    } else {

        System.out.print("AURA: Enter task ID: ");

        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;

        for (Task task : tasks) {

            if (task.getTaskId() == id) {

                task.markCompleted();

                System.out.println(
                        "AURA: Task marked as completed."
                );

                found = true;
                break;
            }
        }

        if (!found) {

            System.out.println("AURA: Task with this ID was not found.");
        }
    }
}
            else if (command.equals("help")) {
            
            System.out.println("\nAURA Commands:");

            System.out.println("1. hello");

            System.out.println("2. time");

            System.out.println("3. date");

            System.out.println("4. calculate");

            System.out.println("5. about");

            System.out.println("6. add task");

            System.out.println("7. view tasks");

            System.out.println("8. complete task");

            System.out.println("9. delete task");

            System.out.println("10. add note");

            System.out.println("11. view notes");

            System.out.println("12. delete note");

            System.out.println("13. search notes");

            System.out.println("14. history");

            System.out.println("15. exit");



            System.out.println();

            System.out.println("Shortcuts:");

            System.out.println("h  → help");

            System.out.println("t  → time");

            System.out.println("d  → date");

            System.out.println("at → add task");

            System.out.println("vt → view tasks");

            System.out.println("an → add note");

            System.out.println("vn → view notes");

            } 
            else if (command.equals("time")) {

                LocalDateTime now = LocalDateTime.now();

                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("hh:mm:ss a");

                System.out.println("AURA: Current time: "
                        + now.format(formatter));

            } 
            else if (command.equals("date")) {

                LocalDateTime now = LocalDateTime.now();

                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("dd-MM-yyyy");

                System.out.println("AURA: Today's date: "
                        + now.format(formatter));

            } 
            else if (command.equals("about")) {

                System.out.println("\nAURA");
                System.out.println("Advanced Universal Response & Assistance System");
                System.out.println("A Java-based personal desktop assistant.");

            } 
            else if (command.equals("calculate")) {

                System.out.print("AURA: Enter first number: ");
                double num1 = scanner.nextDouble();

                System.out.print("AURA: Enter operator (+, -, *, /): ");
                char operator = scanner.next().charAt(0);

                System.out.print("AURA: Enter second number: ");
                double num2 = scanner.nextDouble();

                scanner.nextLine();

                double result = 0;

                if (operator == '+') {
                    result = num1 + num2;
                } 
                else if (operator == '-') {
                    result = num1 - num2;
                } 
                else if (operator == '*') {
                    result = num1 * num2;
                } 
                else if (operator == '/') {

                    if (num2 == 0) {
                        System.out.println(
                                "AURA: Division by zero is not allowed."
                        );
                        continue;
                    }

                    result = num1 / num2;

                } 
                else {

                    System.out.println("AURA: Invalid operator.");
                    continue;
                }

                System.out.println("AURA: Result = " + result);

            } 
             else if (command.equals("exit")) {
                reminderThread.stopReminder();
                System.out.println("AURA: Goodbye! See you again.");
                break;

            } 
            else {

                System.out.println("AURA: I don't understand that commands.");
                System.out.println("AURA: Type 'help' to see available commands.");
            }
        }

        scanner.close();
    }
}
