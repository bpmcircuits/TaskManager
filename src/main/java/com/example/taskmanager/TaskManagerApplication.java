package com.example.taskmanager;

import com.example.taskmanager.taskmanager.Task;
import com.example.taskmanager.taskmanager.TaskRepository;
import com.example.taskmanager.user.User;
import com.example.taskmanager.user.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class TaskManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, TaskRepository taskRepository) {
        return args -> {
            List<User> allUsers = List.of(new User("Bart", "bart@example.com", "tajnehaslowcostamtrzaslo"),
                    new User("Sara", "sara@example.com", "osiemzapalek"));
            userRepository.saveAll(allUsers);
            Task taskA = new Task("Zadanie A", "Opis: Chop stoi i ja sie pytam chopie cos ty zrobil");
            Task taskB = new Task("Zadanie B", "Opis: Dawaj maniek to sie juz kameruje");
            taskA.setUser(allUsers.get(0));
            taskB.setUser(allUsers.get(1));

            Task taskC = new Task("Zadanie C", "Opis: Bede gral w gre");
            taskC.setUser(allUsers.get(1));
            taskRepository.saveAll(List.of(taskA, taskB, taskC));
            System.out.println(">>> [DataInitializer] Przykładowe dane zostały załadowane do bazy <<<");
        };
    }

}
