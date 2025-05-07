package com.myprojects.todospringboot.model;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "todos")
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private boolean completed;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
