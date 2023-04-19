package com.sparta.boardsb.service;

import com.sparta.boardsb.dto.BoardRequestDTO;
import com.sparta.boardsb.dto.BoardResponseDTO;
import com.sparta.boardsb.entity.Post;
import com.sparta.boardsb.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    // DB에 있는 모든 Post를 가져옴.
    @Transactional
    public List<BoardResponseDTO> getAllPost(){
//        return boardRepository.findAll();
        List<BoardResponseDTO> boardResponseDTOList = new ArrayList<>();
        List<Post> allByOrderByCreatedAtDesc = boardRepository.findAllByOrderByCreatedAtDesc();
        for (Post post : allByOrderByCreatedAtDesc) {
            boardResponseDTOList.add(new BoardResponseDTO(post));
        }
        return boardResponseDTOList;
    }

    // Post 등록
    @Transactional
    public BoardResponseDTO createPost(BoardRequestDTO requestDTO){
        Post post = new Post(requestDTO);
        boardRepository.save(post);
        return new BoardResponseDTO(post);
    }

    @Transactional
    public BoardResponseDTO lookUpAPost(Long id) {
        return new BoardResponseDTO(boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id)));
    }

    @Transactional
    public BoardResponseDTO updatePost(Long id, BoardRequestDTO requestDTO) {
        Post post = boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        if(post.getPassword().equals(requestDTO.getPassword()))
            post.update(requestDTO);
        else{
            throw new IllegalArgumentException("비밀번호가 일치하지 않아요!");
        }
        return new BoardResponseDTO(post);
    }

    @Transactional
    public boolean deletePost(Long id, BoardRequestDTO requestDTO) {
        Post post = boardRepository.findById(id).orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        Boolean stateText;
        if(post.getPassword().equals(requestDTO.getPassword())){
            boardRepository.deleteById(id);
            stateText = true;
        }else{
            stateText = false;
        }
        return stateText;
    }
}
