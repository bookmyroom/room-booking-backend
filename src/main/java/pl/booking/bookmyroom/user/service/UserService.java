package pl.booking.bookmyroom.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.user.model.User;
import pl.booking.bookmyroom.user.model.UserLogInRequest;
import pl.booking.bookmyroom.user.model.UserRegistrationRequest;
import pl.booking.bookmyroom.user.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Service
public class UserService {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Object loggedInUser = new Object();

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public boolean createNewUser(UserRegistrationRequest request) {
        if(userRepository.findAll().stream().anyMatch(u -> u.getEmail().equals(request.getEmail()))){
            return false;
        }

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword());

        User user = new User();
        user.setActive(true);
        user.setRoles("USER");
        user.setEmail(request.getEmail());
        if(request.getPassword().equals(request.getPasswordValidCheck())){
            String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
            user.setPassword(encodedPassword);
        } else {
            return false;
        }

        token.setDetails(user);

        try {
            Authentication auth = authManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (BadCredentialsException e) {
            System.out.println("error");
        }

        userRepository.save(user);
        return true;
    }

    public boolean tryLogIn(HttpServletRequest sReq, UserLogInRequest request) {
        if(userRepository.findAll()
                .stream()
                .filter(u -> u.getEmail().equals(request.getEmail()))
                .allMatch(u -> bCryptPasswordEncoder.matches(request.getPassword(), u.getPassword())))
        {
            UsernamePasswordAuthenticationToken authReq =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            Authentication auth = authManager.authenticate(authReq);

            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
            HttpSession session = sReq.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);

            return true;
        } else return false;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
