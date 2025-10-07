package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TodoList {
    public static class Task {
        private final String text;
        private boolean done;

        public Task(String text) {
            this.text = text;
            this.done = false;
        }

        public String getText() {
            return text;
        }

        public boolean isDone() {
            return done;
        }

        public void markAsDone() {
            this.done = true;
        }

        @Override
        public String toString() {
            return (done ? "[X] " : "[ ] ") + text;
        }
    }

    private final List<Task> items = new ArrayList<>();

    public void add(String item) {
        if (item != null) {
            item = item.trim();
            if (!item.isEmpty()) {
                items.add(new Task(item));
            }
        }
    }

    public boolean remove(int index) {
        if (index >= 0 && index < items.size()) {
            items.remove(index);
            return true;
        }
        return false;
    }

    public void clear() {
        items.clear();
    }

    public boolean markAsDone(int index) {
        if (index >= 0 && index < items.size()) {
            items.get(index).markAsDone();
            return true;
        }
        return false;
    }

    public List<Task> search(String query) {
        if (query == null || query.trim().isEmpty()) {
            return new ArrayList<>();
        }
        String lowerQuery = query.toLowerCase().trim();
        return items.stream()
                .filter(task -> task.getText().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    public List<Task> getAll() {
        return new ArrayList<>(items);
    }

    public int size() {
        return items.size();
    }
}