package com.example.voyage_market.domain.post.controller;

import com.example.voyage_market.domain.post.dto.PostRequestDto;
import com.example.voyage_market.domain.post.dto.PostResponseDto;
import com.example.voyage_market.domain.post.exception.PostException;
import com.example.voyage_market.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 판매 게시글 작성
    @PostMapping("/post")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto requestDto) {
        PostResponseDto responseDto = postService.createPost(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    //판매 게시글 전체 리스트 조회
    @GetMapping("/post")
    public ResponseEntity<List<PostResponseDto>> getPostList() {
        List<PostResponseDto> responseDto = postService.getPostList();
        responseDto.forEach(PostResponseDto::clearContent);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 판매 게시글 상세 조회
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable Long postId) {
        PostResponseDto responseDto = postService.getPost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 판매 게시글 수정
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto requestDto) {
        PostResponseDto responseDto = postService.updatePost(postId,requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<Map<String, String>> deletePost(@PathVariable Long postId) {
        Map<String, String> response = postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    // 예외 처리
    @ExceptionHandler(PostException.class)
    public ResponseEntity<Map<String, String>> postExceptionHandler(PostException e) {
        Map<String, String> error = Collections.singletonMap("msg", e.getMessage());
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(error);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> runtimeExceptionHandler(Exception e){
        Map<String, String> error = Collections.singletonMap("error","RuntimeException occurred");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
