package com.enwerevincent.restblog.Exception;

import lombok.Getter;

@Getter
public class PostAlreadyLikedException extends RuntimeException{
    private String message;

    public PostAlreadyLikedException(String message) {
        this.message = message;
    }
}
