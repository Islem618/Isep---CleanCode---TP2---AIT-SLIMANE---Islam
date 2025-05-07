package org.isep.cleancode.repository;

import org.isep.cleancode.domain.Todo;
import java.util.*;

public class InMemoryTodoRepository implements ITodoRepository {
    private final Map<Integer, Todo> store = new LinkedHashMap<>();

    @Override public void add(Todo t)                  { store.put(t.getId(), t); }
    @Override public List<Todo> findAll()               { return new ArrayList<>(store.values()); }
    @Override public Optional<Todo> findById(int id)    { return Optional.ofNullable(store.get(id)); }
    @Override public Optional<Todo> findByName(String name) {
        return store.values().stream()
                .filter(t -> t.getName().equals(name))
                .findFirst();
    }
    @Override public void delete(int id)                { store.remove(id); }
}