package org.example.rdf4j_getting_started;

public class Task {

    private String name;
    private boolean completed;

    public Task(String name) {
        this.name = name;
        this.completed = false;
    }

    public String getName() {
        return name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markDone() {
        completed = true;
    }

    public String toString() {

        if (completed) {
            return "[✓] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}