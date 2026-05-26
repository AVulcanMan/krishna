package org.example.rdf4j_getting_started;

import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> tasks;

    public TaskManager() {
        tasks = new ArrayList<Task>();
    }

    public void addTask(String name) {
        Task t = new Task(name);
        tasks.add(t);
    }

    public void viewTasks() {

        if (tasks.size() == 0) {
            System.out.println("No tasks yet.");
            return;
        }

        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void completeTask(int index) {

        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).markDone();
        } else {
            System.out.println("Invalid task number.");
        }
    }

    public void removeTask(int index) {

        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        } else {
            System.out.println("Invalid task number.");
        }
    }
}