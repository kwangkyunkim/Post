package com.sparta.post.dto;


import com.sparta.post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
public class PostResponseDto {
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    // 게시물 조회 결과를 전달하기 위한 데이터 전송 객체(DTO)이다.
    // 주로 서비스나 컨트롤러에서 게시물 정보를 조회한 후, 클라이언트에게 전달하기 위해 사용된다.
    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.author = post.getAuthor();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();

        // PostResponseDto 클래스에는 Post 객체를 매개변수로 받는 생성자가 있다.
        // 이 생성자는 Post 객체에서 필요한 정보를 가져와서 PostResponseDto 객체를 초기화한다.
        // 생성자 내에서 post 객체의 getter 메서드를 호출하여 필드에 값을 설정한다.

        //이렇게 생성된 PostResponseDto 객체는 조회된 게시물의 정보를 담고 있으며, 클라이언트에게 전달될 수 있다.
    }
}