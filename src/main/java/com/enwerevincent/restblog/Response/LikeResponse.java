package com.enwerevincent.restblog.Response;

import com.enwerevincent.restblog.Model.Comment;
import com.enwerevincent.restblog.Model.Like;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class LikeResponse {
    private String message;
    private LocalDateTime timeStamp;
    private Like like;
    private int totalLikes;
}
