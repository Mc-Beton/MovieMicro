package com.kodilla.ratingmicro.Repository;

import com.kodilla.ratingmicro.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

    Rating save(Rating rating);
    List<Rating> findAll();
    Optional<List<Rating>> findAllByMovieId(String movieId);
    Optional<Rating> findByMovieIdAndUserId(String movieId, Long userId);
    void deleteById(Long id);
    Optional<Rating> findById(Long id);
}
