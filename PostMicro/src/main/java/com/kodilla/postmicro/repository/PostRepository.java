package com.kodilla.postmicro.repository;

import com.kodilla.postmicro.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post save(Post post);
    List<Post> findAll();
    Optional<List<Post>> findAllByMovieId(String movieId);
    void deleteById(Long id);
}
