package pl.booking.bookmyroom.reservation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ChangeStatusRequest {
    @NotNull
    private Integer reservationId;

    @NotNull
    private ReservationStatus status;
}
