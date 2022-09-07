package com.enwerevincent.restblog.Controller;

import com.enwerevincent.restblog.DTO.CommentDto;
import com.enwerevincent.restblog.DTO.LikeDto;
import com.enwerevincent.restblog.DTO.PostDto;
import com.enwerevincent.restblog.DTO.UserDto;
import com.enwerevincent.restblog.Model.Post;
import com.enwerevincent.restblog.Response.*;
import com.enwerevincent.restblog.Service.UserService;
import com.enwerevincent.restblog.ServiceImpl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController   @Slf4j
@RequestMapping( value = "/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping(value = "/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody UserDto userDto){
        log.info("Successfully Registered {} " , userDto.getEmail());
     //  URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/register").toUriString());
       // return ResponseEntity.created(uri).body( );
        return new ResponseEntity<>(userService.register(userDto) , CREATED);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<CreatePostResponse> create(@RequestBody PostDto postDto){
        log.info("Successfully Created A post With Title:  {} " , postDto.getTitle());
        return  new ResponseEntity<>(userService.createPost(postDto) , CREATED);
    }

    @PostMapping(value = "/comment/{user_id}/{post_id}")
    public ResponseEntity<CommentResponse> comment(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value = "post_id") Integer post_id,  @RequestBody CommentDto commentDto){
        log.info("Successfully commented :  {} " , commentDto.getComment());
      //  URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/comment").toUriString());
      //  return ResponseEntity.created(uri).body();
        return new ResponseEntity<>( userService.comment(user_id , post_id , commentDto) , CREATED);
    }

    @PostMapping(value = "/like/{user_id}/{post_id}")
    public ResponseEntity<LikeResponse> like(@PathVariable(value = "user_id") Integer user_id, @PathVariable(value = "post_id") Integer post_id, @RequestBody LikeDto likeDto){
        log.info("Successfully liked :  {} " , likeDto.isLiked());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/like").toUriString());
        return ResponseEntity.created(uri).body( userService.like(user_id , post_id , likeDto));
    }

    @GetMapping(value = "/searchPost/{keyword}")
    public ResponseEntity<SearchPostResponse> searchPost(@PathVariable(  value = "keyword") String keyword){
        return new ResponseEntity<>(userService.searchPost(keyword) , OK);
    }

    @GetMapping(value = "/searchComment/{keyword}")
    public ResponseEntity<SearchCommentResponse> searchComment(@PathVariable(  value = "keyword") String keyword){
        return ResponseEntity.ok().body(userService.searchComment(keyword));
    }

    @GetMapping(value = "/post/{id}")
    public ResponseEntity<Post> searchComment(@PathVariable(  value = "id") Integer id){
        return ResponseEntity.ok().body(userService.findPostById(id));
    }


}
