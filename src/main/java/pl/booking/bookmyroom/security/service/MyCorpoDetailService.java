package pl.booking.bookmyroom.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.corporation.model.CorporationRepository;
import pl.booking.bookmyroom.security.model.MyUserDetails;
import pl.booking.bookmyroom.user.model.User;
import pl.booking.bookmyroom.user.repository.UserRepository;

import java.util.Optional;

@Service
public class MyCorpoDetailService implements UserDetailsService {

    CorporationRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found" + userName));
        return user.map(MyUserDetails::new).get();
    }


}
