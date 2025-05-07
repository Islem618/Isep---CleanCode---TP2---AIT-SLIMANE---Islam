package org.isep.cleancode.repository;

import org.isep.cleancode.domain.Todo;
import java.util.List;
import java.util.Optional;

public interface ITodoRepository {
    void add(Todo todo);
    List<Todo> findAll();
    Optional<Todo> findById(int id);
    Optional<Todo> findByName(String name);
    void delete(int id);
}