package pl.booking.bookmyroom.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.corporation.model.Corporation;
import pl.booking.bookmyroom.hotel.model.CreateHotelRequest;
import pl.booking.bookmyroom.hotel.model.DeleteHotelRequest;
import pl.booking.bookmyroom.hotel.model.EditHotelRequest;
import pl.booking.bookmyroom.hotel.model.Hotel;
import pl.booking.bookmyroom.hotel.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final RoomService roomService;

    @Autowired
    public HotelService(HotelRepository hotelRepository, RoomService roomService) {
        this.hotelRepository = hotelRepository;
        this.roomService = roomService;
    }

    public void addHotel(CreateHotelRequest request) {
        Hotel hotel = new Hotel();
        hotel.setCity(request.getCity());
        hotel.setStreet(request.getStreet());
        hotel.setStreetNumber(request.getStreetNumber());
        hotel.setPhoneNumber(request.getPhoneNumber());
        hotel.setStandard(request.getStandard());
        hotel.setCorporationId(request.getCorporationId());
        hotelRepository.save(hotel);
    }

    public void deleteHotel(DeleteHotelRequest request) {
        hotelRepository.deleteById(request.getId());
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public List<Hotel> findHotelByCity(String city){
        return hotelRepository.findByCity(city);
    }

    public List<Hotel> findHotelByCorporationId(Integer corporationId) {
        return hotelRepository.findHotelByCorporationId(corporationId);
    }

    public void editHotel(EditHotelRequest request, Integer id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if(hotel.isPresent()) {
            Hotel hotelToEdit = hotel.get();
            if (request.getPhoneNumber() != null) {
                hotelToEdit.setPhoneNumber(request.getPhoneNumber());
            }
            if (request.getStandard() != null) {
                hotelToEdit.setStandard(request.getStandard());
            }
            hotelRepository.save(hotelToEdit);
        }
    }

    public Integer getNumberOfRoomsByRoomTypeId(Integer id) {
        return roomService.getNumberOfRoomsById(id);
    }
}