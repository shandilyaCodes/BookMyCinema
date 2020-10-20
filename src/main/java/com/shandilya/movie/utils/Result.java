package com.shandilya.movie.utils;

import lombok.Getter;

@Getter
public class Result {

    private int code;
    private String message;
    private Object data;

    private Result setResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        return this;
    }

    public Result success() {
        return setResult(200, "Success", data);
    }
}