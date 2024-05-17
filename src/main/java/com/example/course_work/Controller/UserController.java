package com.example.course_work.Controller;

import com.example.course_work.Dtos.AppError;
import com.example.course_work.Model.User;
import com.example.course_work.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/regist")
    public ResponseEntity<?> registUser(@RequestBody User user){
        try {
            if(userService.getUser(user.getEmail()).isPresent())
                throw new BadCredentialsException("Неверныe данные");
        } catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.FORBIDDEN.value(), e.getMessage()), HttpStatus.FORBIDDEN);
        }

        userService.addUser(user);
        return ResponseEntity.ok(userService.getUser(user.getEmail()));
    }

    @GetMapping("/user")
    public Optional<User> getUserByEmail(@RequestParam(required = false) String email){
        return userService.getUser(email);
    }
}
