package com.example.demo.repository;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


/// /Scratch that, DataJpaTest is for SQL DB, MongoDB is nosql
//@DataJpaTest // will create a Spring context for us to use to test our repository
//    // also use this annotation when dealing with testing repository
//
//    // when testing our Repository, use an in-memory datbase b/c we don't want test values inside our production database
//    //we configured an applicatication.properties file that contains set up for our in-memory database to be used for testing purpose

@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void reset() {
        userRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        User user = new User("email@email.com", "password", "lname", "fname");

        // when
        User u = userRepository.save(user);
        Optional<User> foundUser =  userRepository.findByEmail("email@email.com");

        // that
        assertThat(foundUser.isPresent()).isEqualTo(true);
        assertThat(foundUser.get()).isEqualTo(user);
    }

    @Test
    void failFindByEmail() {
        Optional<User> notFoundUser = userRepository.findByEmail("fakeEmail@email.com");
        assertThat(notFoundUser.isPresent()).isEqualTo(false);

    }

}