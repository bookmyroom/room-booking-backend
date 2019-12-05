package pl.booking.bookmyroom.security.controller;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SecurityController {

    @GetMapping(value = "/")
    public String loggedOut() {
        return "<div>Logged out</div>";
    }

    @GetMapping(value = "/logged")
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    public String logged() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getPrincipal() != null) {
            return auth.getName();
        }
        else return "Not logged in Prince";
    }
}
