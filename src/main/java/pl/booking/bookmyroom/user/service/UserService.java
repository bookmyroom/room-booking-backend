package pl.booking.bookmyroom.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.user.model.User;
import pl.booking.bookmyroom.user.model.UserLogInRequest;
import pl.booking.bookmyroom.user.model.UserRegistrationRequest;
import pl.booking.bookmyroom.user.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public boolean createNewUser(UserRegistrationRequest request) {
        if(userRepository.findAll().stream().anyMatch(u -> u.getEmail().equals(request.getEmail()))){
            return false;
        }
        User user = new User();
        user.setActive(true);
        user.setRoles("USER");
        user.setEmail(request.getEmail());
        if(request.getPassword().equals(request.getPasswordValidCheck())){
            user.setPassword(request.getPassword());
        } else {
            return false;
        }
        userRepository.save(user);
        return true;
    }

    public boolean tryLogIn(UserLogInRequest request) {
        return userRepository.findAll()
                .stream()
                .filter(u -> u.getEmail().equals(request.getEmail()))
                .allMatch(u -> u.getPassword().equals(request.getPassword()));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
