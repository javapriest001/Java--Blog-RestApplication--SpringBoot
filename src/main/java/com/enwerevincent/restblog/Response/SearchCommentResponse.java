package com.enwerevincent.restblog.Response;

import com.enwerevincent.restblog.Model.Comment;
import com.enwerevincent.restblog.Model.Post;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class SearchCommentResponse {

    private String message;
    private LocalDateTime timeStamp;
    private List<Comment> comments;
}
