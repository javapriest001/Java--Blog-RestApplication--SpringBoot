package com.enwerevincent.restblog.Service;

import com.enwerevincent.restblog.DTO.*;
import com.enwerevincent.restblog.Model.Comment;
import com.enwerevincent.restblog.Model.Like;
import com.enwerevincent.restblog.Model.Post;
import com.enwerevincent.restblog.Model.User;
import com.enwerevincent.restblog.Response.*;

public interface UserService {

    RegisterResponse register(UserDto userDto);

    LoginResponse login(LoginDto loginDto);

    CreatePostResponse createPost(PostDto postDto);

    CommentResponse comment(int user_id , int post_id , CommentDto commentDto);

    LikeResponse like(int user_id , int post_id , LikeDto likeDto);

    SearchCommentResponse searchComment(String keyword);


    SearchPostResponse searchPost(String keyword);

    Post findPostById(int id);

}
