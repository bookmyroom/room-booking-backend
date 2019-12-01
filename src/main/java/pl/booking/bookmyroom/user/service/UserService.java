package pl.booking.bookmyroom.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.user.model.User;
import pl.booking.bookmyroom.user.model.UserLogInRequest;
import pl.booking.bookmyroom.user.model.UserRegistrationRequest;
import pl.booking.bookmyroom.user.repository.UserRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean createNewUser(UserRegistrationRequest request) {
        if(repository.findAll().stream().anyMatch(u -> u.getEmail().equals(request.getEmail()))){
            return false;
        }
        User user = new User();
        user.setEmail(request.getEmail());
        if(request.getPassword().equals(request.getPasswordValidCheck())){
            user.setPassword(request.getPassword());
        } else {
            return false;
        }
        repository.save(user);
        return true;
    }

    public boolean tryLogIn(UserLogInRequest request) {
        return repository.findAll()
                .stream()
                .filter(u -> u.getEmail().equals(request.getEmail()))
                .allMatch(u -> u.getPassword().equals(request.getPassword()));
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public void addSessionId(String email, String sessionId) {
        repository.findAll()
                .stream()
                .filter(u -> u.getEmail().equals(email))
                .forEach(u -> u.setSessionId(sessionId));
    }
}
