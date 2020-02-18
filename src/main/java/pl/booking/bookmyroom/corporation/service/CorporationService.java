package pl.booking.bookmyroom.corporation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.corporation.model.Corporation;
import pl.booking.bookmyroom.corporation.model.CreateCorporationRequest;
import pl.booking.bookmyroom.corporation.model.CorporationRepository;
import pl.booking.bookmyroom.corporation.model.LoginCorporationRequest;
import pl.booking.bookmyroom.security.model.LoginStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CorporationService {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    LoginStatus loginStatus;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final CorporationRepository corporationRepository;

    @Autowired
    public CorporationService(CorporationRepository corporationRepository) {
        this.corporationRepository = corporationRepository;
    }

    public boolean addCorporation(CreateCorporationRequest request) {
        if(!getCorporationByEmail(request.getEmail()).isEmpty()) {
            return false;
        } else {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword());


            Corporation corporation = new Corporation();
            corporation.setName(request.getName());
            if(request.getPassword().equals(request.getRepeatedPassword())) {
                String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
                corporation.setPassword(encodedPassword);
            } else {
                return false;
            }

            corporation.setEmail(request.getEmail());
            corporation.setRoles("CORPO");

            token.setDetails(corporation);

            try {
                Authentication auth = authManager.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (BadCredentialsException e) {
                System.out.println("error");
            }

            corporationRepository.save(corporation);
            return true;
        }
    }

    public boolean loginCorporation(HttpServletRequest sReq, LoginCorporationRequest request) {
        if(getCorporationByEmail(request.getEmail()).stream()
                .anyMatch(c -> bCryptPasswordEncoder.matches(request.getPassword(), c.getPassword()))) {

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword());

            Corporation user = new Corporation();
            user.setRoles("CORPO");
            user.setActive(true);
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());


            token.setDetails(user);

            try {
                Authentication auth = authManager.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (BadCredentialsException e) {
                System.out.println("error");
            }

            loginStatus.setLoggedIn(true);
            loginStatus.setUsername(request.getEmail());
            corporationRepository.findByEmail(request.getEmail()).forEach(u -> loginStatus.setUserId(u.getId()));
            corporationRepository.findByEmail(request.getEmail()).forEach(u -> loginStatus.setUserType(u.getRoles()));
            return true;
        } else return false;
    }

    public List<Corporation> getAllCorporations(){
        return corporationRepository.findAll();
    }

    public List<Corporation> getCorporationByEmail(String email){
        return corporationRepository.findCorporationByEmail(email);
    }
}
