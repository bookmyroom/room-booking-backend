package pl.booking.bookmyroom.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.booking.bookmyroom.security.model.LoginStatus;
import pl.booking.bookmyroom.user.model.User;
import pl.booking.bookmyroom.user.model.UserLogInRequest;
import pl.booking.bookmyroom.user.model.UserRegistrationRequest;
import pl.booking.bookmyroom.user.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private LoginStatus loginStatus;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public boolean createNewUser(UserRegistrationRequest request) {
        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            System.out.println("VVVVVVVVVVVVVVVVVVV");
            return false;
        }

        User user = new User();
        user.setEnabled(true);
        user.setRoles("USER");
        user.setUsername(request.getUsername());
        if(request.getPassword().equals(request.getPasswordValidCheck())){
            String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
            user.setPassword(encodedPassword);
        } else {
            System.out.println("AAAAAAAAAAAAAAA");
            return false;
        }

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        token.setDetails(user);

        try {
            Authentication auth = authManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);

        } catch (BadCredentialsException e) {
            System.out.println("BadCredentialsException");
        }
        userRepository.save(user);
        return true;
    }

    public boolean tryLogIn(HttpServletRequest sReq, UserLogInRequest request) {
        if(userRepository.findByUsername(request.getUsername()).isPresent())
        {
            System.out.println("User " + request.getUsername() + " found!");
            if(bCryptPasswordEncoder.matches(
                    request.getPassword(),
                    userRepository.findByUsername(request.getUsername()).get().getPassword()
            ))
            {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword());

                System.out.println("Token created");
                User user = new User();
                user.setRoles("USER");
                user.setEnabled(true);
                user.setUsername(request.getUsername());
                user.setPassword(request.getPassword());


                token.setDetails(user);
                System.out.println(token.getCredentials().toString());

                try {
                    Authentication auth = authManager.authenticate(token);
                    if(auth.isAuthenticated())
                        SecurityContextHolder.getContext().setAuthentication(auth);

                } catch (BadCredentialsException e) {
                    System.out.println("BadCredentials for user" + user.getUsername());
                }

                loginStatus.setLoggedIn(true);
                loginStatus.setUsername(request.getUsername());

                loginStatus.setUserId(userRepository.findByUsername(request.getUsername()).get().getId());
                loginStatus.setUserType(userRepository.findByUsername(request.getUsername()).get().getRoles());

                return true;
            } else {
                System.out.println("User " + request.getUsername() + " password dont match!");
                return false;
            }
        } else {
            System.out.println("User " + request.getUsername() + " not found!");
            return false;
        }
        //return false;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
