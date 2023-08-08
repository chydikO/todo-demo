package org.itstep.dao.impl;

import org.itstep.dao.GenericDao;
import org.itstep.model.ToDoItem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ToDoItemDao implements GenericDao<ToDoItem> {

    private final Connection connection;

    public ToDoItemDao(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    @Override
    public void save(ToDoItem model) {
        try (Statement smt = connection.createStatement()) {
            smt.executeUpdate("INSERT INTO todos(description, done) values ('%s', %s)".formatted(model.description(), model.done()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ToDoItem> findAll() {
        List<ToDoItem> todos = new ArrayList<>();
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT id, description, done FROM todos");
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String description = rs.getString("description");
                boolean done = rs.getBoolean("done");
                todos.add(new ToDoItem(id, description, done));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return todos;
    }

    @Override
    public Optional<ToDoItem> findById() {
        return Optional.empty();
    }

    @Override
    public void update(ToDoItem model) {

    }

    @Override
    public void delete(ToDoItem model) {

    }

    @Override
    public void delete(int id) {
        try (Statement smt = connection.createStatement()) {
            smt.executeUpdate("DELETE FROM todos WHERE id IN (%d)".formatted(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
