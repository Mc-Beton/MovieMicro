package com.kodilla.postmicro.mapper;

import com.kodilla.postmicro.domain.Post;
import com.kodilla.postmicro.domain.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostMapper {

    public Post mapToPost(PostDto postDto) {
        return new Post(
                postDto.getMovieId(),
                postDto.getUserId(),
                postDto.getContent()
        );
    }

    public PostDto mapToPostDto(Post post) {
        return new PostDto(
                post.getId(),
                post.getMovieId(),
                post.getUserId(),
                post.getContent()
        );
    }

    public List<PostDto> mapToPostDtoList(List<Post> postList) {
        return postList.stream()
                .map(this::mapToPostDto)
                .collect(Collectors.toList());
    }
}
