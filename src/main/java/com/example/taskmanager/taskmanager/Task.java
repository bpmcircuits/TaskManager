package com.example.taskmanager.taskmanager;

import com.example.taskmanager.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @NotBlank(message = "Title cannot be blank!")
    private String title;
    @Setter
    @NotBlank(message = "Description cannot be blank!")
    @Size(min = 3, max = 255, message = "Description needs to be between 3 to 255 chars length!")
    private String description;

    @Setter
    @Enumerated(EnumType.STRING)
    private TaskStatus status = TaskStatus.NEW;

    @ManyToOne
    @JoinColumn(name = "user_id") // kolumna z kluczem obcym w tabeli tasks
    @JsonBackReference
    @Setter
    private User user;

    @Setter
    private LocalDateTime createdAt;

    @Setter
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Task() {
    }


}
