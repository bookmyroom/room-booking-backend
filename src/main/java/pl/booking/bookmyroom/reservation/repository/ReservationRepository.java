package pl.booking.bookmyroom.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.booking.bookmyroom.reservation.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
}
