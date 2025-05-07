package org.isep.cleancode;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {
        port(4567);
        TodoController controller = new TodoController();

        get   ("/todos",       controller::getAllTodos);
        post  ("/todos",       controller::createTodo);
        get   ("/todos/:id",   controller::getTodoById);
        put   ("/todos/:id",   controller::updateTodo);
    }
}