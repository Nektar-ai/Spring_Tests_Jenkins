package fr.easit.controllers;

import fr.easit.configuration.ApiError;
import fr.easit.models.User;
import fr.easit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class AuthController {

    Logger logger = Logger.getLogger("AuthController");

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    //User : dbrewse0@gnu.org

    @GetMapping("/login")

    public String login(HttpServletRequest request, HttpSession session, HttpServletResponse response) throws IOException {

        String key = "SPRING_SECURITY_LAST_EXCEPTION";
        Exception exception = (Exception) request.getSession().getAttribute(key);
        String errorMessage = null;
        if (exception instanceof BadCredentialsException) {
            errorMessage = "Invalid username and password!!";
        } else if (exception instanceof LockedException) {
            errorMessage = exception.getMessage();
        }/*else{
            errorMessage = "Invalid username and password!!";
        }*/
        logger.warning(errorMessage);

        if(errorMessage != null){
            ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, errorMessage);
            response.setHeader("Custom-Header", "foo");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().println(error);
        }

        return "login";
    }


}