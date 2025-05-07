package org.isep.cleancode;

import static spark.Spark.*;

import org.isep.cleancode.config.AppConfig;
import org.isep.cleancode.repository.CsvTodoRepository;
import org.isep.cleancode.repository.InMemoryTodoRepository;
import org.isep.cleancode.repository.ITodoRepository;
import org.isep.cleancode.service.TodoService;
import org.isep.cleancode.controller.TodoController;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length < 2 || !"--config".equals(args[0])) {
            System.err.println("Usage : java -jar todo-api.jar --config <config-file>");
            System.exit(1);
        }
        String configPath = args[1];
        AppConfig config = new AppConfig(configPath);

        ITodoRepository repo;
        switch (config.getRepoType()) {
            case CSV:
                repo = new CsvTodoRepository();
                break;
            case INMEMORY:
            default:
                repo = new InMemoryTodoRepository();
        }

        TodoService service = new TodoService(repo);
        TodoController controller = new TodoController(service);

        port(4567);
        get    ("/todos",     controller::getAll);
        post   ("/todos",     controller::create);
        get    ("/todos/:id", controller::getById);
        put    ("/todos/:id", controller::update);
        delete ("/todos/:id", controller::delete);
    }
}