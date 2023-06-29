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
    // 비지니스 로직을 수행 ( 컨트롤러와 레포지토리 사이에서 일을 한다. )


    private final PostRepository postRepository;
    // PostRepository 인터페이스를 사용하여 데이터베이스와의 상호작용을 담당하는 필드다.
    // final 키워드로 선언되어 의존성 주입을 받고, @RequiredArgsConstructor 애노테이션을 통해 해당 필드를 초기화한다.

    public List<PostResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc()
                        //생성일 기준으로 내림차순으로 모든 게시물을 조회
                .stream().map(PostResponseDto::new).toList();
                // 각 Post 객체를 PostResponseDto 로 변환한 뒤 리스트로 반환
    }
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        // Post 객체를 생성하고,

        Post savedPost = postRepository.save(post);
        // postRepository.save(post)를 통해 데이터베이스에 저장한 후,
        // 생성된 게시물(savedPost)을 PostResponseDto 로 변환하여 반환합니다.

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