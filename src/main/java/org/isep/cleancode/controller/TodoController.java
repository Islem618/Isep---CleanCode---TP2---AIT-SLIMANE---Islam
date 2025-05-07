package org.isep.cleancode.controller;

import com.google.gson.*;
import spark.Request;
import spark.Response;
import org.isep.cleancode.config.LocalDateAdapter;
import org.isep.cleancode.dto.TodoDto;
import org.isep.cleancode.domain.Todo;
import org.isep.cleancode.service.TodoService;
import java.time.LocalDate;
import java.util.Map;

public class TodoController {
    private final TodoService srv;
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

    public TodoController(TodoService srv) { this.srv = srv; }

    private void prepareJson(Response res) {
        res.raw().setCharacterEncoding("UTF-8");
        res.type("application/json; charset=UTF-8");
    }

    public Object getAll(Request req, Response res) {
        prepareJson(res);
        return gson.toJson(srv.getAll());
    }

    public Object create(Request req, Response res) {
        prepareJson(res);
        try {
            TodoDto dto = gson.fromJson(req.body(), TodoDto.class);
            Todo t = srv.create(dto);
            res.status(201);
            return gson.toJson(t);
        } catch(IllegalArgumentException e) {
            res.status(400);
            return gson.toJson(Map.of("error", e.getMessage()));
        }
    }

    public Object getById(Request req, Response res) {
        prepareJson(res);
        try {
            int id = Integer.parseInt(req.params(":id"));
            return gson.toJson(srv.getById(id));
        } catch(Exception e) {
            res.status(404);
            return gson.toJson(Map.of("error","Todo not found"));
        }
    }

    public Object update(Request req, Response res) {
        prepareJson(res);
        try {
            int id = Integer.parseInt(req.params(":id"));
            TodoDto dto = gson.fromJson(req.body(), TodoDto.class);
            return gson.toJson(srv.update(id, dto));
        } catch(IllegalArgumentException e) {
            res.status(400);
            return gson.toJson(Map.of("error", e.getMessage()));
        }
    }

    public Object delete(Request req, Response res) {
        prepareJson(res);
        try {
            int id = Integer.parseInt(req.params(":id"));
            srv.delete(id);
            res.status(204);
            return "";
        } catch(Exception e) {
            res.status(404);
            return gson.toJson(Map.of("error","Todo not found"));
        }
    }
}