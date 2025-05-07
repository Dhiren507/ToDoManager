package com.myprojects.todospringboot.controller;

import com.myprojects.todospringboot.model.Todo;
import com.myprojects.todospringboot.model.User;
import com.myprojects.todospringboot.services.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String getTodos(Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("todos", todoService.getTodosForUser(loggedInUser));
        model.addAttribute("loggedInUser", loggedInUser);
        return "todos";
    }

    @PostMapping
    public String addTodo(@RequestParam String title, @RequestParam String description, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        todoService.addTodoForUser(title, description, loggedInUser);
        return "redirect:/todos";
    }

    @GetMapping("/{id}/delete")
    public String deleteTodo(@PathVariable Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        todoService.deleteTodoForUser(id, loggedInUser);
        return "redirect:/todos";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTodo(@PathVariable Long id, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        todoService.toggleTodoForUser(id, loggedInUser);
        return "redirect:/todos";
    }

    @PostMapping("/{id}/edit")
    public String editTodo(@PathVariable Long id, @RequestParam String title, @RequestParam String description, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        todoService.editTodoForUser(id, title, description, loggedInUser);
        return "redirect:/todos";
    }

    @GetMapping("/{id}/edit")
    public String getEditTodoPage(@PathVariable Long id, Model model, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("todo", todoService.getTodoByIdForUser(id, loggedInUser));
        return "edit";
    }
}
