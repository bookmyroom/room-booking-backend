package pl.booking.bookmyroom.corporation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LoginCorporationRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;
}
