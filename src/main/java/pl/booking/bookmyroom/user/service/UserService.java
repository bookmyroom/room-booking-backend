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
import pl.booking.bookmyroom.security.model.LoginStatus;
import pl.booking.bookmyroom.security.model.MyUserDetails;
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
    LoginStatus loginStatus;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    public boolean createNewUser(UserRegistrationRequest request) {
        if(!userRepository.findByEmail(request.getEmail()).isEmpty()) {
            return false;
        }



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


        userRepository.save(user);
        return true;
    }

    public boolean tryLogIn(HttpServletRequest sReq, UserLogInRequest request) {
        if(userRepository.findByEmail(request.getEmail())
                .stream()
                .allMatch(u -> bCryptPasswordEncoder.matches(request.getPassword(), u.getPassword())))
        {

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword());

            User user = new User();
            user.setRoles("USER");
            user.setActive(true);
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());


            token.setDetails(user);
            System.out.println(token.getCredentials().toString());

            try {
                Authentication auth = authManager.authenticate(token);
                SecurityContextHolder.getContext().setAuthentication(auth);

            } catch (BadCredentialsException e) {
                System.out.println("error");
            }

            loginStatus.setLoggedIn(true);
            loginStatus.setUsername(request.getEmail());

            userRepository.findByEmail(request.getEmail()).forEach(u -> loginStatus.setUserId(u.getId()));
            userRepository.findByEmail(request.getEmail()).forEach(u -> loginStatus.setUserType(u.getRoles()));
            return true;
        } else return false;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
