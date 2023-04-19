package com.sparta.boardsb.dto;

import com.sparta.boardsb.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardResponseDTO {
    private String title;
    private String writer;
    private LocalDateTime createdAt;
    private String content;

    public BoardResponseDTO(Post post) {
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.createdAt = post.getCreatedAt();
        this.content = post.getContent();
    }
}
