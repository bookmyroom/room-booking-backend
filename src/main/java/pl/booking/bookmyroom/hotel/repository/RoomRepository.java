package pl.booking.bookmyroom.hotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.booking.bookmyroom.hotel.model.RoomType;

@Repository
public interface RoomRepository extends JpaRepository<RoomType, Integer> {

}
