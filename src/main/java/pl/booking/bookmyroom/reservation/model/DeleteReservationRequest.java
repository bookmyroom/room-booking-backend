package pl.booking.bookmyroom.reservation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class DeleteReservationRequest {
    @NotNull
    private Integer reservationId;
}
