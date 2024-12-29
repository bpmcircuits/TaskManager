package com.example.taskmanager.taskmanager;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

//    @PostMapping
//    public Task createTaskForUser(@RequestParam Long userId,
//                                  @RequestParam String title,
//                                  @RequestParam String description) {
//
//        return taskService.createTaskForUser(userId, title, description);
//    }

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskRequest taskRequest, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        Task task = taskService.createTaskForUser(taskRequest.getUserId(), taskRequest.getTitle(), taskRequest.getDescription());
        return ResponseEntity.ok(task);
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping
    public List<Task> getTaskByStatus(@RequestParam(name = "status", required = false) String status) {
        return switch (status) {
            case "new" -> taskService.getTaskByStatus(TaskStatus.NEW);
            case "in_progress" -> taskService.getTaskByStatus(TaskStatus.IN_PROGRESS);
            case "completed" -> taskService.getTaskByStatus(TaskStatus.COMPLETED);
            default -> taskService.getAllTasks();
        };
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,
                                           @RequestBody Task updatedTask) {
        Task result = taskService.updateTask(id, updatedTask);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if(taskService.deleteTask(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
