package pl.booking.bookmyroom.hotel.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateHotelRequest {

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String streetNumber;

    @NotNull
    private String phoneNumber;

    @NotNull
    @Max(5)
    private Integer standard;

    @NotNull
    private Integer corporationId;
}
