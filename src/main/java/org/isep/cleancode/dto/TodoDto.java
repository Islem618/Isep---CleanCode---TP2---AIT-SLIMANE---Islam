package org.isep.cleancode.dto;

public class TodoDto {
    private String name;
    private String dueDate;  // format ISO YYYY-MM-DD

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
}