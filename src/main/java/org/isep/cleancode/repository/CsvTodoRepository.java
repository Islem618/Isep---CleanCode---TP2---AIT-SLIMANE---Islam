package org.isep.cleancode.repository;

import org.isep.cleancode.domain.Todo;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;

public class CsvTodoRepository implements ITodoRepository {
    private final Path file = Paths.get(System.getenv("APPDATA"), "todos.csv");
    private final List<Todo> cache = new ArrayList<>();

    public CsvTodoRepository() throws IOException {
        if (Files.exists(file)) {
            try (BufferedReader r = Files.newBufferedReader(file)) {
                String line;
                while ((line = r.readLine()) != null) {
                    var parts = line.split(",");
                    LocalDate due = parts[2].isEmpty() ? null : LocalDate.parse(parts[2]);
                    cache.add(new Todo(parts[1], due));
                }
            }
        } else {
            Files.createDirectories(file.getParent());
            Files.createFile(file);
        }
    }

    private void persist() throws IOException {
        try (BufferedWriter w = Files.newBufferedWriter(file)) {
            for (Todo t : cache) {
                w.write(t.getId() + "," + t.getName() + "," +
                        (t.getDueDate()==null?"":t.getDueDate()));
                w.newLine();
            }
        }
    }

    @Override public void add(Todo t)           { cache.add(t); try { persist(); } catch(IOException ignore){} }
    @Override public List<Todo> findAll()        { return List.copyOf(cache); }
    @Override public Optional<Todo> findById(int id)    { return cache.stream().filter(t->t.getId()==id).findFirst(); }
    @Override public Optional<Todo> findByName(String name) { return cache.stream().filter(t->t.getName().equals(name)).findFirst(); }
    @Override public void delete(int id)         { cache.removeIf(t->t.getId()==id); try { persist(); } catch(IOException ignore){} }
}