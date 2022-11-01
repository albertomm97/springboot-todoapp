package com.projects.todoapp.persistence.entity;

public enum TaskStatus {
    ON_TIME("on_time"),
    FINISHED("finished"),
    LATE("late");

    private final String name;

    private TaskStatus(String name) {
        this.name = name;
    }

    public boolean equals(String other) {
        return name.equals(other);
    }

    public String toString() {
        return this.name();
    }
}
