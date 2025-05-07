package com.myprojects.todospringboot.repository;

import com.myprojects.todospringboot.model.Todo;
import com.myprojects.todospringboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUser(User user);
}
