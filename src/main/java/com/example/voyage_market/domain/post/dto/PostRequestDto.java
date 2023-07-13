package com.example.voyage_market.domain.post.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    private final String title;
    private final String content;
    private final Integer price;
    private final String username;

    public PostRequestDto(String title, String content, Integer price, String username) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.username = username;
    }
}
