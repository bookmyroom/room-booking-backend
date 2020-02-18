package pl.booking.bookmyroom.security.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pl.booking.bookmyroom.security.model.LoginStatus;


@RestController
@CrossOrigin
public class SecurityController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    LoginStatus loginStatus;

    @GetMapping(value = "/logout")
    @ResponseStatus(code = HttpStatus.OK)
    public String logout() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth.getName().equals("anonymousUser")) {
            loginStatus.setLoggedIn(false);
            loginStatus.setUsername("logged out");
            return "Logged out";
        } else return "Didn't log out";
    }

    @GetMapping(value = "/logged")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public LoginStatus logged() {

        SecurityContext sc = SecurityContextHolder.getContext();
        System.out.println(sc.getAuthentication().getName());
        Authentication auth = sc.getAuthentication();
        if(auth.getPrincipal() != null) {
            System.out.println(SecurityContextHolder.getContext().getAuthentication().toString());
            loginStatus.setLoggedIn(true);
            loginStatus.setUsername(sc.getAuthentication().getName());
            loginStatus.setUserType(sc.getAuthentication().getAuthorities().stream().findFirst().toString());
            return loginStatus;
        }
        else return null;
    }
}