package com.enwerevincent.restblog.Response;

import com.enwerevincent.restblog.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private LocalDateTime timeStamp;
}
