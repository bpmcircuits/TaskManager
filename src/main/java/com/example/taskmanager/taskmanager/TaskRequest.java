package com.example.taskmanager.taskmanager;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskRequest {

    @NotNull(message = "ID użytkownika jest wymagane!")
    private Long userId;

    @NotBlank(message = "Tytuł nie może być pusty!")
    @Size(max = 100, message = "Tytuł nie może mieć więcej niż 100 znaków!")
    private String title;

    @NotBlank(message = "Opis nie może być pusty!")
    @Size(max = 500, message = "Opis nie może mieć więcej niż 500 znaków!")
    private String description;


}
