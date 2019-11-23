package pl.booking.bookmyroom.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.User.Model.User;
import pl.booking.bookmyroom.User.Model.UserLogInRequest;
import pl.booking.bookmyroom.User.Model.UserRegistrationRequest;
import pl.booking.bookmyroom.User.Repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private UserRepository repository;

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
}
