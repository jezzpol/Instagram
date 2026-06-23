package com.Futkaradze.service.impl;

import com.Futkaradze.models.Comment;
import com.Futkaradze.models.Post;
import com.Futkaradze.models.User;
import com.Futkaradze.repository.CommentRepository;
import com.Futkaradze.repository.PostRepository;
import com.Futkaradze.service.CommentService;
import com.Futkaradze.service.PostService;
import com.Futkaradze.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {


    private final PostService postService;
    private final UserService userService;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(PostService postService, UserService userService, CommentRepository commentRepository, PostRepository postRepository) {
        this.postService = postService;
        this.userService = userService;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public Comment createComment(Comment comment, Long postId, Long userId) throws Exception {

        User user = userService.findUserById(userId);

        Post post = postService.findPostById(postId);

        comment.setUser(user);
        comment.setContent(comment.getContent());
        comment.setCraetedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        post.getComments().add(savedComment);

        postRepository.save(post);

        return savedComment;
    }

    @Override
    public Comment findCommentById(Long commentId) throws Exception {

        Optional<Comment> opt = commentRepository.findById(commentId);

        if (opt.isEmpty()) {
            throw new Exception("Comment not exist");
        }
        return opt.get();
    }

    @Override
    public Comment likeComment(Long CommentId, Long userId) throws Exception {

        Comment comment = findCommentById(CommentId);

        User user = userService.findUserById(userId);

        if (!comment.getLiked().contains(user)) {

            comment.getLiked().add(user);

        } else comment.getLiked().remove(user);

        return commentRepository.save(comment);
    }
}
