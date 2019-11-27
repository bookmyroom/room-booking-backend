package pl.booking.bookmyroom.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.booking.bookmyroom.hotel.model.RoomType;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomType, Integer> {

    @Query(value = "SELECT rt FROM RoomType rt WHERE rt.hotelId=hotelsId")
    List<RoomType> findByHotelsId(Integer hotelsId);
}
