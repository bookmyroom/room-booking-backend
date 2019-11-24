package pl.booking.bookmyroom.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.hotel.service.HotelService;
import pl.booking.bookmyroom.reservation.model.DeleteReservationRequest;
import pl.booking.bookmyroom.reservation.model.EditReservationRequest;
import pl.booking.bookmyroom.reservation.model.MakeReservationRequest;
import pl.booking.bookmyroom.reservation.model.Reservation;
import pl.booking.bookmyroom.reservation.repository.ReservationRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationService {
    private final ReservationRepository repository;
    private final HotelService hotelService;

    @Autowired
    public ReservationService(ReservationRepository repository, HotelService hotelService) {
        this.repository = repository;
        this.hotelService = hotelService;
    }

    public boolean makeReservation(MakeReservationRequest request) {
        if (hotelHasFreeRooms(request.getStartDate(), request.getEndDate(), request.getIdRoom())) {
            Reservation reservation = new Reservation();
            reservation.setReservationStart(request.getStartDate());
            reservation.setReservationEnd(request.getEndDate());
            reservation.setHotelsId(request.getIdHotel());
            reservation.setRoomTypeId(request.getIdRoom());
            reservation.setUserId(request.getIdUser());
            repository.save(reservation);
            return true;
        } else {
            return false;
        }
    }

    private boolean hotelHasFreeRooms(Date startDate, Date endDate, Integer roomId){
        long numOfReservations = repository.findAllByRoomId(roomId).stream()
                .filter(r -> r.isCollidingWith(startDate, endDate))
                .count();
        long numOfRoomsInHotel = (long) hotelService.getNumberOfRoomsByRoomTypeId(roomId);
        return numOfReservations >= numOfRoomsInHotel;
    }

    public boolean deleteReservation(DeleteReservationRequest request) {
        Optional<Reservation> reservation = repository.findById(request.getReservationId());
        if(reservation.isPresent()){
            repository.delete(reservation.get());
            return true;
        } else {
            return false;
        }
    }

    public boolean editReservation(EditReservationRequest request) {
        Optional<Reservation> reservation = repository.findById(request.getReservationId());
        if (reservation.isPresent()){
            if(hotelHasFreeRooms(request.getStartDate(), request.getEndDate(), request.getIdRoom())) {
                Reservation r = reservation.get();
                r.setReservationStart(request.getStartDate());
                r.setReservationEnd(request.getEndDate());
                r.setHotelsId(request.getIdHotel());
                r.setRoomTypeId(request.getIdRoom());
                repository.save(r);
                return true;
            }
        }
        return false;
    }
}
