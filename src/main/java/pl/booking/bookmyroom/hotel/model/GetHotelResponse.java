package pl.booking.bookmyroom.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@Setter
@AllArgsConstructor
public class GetHotelResponse {
    @NotNull
    private Hotel hotel;

    @NotNull
    private List<RoomType> hotelRoomTypes;
}
