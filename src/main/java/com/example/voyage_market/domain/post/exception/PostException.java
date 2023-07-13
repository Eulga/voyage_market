package com.example.voyage_market.domain.post.exception;

import lombok.Getter;

@Getter
public class PostException extends RuntimeException {
    private final ErrorCode errorCode;

    public PostException(ErrorCode e) {
        super(e.getDetail());
        this.errorCode = e;
    }
}
