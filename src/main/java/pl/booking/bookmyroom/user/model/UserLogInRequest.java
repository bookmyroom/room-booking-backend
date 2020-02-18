package pl.booking.bookmyroom.user.model;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
public class UserLogInRequest {
    @NotNull
    @Email
    private String username;
    @NotNull
    private String password;

    public UserLogInRequest(@NotNull @Email String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }
}
