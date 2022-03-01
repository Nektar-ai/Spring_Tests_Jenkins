package fr.easit.services;

import fr.easit.models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public abstract List<User> findAll();

    public abstract UserDetails findUserByUsername(String username);

    public abstract void createUser(UserDetails user);

}
