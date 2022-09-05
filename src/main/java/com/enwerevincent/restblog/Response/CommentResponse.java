package com.enwerevincent.restblog.Response;

import com.enwerevincent.restblog.Model.Comment;
import com.enwerevincent.restblog.Model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CommentResponse {
    private String message;
    private LocalDateTime timeStamp;
    private Comment comment;
    private  Post post;

}
