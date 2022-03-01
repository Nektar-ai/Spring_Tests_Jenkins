package fr.easit.services;

import fr.easit.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService extends UserDetailsService {
    public abstract List<User> findAll();
    public abstract Optional<User> findUserByUsername(String username);

    public abstract void createUser(UserDetails user);

}
