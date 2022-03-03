package fr.easit.configuration;

import java.util.ArrayList;
import java.util.Optional;

import fr.easit.models.User;
import fr.easit.repositories.UserRepository;
import fr.easit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class AuthProvider implements AuthenticationProvider {


    @Autowired
    private  UserRepository userRepository;

    private  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if(isUserAuthenticated(username, password)) {
            return new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>());
        }
        return null;


    }

    public boolean isUserAuthenticated(String username, String password){
        Optional<User> optionalUser = userRepository.findUserByUsername(username);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return bCryptPasswordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override public boolean supports(Class<?> authentication) {
        return true;
    }
}
