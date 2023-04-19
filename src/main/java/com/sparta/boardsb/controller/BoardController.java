package com.sparta.boardsb.controller;

import com.sparta.boardsb.dto.BoardRequestDTO;
import com.sparta.boardsb.dto.BoardResponseDTO;
import com.sparta.boardsb.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // Board에 등록된 모든 Post를 조회
    @GetMapping("/board/get")
    public List<BoardResponseDTO> getAllPosts(){
        return boardService.getAllPost();
    }

    // Client가 입력한 data를 토대로 Post를 작성
    @PostMapping("/board/posts")
    public BoardResponseDTO createPost(@RequestBody BoardRequestDTO boardRequestDTO){
        return boardService.createPost(boardRequestDTO);
    }

    // 입력받은 id를 기준으로 해당 post를 한건을 조회
    @GetMapping("/board/posts/{id}")
    public BoardResponseDTO lookUpAPost(@PathVariable Long id){
        return boardService.lookUpAPost(id);
    }

    // 입력받은 id를 기준으로 해당 post를 수정
    @PutMapping("/board/posts/{id}")
    public BoardResponseDTO updatePost(@PathVariable Long id, @RequestBody BoardRequestDTO requestDTO){
        return boardService.updatePost(id, requestDTO);
    }

    // 입력받은 id를 기준으로 해당 post를 삭제
    @DeleteMapping("/board/posts/{id}")
    public boolean deletePost(@PathVariable Long id, @RequestBody BoardRequestDTO requestDTO){
        return boardService.deletePost(id, requestDTO);
    }
}
