package com.example.voyage_market.domain.post.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum ErrorCode {
    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    NOT_FOUND_DATA(NOT_FOUND, "해당 게시물을 찾을 수 없습니다."),

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),

    ;

    private final HttpStatus httpStatus;
    private final String detail;

    ErrorCode(HttpStatus httpStatus, String detail) {
        this.httpStatus = httpStatus;
        this.detail = detail;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public String getDetail() {
        return this.detail;
    }
}
