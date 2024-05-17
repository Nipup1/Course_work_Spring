package com.example.course_work.ServiceTest;

import com.example.course_work.Model.User;
import com.example.course_work.Repository.UserRepository;
import com.example.course_work.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testLoadUserByUsername_WhenUserExists() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername(email);

        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsername_WhenUserDoesNotExist() {
        String email = "test@example.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(email);
        });
    }

    @Test
    void testGetUser_WhenEmailIsNull() {
        String email = "test@example.com";
        when(authentication.getPrincipal()).thenReturn(email);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUser(null);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
    }

    @Test
    void testGetUser_WhenEmailIsNotNull() {
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<User> result = userService.getUser(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
    }

    @Test
    void testAddUser() {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");
        String encodedPassword = "encodedPassword";
        when(encoder.encode(user.getPassword())).thenReturn(encodedPassword);
        when(userRepository.save(user)).thenReturn(user);

        userService.addUser(user);

        assertEquals(encodedPassword, user.getPassword());
        verify(userRepository, times(1)).save(user);
    }
}
