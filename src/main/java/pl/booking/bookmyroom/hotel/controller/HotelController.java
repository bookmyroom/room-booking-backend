package pl.booking.bookmyroom.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.booking.bookmyroom.hotel.model.CreateHotelRequest;
import pl.booking.bookmyroom.hotel.model.DeleteHotelRequest;
import pl.booking.bookmyroom.hotel.model.EditHotelRequest;
import pl.booking.bookmyroom.hotel.model.Hotel;
import pl.booking.bookmyroom.hotel.service.HotelService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public void addHotel(@RequestBody @Valid CreateHotelRequest request) {
        hotelService.addHotel(request);
    }

    @DeleteMapping
    public void deleteHotel(@RequestBody @Valid DeleteHotelRequest request) {
        hotelService.deleteHotel(request);
    }

    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("city/{city}")
    public List<Hotel> findHotelByCity(@PathVariable String city) {
        return hotelService.findHotelByCity(city);
    }

    @GetMapping("company-id/{corporationId}")
    public List<Hotel> findHotelByCorporationId(@PathVariable Integer corporationId){
        return hotelService.findHotelByCorporationId(corporationId);
    }

    @PutMapping
    public void editHotel(@RequestBody EditHotelRequest request, @RequestParam Integer id) {
        hotelService.editHotel(request, id);
    }
}
