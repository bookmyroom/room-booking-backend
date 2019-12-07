package pl.booking.bookmyroom.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.corporation.model.Corporation;
import pl.booking.bookmyroom.corporation.model.CorporationRepository;
import pl.booking.bookmyroom.security.model.MyCorpoDetails;
import pl.booking.bookmyroom.security.model.MyUserDetails;
import pl.booking.bookmyroom.user.model.User;
import pl.booking.bookmyroom.user.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MyCorpoDetailService implements UserDetailsService {

    CorporationRepository corporationRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Corporation user = corporationRepository.findByEmail(userName).get(0);

        if(user == null)
            throw new UsernameNotFoundException("Not found" + userName);
        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles());
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                auth);
    }


}
