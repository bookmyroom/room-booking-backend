package pl.booking.bookmyroom.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.booking.bookmyroom.hotel.model.Hotel;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    @Query(value = "SELECT h FROM Hotel h WHERE h.city=:city")
    List<Hotel> findByCity(String city);

    @Query(value = "SELECT h FROM Hotel h WHERE h.standard=:standard")
    List<Hotel> findByStandard(Integer standard);

    @Query(value = "SELECT h FROM Hotel h WHERE h.standard=:standard AND h.city=:city")
    List<Hotel> findByStandardAndCity(Integer standard, String city);

    @Query(value = "SELECT h FROM Hotel h WHERE h.corporationId=:id")
    List<Hotel> findHotelByCorporationId(Integer id);
}
