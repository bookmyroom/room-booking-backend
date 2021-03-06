package pl.booking.bookmyroom.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.booking.bookmyroom.security.model.LoginStatus;
import pl.booking.bookmyroom.user.model.User;
import pl.booking.bookmyroom.user.model.UserLogInRequest;
import pl.booking.bookmyroom.user.model.UserRegistrationRequest;
import pl.booking.bookmyroom.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    LoginStatus loginStatus;

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    @ResponseStatus(code = HttpStatus.OK)
    public String mainPage() {
        return "<h1> Book a Room! </h1>";
    }

    @PostMapping(value = "/register")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<String> registerNewUser (@RequestBody @Valid UserRegistrationRequest request){
        if(!userService.createNewUser(request))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity<String> tryLogIn(HttpServletRequest sReq, @RequestBody@Valid UserLogInRequest request){
        if(!userService.tryLogIn(sReq, request))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {

            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //TODO remove this before release
    @GetMapping(value = "/all")
    @ResponseStatus(code = HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
