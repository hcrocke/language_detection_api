package language_detection.services.user;

import language_detection.exceptions.GeneralException;
import language_detection.mappers.UserMapper;
import language_detection.models.user.User;
import language_detection.services.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    SecurityService securityService;

    public ArrayList<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    public User getUserById(Integer id) throws GeneralException {
        User u = userMapper.getByID(id);

        if (u == null) {
            throw new GeneralException("User not found.");
        }
        return u;
    }

    public User createUser(User user) throws NoSuchAlgorithmException, GeneralException {
        String apiKey = securityService.generateApiKey(128);
        user.setApiKey(apiKey);
        int success = userMapper.insertUser(user);

        if(success == 1) {
            User u = userMapper.getByName(user.getGivenName(), user.getSurname());
            return u;
        } else {
            throw new GeneralException("User Not Created.");
        }
    }

    public User updateUser(User user) throws GeneralException {
        int success = userMapper.updateUser(user);

        if(success == 1) {
            User u = userMapper.getByName(user.getGivenName(), user.getSurname());
            return u;
        } else {
            throw new GeneralException("User Not Updated.");
        }
    }

    public User deleteUser(String id) throws GeneralException {
        int success = userMapper.deleteUser(Integer.parseInt(id));

        if(success == 1) {
            User u = userMapper.getByID(Integer.parseInt(id));
            return u;
        } else {
            throw new GeneralException("User Not Deleted.");
        }
    }
}
