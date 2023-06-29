package com.sparta.post.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private String title;
    private String content;
    private String author;
    private String password;
// 해당 필드는 게시물 작성 요청 데이터를 저장하기 위해 사용되며,
// 클라이언트가 전달한 데이터를 이 필드에 설정하여 서버로 전송함
}