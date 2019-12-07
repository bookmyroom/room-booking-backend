package pl.booking.bookmyroom.security.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter@Setter
@Service
public class LoginStatus {

    public static final LoginStatus INSTANCE = new LoginStatus();

    private boolean loggedIn;
    private Integer userId;
    private String username;

    private LoginStatus() {
        loggedIn = false;
        username = "none";
    }
}