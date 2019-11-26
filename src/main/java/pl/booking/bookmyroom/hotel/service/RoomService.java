package pl.booking.bookmyroom.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.hotel.model.AddRoomsToHotelRequest;
import pl.booking.bookmyroom.hotel.model.RoomStandard;
import pl.booking.bookmyroom.hotel.model.RoomType;
import pl.booking.bookmyroom.hotel.repository.RoomRepository;

import java.util.List;

@Service
class RoomService {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void addRoomsToHotel(AddRoomsToHotelRequest request, Integer hotelsId){
        RoomType roomType = new RoomType();
        roomType.setHotelId(hotelsId);
        roomType.setNumberOfBeds(request.getNumOfBeds());
        roomType.setNumberOfRooms(request.getNumOfRooms());
        roomType.setPrice(request.getPrice());
        roomType.setStandard(request.getStandard());
        roomRepository.save(roomType);
    }

    public List<RoomType> getAllRoomTypes(){
        return roomRepository.findAll();
    }

    public RoomType getRoomTypeById(Integer id) {
        return roomRepository.findById(id).get();
    }

    public Integer getNumberOfRoomsById(Integer id) {
        return roomRepository.findById(id).get().getNumberOfRooms();
    }

    public boolean anyRoomsMatchQuery(Integer hotelsId, Integer numberOfBeds){
        List<RoomType> hotelRooms = roomRepository.findByHotelsId(hotelsId);
        return hotelRooms.stream()
                .anyMatch(r -> r.getNumberOfBeds().equals(numberOfBeds));
    }

    public  boolean anyRoomsMatchQuery(Integer hotelsId, RoomStandard standard){
        List<RoomType> hotelRooms = roomRepository.findByHotelsId(hotelsId);
        return hotelRooms.stream()
                .anyMatch(r -> r.getStandard().equals(standard));
    }

    public boolean anyRoomsMatchQuery(Integer hotelsId, Float priceMin, Float priceMax){
        List<RoomType> hotelRooms = roomRepository.findByHotelsId(hotelsId);
        return hotelRooms.stream()
                .anyMatch(r -> r.getPrice() >= priceMin &&
                        r.getPrice() <= priceMax);
    }
}