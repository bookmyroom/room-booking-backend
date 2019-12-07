package pl.booking.bookmyroom.security.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter@Setter
@Service
public class LoginStatus {

    public static final LoginStatus INSTANCE = new LoginStatus();

    private Integer userId;
    private boolean loggedIn;
    private String userType;

    private String username;

    private LoginStatus() {
        loggedIn = false;
        username = "none";
        userId = 0;
        userType = "ANON";
    }
}
