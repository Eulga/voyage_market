package com.example.voyage_market.domain.post.repository;

import com.example.voyage_market.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
