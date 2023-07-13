package com.example.voyage_market.domain.post.dto;

import com.example.voyage_market.domain.post.entity.Post;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {
    private final Long id;
    private final String title;
    private String content;
    private final Integer price;
    private final String username;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.price = post.getPrice();
        this.username = post.getUsername();
    }

    public void clearContent() {
        this.content = null;
    }

}
