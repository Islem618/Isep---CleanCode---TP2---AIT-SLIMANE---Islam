package org.isep.cleancode.domain;

import java.time.LocalDate;

public class Todo {
    private static int nextId = 1;
    private final int id;
    private String name;
    private LocalDate dueDate;

    public Todo(String name, LocalDate dueDate) {
        this.id = nextId++;
        this.name = name;
        this.dueDate = dueDate;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}