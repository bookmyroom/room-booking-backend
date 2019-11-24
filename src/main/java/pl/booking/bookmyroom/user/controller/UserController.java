package pl.booking.bookmyroom.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.booking.bookmyroom.user.model.User;
import pl.booking.bookmyroom.user.model.UserLogInRequest;
import pl.booking.bookmyroom.user.model.UserRegistrationRequest;
import pl.booking.bookmyroom.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(value = "http://localhost:8000")
public class UserController {
    private final UserService userService;

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
