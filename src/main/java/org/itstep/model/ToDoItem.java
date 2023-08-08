package org.itstep.model;

public record ToDoItem(Integer id,
                String description,
                boolean done) {
    public ToDoItem(String description) {
        this(null, description, false);
    }
}
