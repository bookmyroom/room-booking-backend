package pl.booking.bookmyroom.hotel.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class AddRoomsToHotelRequest {
    @NotNull
    private Float price;

    @NotNull
    private Integer numOfBeds;

    @NotNull
    private RoomStandard standard;

    @NotNull
    private Integer numOfRooms;
}
