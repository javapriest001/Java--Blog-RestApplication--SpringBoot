package com.enwerevincent.restblog.Repository;

import com.enwerevincent.restblog.Model.Comment;
import com.enwerevincent.restblog.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByCommentContaining(String keyword);
}
