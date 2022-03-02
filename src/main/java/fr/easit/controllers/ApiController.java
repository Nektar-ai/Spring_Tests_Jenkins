package fr.easit.controllers;

import fr.easit.configuration.ApiError;
import fr.easit.configuration.AuthProvider;
import fr.easit.dto.ArticleDTO;
import fr.easit.models.User;
import fr.easit.repositories.UserRepository;
import fr.easit.services.ArticleService;
import fr.easit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@ResponseBody
public class ApiController {

    Logger log = Logger.getLogger("ApiController.class");

    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/api/articles", method = RequestMethod.GET)
    public ResponseEntity<?> getArticlesJson(@RequestParam(required = false) String username, @RequestParam(required = false) String password) {
        boolean authed = false;
        User user = null;
        if(username == null || password== null){
           authed = false;
           log.warning("null auth");
            ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, "Field username or password not filled");
            return new ResponseEntity<>(error, error.getHttpStatus());
        }else {
            Optional<User> optionalUser = userRepository.findUserByUsername(username);
            if(optionalUser.isPresent()){
                 user = optionalUser.get();
                 authed = bCryptPasswordEncoder.matches(password, user.getPassword());
            } else {
                log.warning("bad auth");
                ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, "User not found");
                return new ResponseEntity<>(error, error.getHttpStatus());
            }
        }

        if(authed){
            log.warning("authed");
            return new ResponseEntity<>(articleService.getArticles(user), HttpStatus.OK);
        }else{
            ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, "Bad password");
            return new ResponseEntity<>(error, error.getHttpStatus());
        }

    }



}
