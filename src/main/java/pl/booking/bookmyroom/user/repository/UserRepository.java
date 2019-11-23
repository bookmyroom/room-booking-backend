package pl.booking.bookmyroom.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.booking.bookmyroom.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
