package com.enwerevincent.restblog.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class LoginDto {
    private String email;
    private String password;
}
