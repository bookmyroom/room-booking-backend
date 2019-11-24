package pl.booking.bookmyroom.hotel.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

@Data
@NoArgsConstructor
public class EditHotelRequest {

    private String phoneNumber;

    @Max(5)
    private Integer standard;
}
