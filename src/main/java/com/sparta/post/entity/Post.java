package com.sparta.post.entity;

import com.sparta.post.dto.PostRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
// Entity 애노테이션은 해당 클래스가 엔티티임을 나타내고, 데이터베이스와 매핑된다는 것을 알려준다.
@Getter
@Setter
@NoArgsConstructor
// 매기변수가 없는 기본 생성자를 만들어 준다.
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    // nullable = false: 해당 컬럼이 null 값을 허용하지 않음을 나타냅니다.
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    // columnDefinition = "TEXT": 해당 컬럼의 데이터 타입을 "TEXT"로 지정합니다.
    // 이는 긴 텍스트 데이터를 저장할 수 있는 타입입니다. (기본값은 VARCHAR 입니다.)
    private String content;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;

//    public Post(String title, String content, String author, String password) {
//        this.title = title;
//        this.content = content;
//        this.author = author;
//        this.password = password;
//    }

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
    }

    // 게시물 수정
    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.author = requestDto.getAuthor();
    }
}
