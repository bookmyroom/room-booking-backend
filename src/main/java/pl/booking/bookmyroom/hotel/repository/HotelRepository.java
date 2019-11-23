package pl.booking.bookmyroom.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.booking.bookmyroom.hotel.model.Hotel;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {


    List<Hotel> findByCity(String city);

    List<Hotel> findHotelByCorporationId(Integer id);
}
