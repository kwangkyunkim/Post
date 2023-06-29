package com.sparta.post.controller;

import com.sparta.post.dto.PostRequestDto;
import com.sparta.post.dto.PostResponseDto;
import com.sparta.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
// REST API 요청을 처리하는 컨트롤러 // JSON
@RequestMapping("/api")
// 기본경로 지정
@RequiredArgsConstructor
// 생성자 주입 ( PostService 객체를 생성자에서 주입 )
public class PostController {
    private final PostService postService;

    // 모든 게시물 조회 요청에 대한 핸들러
    @GetMapping("/posts")
    //  "/posts" 경로로 GET 요청이 오면 이 메서드가 실행됩니다.
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
        // postService 를 통해 모든 게시물을 조회한 결과를 반환합니다.
    }

    // 특정 게시물 조회 요청에 대한 핸들러
    @GetMapping("/post/{id}")
    public PostResponseDto getPostById(@PathVariable Long id) {
        return postService.findPostResponse(id);
        // getPostById(Long id): 특정 게시물을 조회
    }

    // 게시물 생성 요청에 대한 핸들러
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }  // requestDto 를 통해 게시물을 생성한 결과를 반환

    // 게시물 수정 요청에 대한 핸들러
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id, requestDto);
    } // id에 해당하는 게시물을 requestDto의 내용으로 수정한 결과를 반환

    // 게시물 삭제 요청에 대한 핸들러
    @DeleteMapping("/post/{id}")
    public @ResponseBody Map<String, Boolean>
    deletePost(@PathVariable Long id, @RequestBody Map<String, String> password) {
       // id에 해당하는 게시물을 password 에 해당하는 비밀번호로 삭제한 결과를 반환
        // 응답 결과를 JSON 형식으로 반환하도록 지정 ( @RequestBody)
        Map<String, Boolean> deleteResponse = new HashMap<>();
        //  응답 결과를 담을 맵 객체를 생성
        deleteResponse.put("success", postService.deletePost(id, password.get("password")));
        // 삭제 결과를 deleteResponse 맵에 "success" 키로 저장
        return deleteResponse;

    }
}
