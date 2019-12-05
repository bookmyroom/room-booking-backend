package pl.booking.bookmyroom.security.controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.booking.bookmyroom.security.model.MyUserDetails;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin
public class SecurityController {

    @GetMapping(value = "/")
    public String loggedOut() {
        return "<div>Logged out</div>";
    }

    @GetMapping(value = "/logged")
    @ResponseBody
    public String logged() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getPrincipal() != null)
            return auth.getName();
        else return "Not logged in Prince";
    }
}
