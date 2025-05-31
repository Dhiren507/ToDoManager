package com.myprojects.todospringboot.services;

import com.myprojects.todospringboot.model.Todo;
import com.myprojects.todospringboot.model.User;
import com.myprojects.todospringboot.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public List<Todo> getTodosForUser(User user) {
        return todoRepository.findByUser(user);
    }

    public void addTodoForUser(String title, String description, User user) {
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDescription(description);
        todo.setCompleted(false);
        todo.setUser(user);
        todoRepository.save(todo);
    }
    
    public void deleteTodoForUser(Long id, User user) {
        todoRepository.findById(id).ifPresent(todo -> {
            if (todo.getUser().equals(user)) {
                todoRepository.delete(todo);
            }
        });
    }

    public void toggleTodoForUser(Long id, User user) {
        todoRepository.findById(id).ifPresent(todo -> {
            if (todo.getUser().equals(user)) {
                todo.setCompleted(!todo.isCompleted());
                todoRepository.save(todo);
            }
        });
    }

    public void editTodoForUser(Long id, String title, String description, User user) {
        todoRepository.findById(id).ifPresent(todo -> {
            if (todo.getUser().equals(user)) {
                todo.setTitle(title);
                todo.setDescription(description);
                todoRepository.save(todo);
            }
        });
    }

    public Todo getTodoByIdForUser(Long id, User user) {
        return todoRepository.findById(id).filter(todo -> todo.getUser().equals(user))
                .orElseThrow(() -> new RuntimeException("Unauthorized access"));
    }
}
