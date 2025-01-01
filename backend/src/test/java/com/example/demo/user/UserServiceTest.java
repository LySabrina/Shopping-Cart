package com.example.demo.user;

import com.example.demo.exceptions.UserAlreadyExistException;

import com.example.demo.security.UserSecurityManager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    UserSecurityManager userSecurityManager;

    @InjectMocks
    private UserService userService;

    @Test
    void getUserByEmail() {
        //arrange
        String email = "email@email.com";

        User user = new User("email@email.com", "password", "lname", "fname");
        UserDTO userDTO = new UserDTO(user.getFname(), user.getLname());

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        //Act
        UserDTO dto = userService.getUserByEmail(email);

        //assert
        Mockito.verify(userRepository).findByEmail(email);
        assertThat(userDTO).isEqualTo(dto);
    }

    @Test
    void failGetUserByEmail() {
        // arrange
        String email = "fake@gmail.com";
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        //act + assert
        assertThatExceptionOfType(UsernameNotFoundException.class).isThrownBy(() -> {
            userService.getUserByEmail(email);
        });
    }

    @Test
    void registerUser() throws UserAlreadyExistException {
        // arrange
        UserDetails userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(userSecurityManager.userExists("test@gmail.com")).thenReturn(false);
        Mockito.when(userDetails.getUsername()).thenReturn("test@gmail.com");

        // Act
        userService.registerUser(userDetails);

        // assert
        Mockito.verify(userSecurityManager).createUser(userDetails);
        Mockito.verify(userSecurityManager).userExists("test@gmail.com");
        Mockito.verifyNoMoreInteractions(userDetails, userSecurityManager, userRepository);

    }

    @Test
    void registerUserFail() {
        // arrange
        UserDetails userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(userSecurityManager.userExists("test@gmail.com")).thenReturn(true);
        Mockito.when(userDetails.getUsername()).thenReturn("test@gmail.com");

        // act + assert
        //assert
        assertThatExceptionOfType(UserAlreadyExistException.class).isThrownBy(() -> {
            //act
            userService.registerUser(userDetails);
        });
        // assert
        Mockito.verify(userSecurityManager).userExists("test@gmail.com");
        Mockito.verify(userDetails, Mockito.times(2)).getUsername();
        Mockito.verify(userSecurityManager, Mockito.never()).createUser(userDetails);

        Mockito.verifyNoMoreInteractions(userDetails, userSecurityManager, userRepository);
    }
}
