package org.isep.cleancode;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(4567);
        TodoController controller = new TodoController();

        // routes
        get("/todos", controller::getAllTodos);
        post("/todos", controller::createTodo);
        get("/todos/:id", controller::getTodoById);
    }
}