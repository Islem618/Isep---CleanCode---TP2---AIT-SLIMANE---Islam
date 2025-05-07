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

    private void setUtf8Json(Response res) {
        res.raw().setCharacterEncoding("UTF-8");
        res.type("application/json; charset=UTF-8");
    }

    // GET /todos
    public Object getAllTodos(Request req, Response res) {
        setUtf8Json(res);
        return gson.toJson(todos);
    }

    // POST /todos
    public Object createTodo(Request req, Response res) {
        setUtf8Json(res);
        Todo todo = gson.fromJson(req.body(), Todo.class);
        todos.add(todo);
        res.status(201);
        return gson.toJson(todo);
    }

    // GET /todos/:id
    public Object getTodoById(Request req, Response res) {
        setUtf8Json(res);
        int id = Integer.parseInt(req.params(":id"));
        for (Todo t : todos) if (t.getId() == id) return gson.toJson(t);
        res.status(404);
        return gson.toJson(Map.of("error", "Todo not found"));
    }

    // PUT /todos/:id  (Step 3)
    public Object updateTodo(Request req, Response res) {
        setUtf8Json(res);
        int id = Integer.parseInt(req.params(":id"));
        Todo payload = gson.fromJson(req.body(), Todo.class);
        for (Todo t : todos) {
            if (t.getId() == id) {
                t.setName(payload.getName());
                return gson.toJson(t);
            }
        }
        res.status(404);
        return gson.toJson(Map.of("error", "Todo not found"));
    }
}