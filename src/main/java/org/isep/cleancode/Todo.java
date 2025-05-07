package org.isep.cleancode;

public class Todo {
    // IDs démarrent à 1
    private static int nextId = 1;

    private final int id;
    private String name;

    // nécessaire pour Gson
    public Todo() {
        this.id = nextId++;
    }

    public Todo(String name) {
        this.id = nextId++;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // setter nécessaire pour Gson
    public void setName(String name) {
        this.name = name;
    }
}
