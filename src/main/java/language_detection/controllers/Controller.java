package language_detection.controllers;

import language_detection.exceptions.AuthenticationException;
import language_detection.exceptions.GeneralException;
import language_detection.models.language.MultiLanguage;
import language_detection.models.user.User;
import language_detection.models.language.LanguageRoot;
import language_detection.services.language.LanguageService;
import language_detection.services.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import language_detection.services.user.UserService;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/language")

public class Controller {

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    LanguageService languageService;


//        @GetMapping("/")
//    public ArrayList<User> getAllUsers(@RequestParam("api-key") String apiKey) throws AuthenticationException {
//
//        if (securityService.authenticateApiKey(apiKey))
//            return userService.getAllUsers();
//        else
//            throw new AuthenticationException("FALSE. Invalid API key.");
//    }

    @RequestMapping("/detect")
    public LanguageRoot detectLanguage(@RequestParam(value = "words", defaultValue = "helloooo") String words, @RequestParam("api-key") String apiKey) throws AuthenticationException {

        if (securityService.authenticateApiKey(apiKey))
            return languageService.detectLanguage(words);
        else
            throw new AuthenticationException("Get outta here with your invalid API key.");
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable(value = "id") Integer id, @RequestParam("api-key") String apiKey)
            throws GeneralException, AuthenticationException {

        if (securityService.authenticateApiKey(apiKey))
            return userService.getUserById(id);
        else
            throw new AuthenticationException("Get outta here with your invalid API key.");
    }

    @RequestMapping("/searchmultiple")
    public MultiLanguage multiLanguages(@RequestParam(value = "t1", required = true) String t1,
                                        @RequestParam(value = "t2", required = true) String t2,
                                        @RequestParam(value = "p", required = true) boolean persist) {

        return languageService.multiLanguages(t1, t2, persist);
    }

    @PostMapping("/")
    public User createUser(@RequestBody User user) throws GeneralException, NoSuchAlgorithmException {

        return userService.createUser(user);
    }

    @PutMapping("/")
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
