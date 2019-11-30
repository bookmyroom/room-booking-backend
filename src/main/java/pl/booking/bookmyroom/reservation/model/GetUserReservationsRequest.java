package pl.booking.bookmyroom.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class GetUserReservationsRequest {
    @NotNull
    private Integer userId;
}
