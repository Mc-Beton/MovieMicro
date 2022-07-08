package com.kodilla.postmicro.controller;

import com.kodilla.postmicro.domain.Post;
import com.kodilla.postmicro.domain.PostDto;
import com.kodilla.postmicro.mapper.PostMapper;
import com.kodilla.postmicro.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;

    @GetMapping("all")
    public ResponseEntity<List<PostDto>> getPostList() {
        List<Post> postList = postService.getAllPosts();
        return ResponseEntity.ok(postMapper.mapToPostDtoList(postList));
    }

    @GetMapping("{movieId}")
    public ResponseEntity<List<PostDto>> getMoviePostList(@PathVariable String movieId) {
        List<Post> postList = postService.getPostsForMovie(movieId);
        return ResponseEntity.ok(postMapper.mapToPostDtoList(postList));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addNewPost(@RequestBody PostDto postDto) {
        Post post = postMapper.mapToPost(postDto);
        postService.createNewPostToMovieByUser(post);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}
