package controllers;

import models.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/")
    public ArrayList<User> getAllUsers() {

        return null;
    }

    @GetMapping("/#{id}")
    public User getUserById(@PathVariable("id") String id) {

        return null;
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) {

        return null;
    }

    @PutMapping("/#{id}")
    public User updateUser(@PathVariable("id") String value, @RequestBody User user) {

        return null;
    }

    @DeleteMapping("/#{id}")
    public User deleteUser() {

        return null;
    }

}
