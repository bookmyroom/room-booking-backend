package pl.booking.bookmyroom.reservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.booking.bookmyroom.reservation.model.DeleteReservationRequest;
import pl.booking.bookmyroom.reservation.model.EditReservationRequest;
import pl.booking.bookmyroom.reservation.model.MakeReservationRequest;
import pl.booking.bookmyroom.reservation.service.ReservationService;

import javax.validation.Valid;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService service;

    @Autowired
    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    public boolean makeNewReservation(@RequestBody @Valid MakeReservationRequest request){
        return service.makeReservation(request);
    }

    @DeleteMapping
    public boolean deleteReservation(@RequestBody @Valid DeleteReservationRequest request){
        return service.deleteReservation(request);
    }

    @PutMapping
    public boolean editReservation(@RequestBody @Valid EditReservationRequest request){
        return service.editReservation(request);
    }
}
