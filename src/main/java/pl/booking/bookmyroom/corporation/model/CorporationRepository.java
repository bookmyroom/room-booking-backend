package pl.booking.bookmyroom.corporation.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.booking.bookmyroom.user.model.User;

import javax.validation.constraints.Email;
import java.util.List;
import java.util.Optional;

@Repository
public interface CorporationRepository extends JpaRepository<Corporation, Integer> {

    @Query(value = "SELECT c FROM Corporation c WHERE c.email=:email")
    public List<Corporation> findCorporationByEmail(@Email String email);

    Optional<User> findByEmail(String userName);
}
