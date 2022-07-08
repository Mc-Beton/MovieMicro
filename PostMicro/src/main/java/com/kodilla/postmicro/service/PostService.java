package com.kodilla.postmicro.service;

import com.kodilla.postmicro.domain.Post;
import com.kodilla.postmicro.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post createNewPostToMovieByUser(Post post) {
        return postRepository.save(post);
    }

    public List<Post> getPostsForMovie(String movieId) {
        Optional<List<Post>> postList = Optional.of(postRepository.findAllByMovieId(movieId).get());
        return postList.orElseGet(ArrayList::new);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
