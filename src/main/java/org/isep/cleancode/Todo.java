package org.isep.cleancode;

public class Todo {
    private static int nextId = 1;
    private final int id;
    private String name;

    // Constructeur sans arguments pour Gson
    public Todo() {
        this.id = nextId++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Gson appelle ce setter quand il parse le JSON
    public void setName(String name) {
        this.name = name;
    }
}