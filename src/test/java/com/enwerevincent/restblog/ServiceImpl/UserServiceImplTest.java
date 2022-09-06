package com.enwerevincent.restblog.ServiceImpl;

import com.enwerevincent.restblog.DTO.*;
import com.enwerevincent.restblog.Model.Comment;
import com.enwerevincent.restblog.Model.Like;
import com.enwerevincent.restblog.Model.Post;
import com.enwerevincent.restblog.Model.User;
import com.enwerevincent.restblog.Repository.CommentRepository;
import com.enwerevincent.restblog.Repository.LikeRepository;
import com.enwerevincent.restblog.Repository.PostRepository;
import com.enwerevincent.restblog.Repository.UserRepository;
import com.enwerevincent.restblog.Response.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.time.Month.AUGUST;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private LikeRepository likeRepository;
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private LocalDateTime localDateTime;
    private User user;
    private Comment comment;
    private Like like;
    private Post post;
    List<Like> likeList = new ArrayList<>();
    List<Post> postList = new ArrayList<>();
    List<Comment> commentList = new ArrayList<>();
    List<User> userList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        localDateTime = LocalDateTime.of(2022, AUGUST,3,6,30,40,50000);
        user = new User(1 , "vincent" , "enwerevincent@gmail.com" , "Admin" , "12345" , localDateTime , localDateTime  , postList , commentList , likeList);
        post = new Post(1 , "title of post" , "small description" , "title-of-post" , "0xEsddfmk.png", localDateTime , localDateTime , user, commentList , likeList);
        like = new Like(1 , true , localDateTime , localDateTime , user , post);
        comment = new Comment(1 , "lovely" , localDateTime , localDateTime , post , user);
    }

    @Test
    void register() {
        UserDto userDTO = new UserDto( "vincent" , "enwerevincent@gmail.com" , "Admin" , "12345");
        when(userRepository.save(user)).thenReturn(user);
        RegisterResponse registerResponse = new RegisterResponse("success" , localDateTime , user);
        var actual =  userService.register(userDTO);
        actual.setTimeStamp(localDateTime);
        actual.getUser().setCreatedAt(localDateTime);
        actual.getUser().setUpdatedAt(localDateTime);
        actual.getUser().setId(1);
        assertEquals(registerResponse , actual);
    }

    @Test
    void login_SuccessFullLogin() {
        LoginDto loginDto = new LoginDto("enwerevincent@gmail.com" , "12345");
        when(userRepository.findUserByEmail("enwerevincent@gmail.com")).thenReturn(Optional.ofNullable(user));
        LoginResponse loginResponse = new LoginResponse("success" , localDateTime);
        var actual =  userService.login(loginDto);
        actual.setTimeStamp(localDateTime);
        assertEquals(loginResponse , actual);
    }

    @Test
    void login_IncorrectPassword() {
        LoginDto loginDto = new LoginDto("enwerevincent@gmail.com" , "1238745");
        when(userRepository.findUserByEmail("enwerevincent@gmail.com")).thenReturn(Optional.ofNullable(user));
        LoginResponse loginResponse = new LoginResponse("password MisMatch" , localDateTime);
        var actual =  userService.login(loginDto);
        actual.setTimeStamp(localDateTime);
        assertEquals(loginResponse , actual);
    }

    @Test
    void createPost() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        PostDto postDto = new PostDto( "title of post" , "small description" , "0xEsddfmk.png",  1);
        CreatePostResponse createPostResponse = new CreatePostResponse("success" , localDateTime , post);
        var actual = userService.createPost(postDto);
        actual.setTimeStamp(localDateTime);
        actual.getPost().setCreatedAt(localDateTime);
        actual.getPost().setUpdatedAt(localDateTime);
        actual.getPost().setId(1);
        actual.getPost().setSlug("title-of-post");
        assertEquals(createPostResponse , actual);
    }

    @Test
    void like() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        when(postRepository.findById(1)).thenReturn(Optional.ofNullable(post));
        List<Like> likes = new ArrayList<>(Arrays.asList(like));
        when(likeRepository.likeList(1)).thenReturn(likes);
        LikeDto likeDto = new LikeDto(true);
        LikeResponse likeResponse = new LikeResponse("success" , localDateTime , like , 1);
        var actual = userService.like(1 , 1  , likeDto);
        actual.setTimeStamp(localDateTime);
        actual.setLike(like);
        likeResponse.getLike().setUser(user);
        likeResponse.getLike().setPost(post);
        assertEquals(likeResponse , actual);
    }

    @Test
    void comment() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        when(postRepository.findById(1)).thenReturn(Optional.ofNullable(post));
        CommentDto commentDto = new CommentDto("lovely");
        CommentResponse commentResponse = new CommentResponse("success" , localDateTime , comment , post);
        var actual = userService.comment(1 , 1  , commentDto);
        actual.setTimeStamp(localDateTime);
        actual.setComment(comment);
        commentResponse.setComment(comment);
        commentResponse.setPost(post);
        assertEquals(commentResponse , actual);
    }



    @Test
    void findUserById() {
        when(userRepository.findById(1)).thenReturn(Optional.ofNullable(user));
        assertEquals(user , userService.findUserById(1));
    }

    @Test
    void findPostById() {
        when(postRepository.findById(1)).thenReturn(Optional.ofNullable(post));
        assertEquals(post , userService.findPostById(1));
    }

    @Test
    void findUserByEmail() {
        when(userRepository.findUserByEmail("enwerevincent@gmail.com")).thenReturn(Optional.ofNullable(user));
        assertEquals(user , userService.findUserByEmail("enwerevincent@gmail.com"));
    }
}