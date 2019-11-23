package pl.booking.bookmyroom.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.booking.bookmyroom.User.Model.User;
import pl.booking.bookmyroom.User.Model.UserLogInRequest;
import pl.booking.bookmyroom.User.Model.UserRegistrationRequest;
import pl.booking.bookmyroom.User.Service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public boolean registerNewUser (@RequestBody @Valid UserRegistrationRequest request){
        return userService.createNewUser(request);
    }

    @GetMapping(value = "/login")
    public boolean tryLogIn(@RequestBody@Valid UserLogInRequest request){
        return userService.tryLogIn(request);
    }

    //TODO remove this before release
    @GetMapping(value = "/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
