package org.isep.cleancode;

import static spark.Spark.*;
import org.isep.cleancode.repository.InMemoryTodoRepository;
import org.isep.cleancode.repository.CsvTodoRepository;
import org.isep.cleancode.service.TodoService;
import org.isep.cleancode.controller.TodoController;

public class Main {
    public static void main(String[] args) throws Exception {
        port(4567);

        // Pour changer de persistance, commente ou décommente :
        var repo = new InMemoryTodoRepository();
        // var repo = new CsvTodoRepository();

        var service = new TodoService(repo);
        var controller = new TodoController(service);

        get    ("/todos",       controller::getAll);
        post   ("/todos",       controller::create);
        get    ("/todos/:id",   controller::getById);
        put    ("/todos/:id",   controller::update);
        delete ("/todos/:id",   controller::delete);
    }
}