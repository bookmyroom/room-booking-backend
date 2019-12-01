package pl.booking.bookmyroom.reservation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ChangeStatusRequest {
    @NotNull
    private Integer reservationId;
    @NotNull
    private ReservationStatus status;
}
