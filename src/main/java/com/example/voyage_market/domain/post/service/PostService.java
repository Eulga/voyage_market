package com.example.voyage_market.domain.post.service;

import com.example.voyage_market.domain.post.dto.PostRequestDto;
import com.example.voyage_market.domain.post.dto.PostResponseDto;
import com.example.voyage_market.domain.post.entity.Post;
import com.example.voyage_market.domain.post.exception.PostException;
import com.example.voyage_market.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.voyage_market.domain.post.exception.ErrorCode.NOT_FOUND_DATA;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 판매 게시글 작성
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = Post.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .price(requestDto.getPrice())
                .username(requestDto.getUsername())
                .build();
        Post responsePost = postRepository.save(post);
        return new PostResponseDto(responsePost);
    }

    //판매 게시글 전체 리스트 조회
    public List<PostResponseDto> getPostList() {
        return postRepository.findAll().stream().map(PostResponseDto::new).toList();
    }

    // 판매 게시글 상세 조회
    public PostResponseDto getPost(Long id) {
        return new PostResponseDto(postRepository.findById(id).orElseThrow(
                () -> new PostException(NOT_FOUND_DATA)
        ));
    }

    // 판매 게시글 수정
    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostException(NOT_FOUND_DATA)
        );
        post.updatePost(requestDto);

        return new PostResponseDto(post);
    }

    // 게시글 삭제
    public Map<String, String> deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new PostException(NOT_FOUND_DATA)
        );

        postRepository.delete(post);
        return Collections.singletonMap("msg","삭제완료");
    }
}
