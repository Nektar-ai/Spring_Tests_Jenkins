package fr.easit.services;

import fr.easit.models.User;
import fr.easit.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
       return userRepository.findAll();
    }

    @Override
    public UserDetails findUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur n'existe pas"));
        return user;
    }

    @Override
    public void createUser(UserDetails user){
        userRepository.save((User) user);
    }

}
