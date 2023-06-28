package com.sparta.post.service;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.entity.Post;
import com.sparta.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);

        Post savedPost = postRepository.save(post);

        return new PostResponseDto(savedPost);
    }

    public PostResponseDto findPostResponse(Long id) {
        Post post = findPost(id);
        return new PostResponseDto(post);
    }

    public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
        Post post = findPost(id);
        if (post.getPassword().equals(requestDto.getPassword())) {
            post.update(requestDto);
            return new PostResponseDto(post);
        } else {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다..");
        }
    }

    public Boolean deletePost(Long id, String password) {
        Post post = findPost(id);
        if (post.getPassword().equals(password)) {
            postRepository.delete(post);
            return true;
        }
        return false;
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 post는 없습니다.")
        );
    }
}