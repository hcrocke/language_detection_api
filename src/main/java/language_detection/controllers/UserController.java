package language_detection.controllers;

import language_detection.exceptions.AuthenticationException;
import language_detection.exceptions.GeneralException;
import language_detection.models.User;
import language_detection.services.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import language_detection.services.user.UserService;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @GetMapping("/")
    public ArrayList<User> getAllUsers(@RequestParam("api-key") String apiKey) throws AuthenticationException {

        if (securityService.authenticateApiKey(apiKey))
            return userService.getAllUsers();
        else
            throw new AuthenticationException("FALSE. Invalid API key.");

    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Integer id, @RequestParam("api-key") String apiKey)
            throws GeneralException, AuthenticationException {

        if (securityService.authenticateApiKey(apiKey))
            return userService.getUserById(id);
         else
            throw new AuthenticationException("Get outta here with your invalid API key.");
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws GeneralException, NoSuchAlgorithmException {

        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user) throws GeneralException, AuthenticationException {

        if (securityService.authenticateApiKey(user.getApiKey()))
            return userService.updateUser(user);
        else
            throw new AuthenticationException("Invalid API key, ya dangus");
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable("id") String id, @RequestParam("api-key") String apiKey) throws GeneralException, AuthenticationException {

        if (securityService.authenticateApiKey(apiKey))
            return userService.deleteUser(id);
        else
            throw new AuthenticationException("No way, Jose. Invalid API key.");
    }
}
