package pl.booking.bookmyroom.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.hotel.model.RoomType;
import pl.booking.bookmyroom.hotel.repository.RoomRepository;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public RoomType getRoomTypeById(Integer id) {
        return roomRepository.findById(id).get();
    }

    public Integer getNumberOfRoomsById(Integer id) {
        return roomRepository.findById(id).get().getNumberOfRooms();
    }
}