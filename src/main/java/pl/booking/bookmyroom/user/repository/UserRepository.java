package pl.booking.bookmyroom.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.booking.bookmyroom.user.model.User;

<<<<<<< Updated upstream
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
=======
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findByEmail(String email);
>>>>>>> Stashed changes
}
