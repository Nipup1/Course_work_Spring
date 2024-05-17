package com.example.course_work.Service;

import com.example.course_work.Config.UserDetail;
import com.example.course_work.Model.User;
import com.example.course_work.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(UserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException(email + " not found"));
    }

    public Optional<User> getUser(String email){
        if(email == null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return userRepository.findByEmail((String) authentication.getPrincipal());
        }else{
            return userRepository.findByEmail(email);
        }
    }

    public void addUser(User user){
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
