package pl.booking.bookmyroom.hotel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@NoArgsConstructor
public class HotelSearchRequest {
    private String city;

    @Max(5)
    @Min(1)
    private Integer hotelStandard;

    private Float priceMin, priceMax;

    private Integer numberOfBeds;

    private RoomStandard roomStandard;
}
