package pl.booking.bookmyroom.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.user.model.MyUserDetails;
import pl.booking.bookmyroom.user.model.User;
import pl.booking.bookmyroom.user.repository.UserRepository;
import java.util.Optional;
@Primary
@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;


    @Autowired
    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

        return user.map(MyUserDetails::new).get();

    }
}
