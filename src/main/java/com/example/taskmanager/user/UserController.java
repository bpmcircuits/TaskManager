package com.example.taskmanager.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User users) {
        return userService.createUser(users.getUsername(), users.getEmail(), users.getPassword());
    }

    @GetMapping
    public ResponseEntity<User> getUsername(@RequestParam(name = "username", required = false) String username,
                                            @RequestParam(name = "id", required = false) Long id) {
        // Możesz reagować w zależności od tego, które parametry zostały przekazane:
        if (username != null) {
            // wyszukiwanie po username
            Optional<User> userOpt = userService.findByUsername(username);
            return userOpt.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else if (id != null) {
            // wyszukiwanie po ID
            Optional<User> userOpt = userService.findById(id);
            return userOpt.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            // Brak żadnego parametru → zwróć listę wszystkich lub 400 Bad Request
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }
}

