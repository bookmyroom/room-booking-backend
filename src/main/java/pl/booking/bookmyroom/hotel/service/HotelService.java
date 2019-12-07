package pl.booking.bookmyroom.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.corporation.model.Corporation;
import pl.booking.bookmyroom.hotel.model.*;
import pl.booking.bookmyroom.hotel.repository.HotelRepository;

import java.util.*;
import java.util.stream.Collectors;

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
        hotel.setName(request.getName());
        hotel.setCity(request.getCity());
        hotel.setStreet(request.getStreet());
        hotel.setStreetNumber(request.getStreetNumber());
        hotel.setPhoneNumber(request.getPhoneNumber());
        hotel.setStandard(request.getStandard());
        hotel.setCorporationId(request.getCorporationId());
        hotelRepository.save(hotel);

        AddRoomsToHotelRequest[] roomsToHotelRequests = request.getRoomsToHotelRequests();
        for (AddRoomsToHotelRequest r : roomsToHotelRequests) {
            roomService.addRoomsToHotel(r, hotel.getId());
        }
    }

    public void deleteHotel(DeleteHotelRequest request) {
        hotelRepository.deleteById(request.getId());
    }

    public List<GetHotelResponse> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        List<RoomType> roomTypes = roomService.getAllRoomTypes();
        List<GetHotelResponse> response = new ArrayList<>();
        for (Hotel h : hotels) {
            List<RoomType> hotelRooms = roomTypes.stream().filter(r -> r.getHotelId().equals(h.getId())).collect(Collectors.toList());
            response.add(new GetHotelResponse(h, hotelRooms));
        }
        return response;
    }

    public List<Hotel> getHotelsByCity(String city){
        return hotelRepository.findByCity(city);
    }

    public List<Hotel> getHotelsByCorporationId(Integer corporationId) {
        return hotelRepository.findHotelByCorporationId(corporationId);
    }

//    public List<Hotel> getHotelsByStandard(Integer standard) {
//        return hotelRepository.findByStandard(standard);
//    }

    private List<Hotel> getHotelsByStandardAndCity(Integer standard, String city){
        return hotelRepository.findByStandardAndCity(standard, city);
    }

    public List<Hotel> findHotelsMatchingQuery(String city,
                                               Integer hotelStandard,
                                               Float priceMin,
                                               Float priceMax,
                                               Integer numberOfBeds,
                                               RoomStandard roomStandard,
                                               Date start,
                                               Date end){
        List<Hotel> searchResult;

        if(hotelStandard != null){
            searchResult = getHotelsByStandardAndCity(hotelStandard, city);
        } else {
            searchResult = getHotelsByCity(city);
        }

        searchResult = searchResult.stream()
                .filter(h -> roomService.anyRoomsMatchQuery(h.getId(),
                        Optional.ofNullable(numberOfBeds),
                        Optional.ofNullable(roomStandard),
                        Optional.ofNullable(priceMin),
                        Optional.ofNullable(priceMax),
                        Optional.ofNullable(start),
                        Optional.ofNullable(end)))
                .collect(Collectors.toList());

        return searchResult;
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

    public Float getRoomPriceByRoomTypeId(Integer id) {
        return roomService.getRoomTypeById(id).getPrice();
    }
}