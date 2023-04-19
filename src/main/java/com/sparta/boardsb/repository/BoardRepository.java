package com.sparta.boardsb.repository;

import com.sparta.boardsb.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc();

    @Query("SELECT p from Post p where p.id = :id")
    Optional<Post> findById(Long id);
}
