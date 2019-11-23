package pl.booking.bookmyroom.User.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.booking.bookmyroom.User.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
