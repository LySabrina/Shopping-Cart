package com.example.demo.review;

import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         UserRepository userRepository
                         ) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
    }

    public Page<ReviewInfoDTO> getReviewsForProduct(Long id, Pageable pageable){
        Page<Review> pages = reviewRepository.findAllById(id, pageable);
        return pages.map((item)->
                new ReviewInfoDTO(item.getText(), item.getCreationDate(), item.getUser())
                );
    }

    public void createReview(ReviewDTO reviewDTO) throws UserNotFoundException {
        Optional<User> foundUser = userRepository.findByEmail(reviewDTO.getEmail());
        if(foundUser.isEmpty()){
            throw new UserNotFoundException(reviewDTO.getEmail());
        }
        Review newReview = new Review();
        newReview.setText(reviewDTO.getText());
        newReview.setCreationDate(LocalDateTime.now());
        newReview.setUser(foundUser.get());
        reviewRepository.save(newReview);
    }



}
