package pl.booking.bookmyroom.hotel.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class DeleteHotelRequest {

    @NotNull
    private Integer id;
}
