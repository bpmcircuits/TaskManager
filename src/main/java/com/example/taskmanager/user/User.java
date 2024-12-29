package com.example.taskmanager.user;

import com.example.taskmanager.taskmanager.Task;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Encja reprezentująca użytkownika w bazie danych.
 */
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ... reszta get/set
    @Setter
    private String username;
    @Setter
    private String email;
    @Setter
    private String password;

    // Przykładowe: OneToMany -> Task
    // (tasks - nazwa kolekcji w klasie User, "user" - nazwa pola w klasie Task)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @Setter
    private List<Task> tasks = new ArrayList<>();


    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    // Dodaj metodę pomocniczą, by poprawnie dodać Task do Usera
    public void addTask(Task task) {
        tasks.add(task);
        task.setUser(this);  // ustawia user w encji Task
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

}
