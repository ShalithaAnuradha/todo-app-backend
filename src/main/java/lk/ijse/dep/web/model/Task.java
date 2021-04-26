package lk.ijse.dep.web.model;

import lk.ijse.dep.web.util.Priority;

/**
 * @author : Shalitha Anuradha <shalithaanuradha123@gmail.com>
 * @since : 2021-04-24
 **/
public class Task {
    private String id;
    private String text;
    private Priority priority;
    private boolean completed;

    public Task() {
    }

    public Task(String id, String text, Priority priority, boolean completed) {
        this.id = id;
        this.text = text;
        this.priority = priority;
        this.completed = completed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", priority=" + priority +
                ", completed=" + completed +
                '}';
    }
}
