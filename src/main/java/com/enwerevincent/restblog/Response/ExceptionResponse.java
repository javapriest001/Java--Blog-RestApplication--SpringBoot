package com.enwerevincent.restblog.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data @AllArgsConstructor @NoArgsConstructor
public class ExceptionResponse {
    private String message;
    private HttpStatus status;
}
