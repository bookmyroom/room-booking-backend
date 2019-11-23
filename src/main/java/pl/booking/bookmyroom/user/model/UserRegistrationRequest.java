package pl.booking.bookmyroom.user.model;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class UserRegistrationRequest {
    @NotNull
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String passwordValidCheck;

    public UserRegistrationRequest(@NotNull @Email String email, @NotNull String password, @NotNull String passwordValidCheck) {
        this.email = email;
        this.password = password;
        this.passwordValidCheck = passwordValidCheck;
    }
}
