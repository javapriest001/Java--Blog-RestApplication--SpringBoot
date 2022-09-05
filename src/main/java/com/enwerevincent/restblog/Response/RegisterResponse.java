package com.enwerevincent.restblog.Response;

import com.enwerevincent.restblog.Model.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RegisterResponse {
    private String message;
    private LocalDateTime timeStamp;
    private User user;
}
