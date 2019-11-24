package pl.booking.bookmyroom.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.booking.bookmyroom.hotel.model.*;
import pl.booking.bookmyroom.hotel.service.HotelService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/hotels")
@CrossOrigin(value = "http://localhost:8000")
public class HotelController {

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addHotel(@RequestBody @Valid CreateHotelRequest request) {
        hotelService.addHotel(request);
    }

    @DeleteMapping
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteHotel(@RequestBody @Valid DeleteHotelRequest request) {
        hotelService.deleteHotel(request);
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<GetHotelResponse> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @GetMapping("city/{city}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Hotel> findHotelByCity(@PathVariable String city) {
        return hotelService.findHotelByCity(city);
    }

    @GetMapping("company-id/{corporationId}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Hotel> findHotelByCorporationId(@PathVariable Integer corporationId){
        return hotelService.findHotelByCorporationId(corporationId);
    }

    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public void editHotel(@RequestBody EditHotelRequest request, @RequestParam Integer id) {
        hotelService.editHotel(request, id);
    }
}
