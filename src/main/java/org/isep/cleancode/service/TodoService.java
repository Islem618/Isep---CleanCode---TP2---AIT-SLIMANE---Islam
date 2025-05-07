package org.isep.cleancode.service;

import org.isep.cleancode.domain.Todo;
import org.isep.cleancode.dto.TodoDto;
import org.isep.cleancode.repository.ITodoRepository;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class TodoService {
    private final ITodoRepository repo;
    public TodoService(ITodoRepository repo) { this.repo = repo; }

    public List<Todo> getAll() { return repo.findAll(); }

    public Todo getById(int id) {
        return repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo not found"));
    }

    public Todo create(TodoDto dto) {
        String name = dto.getName();
        if (name==null||name.isBlank()||name.length()>=64)
            throw new IllegalArgumentException("Name required, <64 chars");
        if (repo.findByName(name).isPresent())
            throw new IllegalArgumentException("Name must be unique");

        LocalDate due = null;
        if (dto.getDueDate()!=null && !dto.getDueDate().isBlank()) {
            try { due = LocalDate.parse(dto.getDueDate()); }
            catch(DateTimeParseException e) { throw new IllegalArgumentException("Invalid dueDate"); }
        }

        Todo t = new Todo(name, due);
        repo.add(t);
        return t;
    }

    public Todo update(int id, TodoDto dto) {
        Todo ex = getById(id);
        if (dto.getName()!=null) {
            String nm = dto.getName();
            if (nm.isBlank()||nm.length()>=64)
                throw new IllegalArgumentException("Name required, <64 chars");
            repo.findByName(nm).filter(t->t.getId()!=id)
                    .ifPresent(t-> { throw new IllegalArgumentException("Name must be unique"); });
            ex.setName(nm);
        }
        return ex;
    }

    public void delete(int id) {
        getById(id);
        repo.delete(id);
    }
}