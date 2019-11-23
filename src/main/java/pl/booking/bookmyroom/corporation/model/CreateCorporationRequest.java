package pl.booking.bookmyroom.corporation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class CreateCorporationRequest {

    @NotNull
    private String name;

    @NotNull
    private String password;

    @NotNull
    private String repeatedPassword;

    @NotNull
    @Email
    private String email;
}
