package org.isep.cleancode;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TodoController {
    private static final Gson gson = new Gson();
    private final List<Todo> todos = new ArrayList<>();

    // GET /todos
    public Object getAllTodos(Request req, Response res) {
        res.type("application/json");
        return gson.toJson(todos);
    }

    // POST /todos
    public Object createTodo(Request req, Response res) {
        Todo newTodo = gson.fromJson(req.body(), Todo.class);
        todos.add(newTodo);
        res.status(201);
        res.type("application/json");
        return gson.toJson(newTodo);
    }

    // GET /todos/:id
    public Object getTodoById(Request req, Response res) {
        res.type("application/json");
        int id = Integer.parseInt(req.params(":id"));
        for (Todo t : todos) {
            if (t.getId() == id) {
                return gson.toJson(t);
            }
        }
        res.status(404);
        return gson.toJson(Map.of("error", "Todo not found"));
    }
}
